package com.clearsoft.welivre.domain.use_cases.impl;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;
import com.clearsoft.welivre.core.utils.DateUtils;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.api.PlanApi;
import com.clearsoft.welivre.domain.api.request.CrUpPlanRequest;
import com.clearsoft.welivre.domain.api.request.GetPlanRequest;
import com.clearsoft.welivre.domain.api.request.PlanRequest;
import com.clearsoft.welivre.domain.mappers.ErrorMapper;
import com.clearsoft.welivre.domain.mappers.PlanMapper;
import com.clearsoft.welivre.domain.repository.PlanRepository;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.dvo.AssistancesDvo;
import com.clearsoft.welivre.ui.dvo.CravingsDvo;
import com.clearsoft.welivre.ui.dvo.HealthDvo;
import com.clearsoft.welivre.ui.dvo.MotivationDvo;
import com.clearsoft.welivre.ui.dvo.MyPlanDvo;
import com.clearsoft.welivre.ui.dvo.PlanDvo;
import com.clearsoft.welivre.ui.dvo.TriggersDvo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by on 18.07.17.
 */

public class PlanUseCaseImpl implements PlanUseCase {


    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private PlanApi planApi;
    private PlanRepository planRepository;
    private PreferenceRepository preferenceRepository;
    private SaveEntityRepository saveEntityRepository;
    private ErrorMapper errorMapper;
    private PlanMapper planMapper;

    public PlanUseCaseImpl(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            Retrofit retrofit,
            AuthRetrofit authRetrofit,
            PlanRepository planRepository,
            PreferenceRepository preferenceRepository,
            SaveEntityRepository saveEntityRepository,
            ErrorMapper errorMapper,
            PlanMapper planMapper
    ) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.planApi = authRetrofit.create(PlanApi.class);
        this.planRepository = planRepository;
        this.preferenceRepository = preferenceRepository;
        this.saveEntityRepository = saveEntityRepository;
        this.errorMapper = errorMapper;
        this.planMapper = planMapper;
    }


    @Override
    public Observable<MyPlanDvo> getMyPlan(App app) {
        return Observable.concat(
                getMyPlanFromDB(app)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()),
                getMyPlanFromSever(app)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()
                        ));
    }


    @Override
    public Observable<Object> createUpdatePlan(PlanDvo dvo) {
        String relapsed = preferenceRepository.getUserPlanId() == 0 ? "1" : "2";
        return planApi.createUpdatePlan(new CrUpPlanRequest(
                Integer.parseInt(preferenceRepository.getUserId()),
                dvo.getDate(),
                dvo.getMotivationIds(),
                dvo.getTroggerIds(),
                dvo.getCravingIds(),
                dvo.getAssistanceIds(),
                "2")
        )
                .doOnNext(o -> {
                    preferenceRepository.saveUserStartNoSmoke(dvo.getMilisec());
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<HealthDvo> getHealth() {
        return Observable.create(e -> {
            HealthDvo dvo;
            long currentTime = System.currentTimeMillis();
            long timeEnd = preferenceRepository.getUserStartSmoke();
            long milisec = currentTime - timeEnd;
            if (currentTime < timeEnd) {
                dvo = new HealthDvo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            } else {
                int days = (int) (milisec / 86400000);
                if (days > 1) {
                    dvo = new HealthDvo(
                            100,
                            100,
                            100,
                            100,
                            days * 100 / 2,
                            days * 100 / 21,
                            days * 100 / 30,
                            days * 100 / 365,
                            days * 100 / 1825,
                            days * 100 / 3650


                    );
                } else if (days == 1) {
                    int minutes = (int) (milisec / 600000);
                    dvo = new HealthDvo(
                            minutes * 100 / 20,
                            minutes * 100 / 120,
                            minutes * 100 / 480,
                            minutes * 100 / 1440,
                            minutes * 100 / 2880,
                            0,
                            0,
                            0,
                            0,
                            0
                    );
                } else {
                    dvo = new HealthDvo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

                }

                e.onNext(dvo);
                e.onComplete();
            }
        });
    }


    @Override
    public Observable<List<MotivationDvo>> getMotivationsToRecycle(App app) {


        return planApi.getMotivations(new PlanRequest(preferenceRepository.getUserPlanId()))
                .doOnNext(o -> {

                })
                .map(o -> {
                    List<MotivationDvo> dvos = getMotivations(app);
                    return dvos;
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

    }

    private Observable<MyPlanDvo> getMyPlanFromSever(App app){
       return planApi.getPlan(new GetPlanRequest(Integer.parseInt(preferenceRepository.getUserId())))
                .doOnNext(planDtoResponse -> {
                    if (planDtoResponse.getResult() != null)
                        saveEntityRepository.savePlan(planDtoResponse.getResult());
                    preferenceRepository.saveUserPlanId(planDtoResponse.getResult().getId());
                    preferenceRepository.saveUserStartNoSmoke(DateUtils.parseDateToMilliseconds(planDtoResponse.getResult().getQuitDate()));

                })
                .flatMap(planDtoResponse -> getMyPlanFromDB(app));

    }

    private Observable<MyPlanDvo> getMyPlanFromDB(App app){
        return planRepository.getMyPlan()
                .map(plan -> {
                    if (StringUtils.isNullEmpty(plan.getUserId()))
                        return new MyPlanDvo(new PlanDvo("0000-00-00","","","","",""),
                                new ArrayList<MotivationDvo>()
                                ,new ArrayList<CravingsDvo>(),
                                new ArrayList<AssistancesDvo>(),
                                new ArrayList<MotivationDvo>());
                    PlanDvo planDvo = new PlanDvo(
                            plan.getQuitDate(),
                            plan.getMotivationsIds(),
                            plan.getTriggersIds(),
                            plan.getCravingsIds(),
                            plan.getAssistancesIds());

                    List<MotivationDvo> newMotivationDvos = new ArrayList<>();

                    String[] motivationIds = plan.getMotivationsIds().split(",");
                    List<MotivationDvo> motivationDvos = getMotivations(app);
                    Stream.of(motivationDvos).forEach(dvo -> {
                        Stream.of(motivationIds).forEach(s -> {
                            if (dvo.getId() == Integer.parseInt(s)) {
                                newMotivationDvos.add(dvo);
                            }
                        });
                    });

                    List<CravingsDvo> newCravingsDvos = new ArrayList<>();
                    String[] cravingsIds = plan.getCravingsIds().split(",");
                    List<CravingsDvo> cravingsDvos = getCravings(app);
                    Stream.of(cravingsDvos).forEach(dvo -> {
                        Stream.of(cravingsIds).forEach(s -> {
                            if (dvo.getCravingId() == Integer.parseInt(s)) {
                                newCravingsDvos.add(dvo);
                            }
                        });
                    });

                    List<AssistancesDvo> newAssistancesDvos = new ArrayList<>();

                    String[] assistancesIds = plan.getAssistancesIds().split(",");
                    List<AssistancesDvo> assistancesDvos = getAssistances(app);
                    Stream.of(assistancesDvos).forEach(dvo -> {
                        Stream.of(assistancesIds).forEach(s -> {
                            if (dvo.getAssistancesId() == Integer.parseInt(s)) {
                                newAssistancesDvos.add(dvo);
                            }
                        });
                    });

                    String[] triggersIds = plan.getTriggersIds().split(",");
                    List<MotivationDvo> newTriggersDvos = new ArrayList<>();

                    List<MotivationDvo> triggersDvos = getTriggers(app);
                    Stream.of(triggersDvos).forEach(dvo -> {
                        Stream.of(triggersIds).forEach(s -> {
                            if (dvo.getId() == Integer.parseInt(s)) {
                                newTriggersDvos.add(dvo);
                            }
                        });
                    });


                    return new MyPlanDvo(planDvo, newMotivationDvos, newCravingsDvos, newAssistancesDvos, newTriggersDvos);

                });
    }

    private List<MotivationDvo> getMotivations(App app) {
        List<MotivationDvo> data = new ArrayList<>();
        data.add(new MotivationDvo(1, app.getString(R.string.activity_plan_motivation_name_1), R.drawable.plan_motivation_1));
        data.add(new MotivationDvo(2, app.getString(R.string.activity_plan_motivation_name_2), R.drawable.plan_motivation_2));
        data.add(new MotivationDvo(3, app.getString(R.string.activity_plan_motivation_name_3), R.drawable.plan_motivation_3));
        data.add(new MotivationDvo(4, app.getString(R.string.activity_plan_motivation_name_4), R.drawable.plan_motivation_4));
        data.add(new MotivationDvo(5, app.getString(R.string.activity_plan_motivation_name_5), R.drawable.plan_motivation_5));
        data.add(new MotivationDvo(6, app.getString(R.string.activity_plan_motivation_name_6), R.drawable.plan_motivation_6));
        data.add(new MotivationDvo(7, app.getString(R.string.activity_plan_motivation_name_7), R.drawable.plan_motivation_7));
        data.add(new MotivationDvo(8, app.getString(R.string.activity_plan_motivation_name_8), R.drawable.plan_motivation_8));
        data.add(new MotivationDvo(9, app.getString(R.string.activity_plan_motivation_name_9), R.drawable.plan_motivation_9));
        data.add(new MotivationDvo(10, app.getString(R.string.activity_plan_motivation_name_10), R.drawable.plan_motivation_10));
        data.add(new MotivationDvo(11, app.getString(R.string.activity_plan_motivation_name_11), R.drawable.plan_motivation_11));
        data.add(new MotivationDvo(12, app.getString(R.string.activity_plan_motivation_name_12), R.drawable.plan_motivation_12));
        data.add(new MotivationDvo(13, app.getString(R.string.activity_plan_motivation_name_13), R.drawable.plan_motivation_13));
        data.add(new MotivationDvo(14, app.getString(R.string.activity_plan_motivation_name_14), R.drawable.plan_motivation_14));
        data.add(new MotivationDvo(15, app.getString(R.string.activity_plan_motivation_name_15), R.drawable.plan_motivation_15));
        data.add(new MotivationDvo(16, app.getString(R.string.activity_plan_motivation_name_16), R.drawable.plan_motivation_16));
        data.add(new MotivationDvo(17, app.getString(R.string.activity_plan_motivation_name_17), R.drawable.plan_motivation_17));
        data.add(new MotivationDvo(18, app.getString(R.string.activity_plan_motivation_name_18), R.drawable.plan_motivation_18));
        data.add(new MotivationDvo(19, app.getString(R.string.activity_plan_motivation_name_19), R.drawable.plan_motivation_19));
        data.add(new MotivationDvo(20, app.getString(R.string.activity_plan_motivation_name_20), R.drawable.plan_motivation_20));
        data.add(new MotivationDvo(21, app.getString(R.string.activity_plan_motivation_name_21), R.drawable.plan_motivation_21));
        data.add(new MotivationDvo(22, app.getString(R.string.activity_plan_motivation_name_22), R.drawable.plan_motivation_22));
        data.add(new MotivationDvo(23, app.getString(R.string.activity_plan_motivation_name_23), R.drawable.plan_motivation_23));
        data.add(new MotivationDvo(24, app.getString(R.string.activity_plan_motivation_name_24), R.drawable.plan_motivation_24));
        data.add(new MotivationDvo(25, app.getString(R.string.activity_plan_motivation_name_25), R.drawable.plan_motivation_25));
        data.add(new MotivationDvo(26, app.getString(R.string.activity_plan_motivation_name_26), R.drawable.plan_motivation_26));
        data.add(new MotivationDvo(27, app.getString(R.string.activity_plan_motivation_name_27), R.drawable.plan_motivation_27));
        data.add(new MotivationDvo(28, app.getString(R.string.activity_plan_motivation_name_28), R.drawable.plan_motivation_28));
        data.add(new MotivationDvo(29, app.getString(R.string.activity_plan_motivation_name_29), R.drawable.plan_motivation_29));

        return data;
    }

    private List<CravingsDvo> getCravings(App app) {
        List<CravingsDvo> data = new ArrayList<>();

        data.add(new CravingsDvo(1, app.getString(R.string.activity_plan_cravings_title_1), app.getString(R.string.activity_plan_cravings_txt_1), R.drawable.cravings_1));
        data.add(new CravingsDvo(2, app.getString(R.string.activity_plan_cravings_title_2), app.getString(R.string.activity_plan_cravings_txt_2), R.drawable.cravings_2));
        data.add(new CravingsDvo(3, app.getString(R.string.activity_plan_cravings_title_3), app.getString(R.string.activity_plan_cravings_txt_3), R.drawable.cravings_3));
        data.add(new CravingsDvo(4, app.getString(R.string.activity_plan_cravings_title_4), app.getString(R.string.activity_plan_cravings_txt_4), R.drawable.cravings_4));
        data.add(new CravingsDvo(5, app.getString(R.string.activity_plan_cravings_title_5), app.getString(R.string.activity_plan_cravings_txt_5), R.drawable.cravings_5));

        return data;
    }

    private List<AssistancesDvo> getAssistances(App app) {
        List<AssistancesDvo> data = new ArrayList<>();
        data.add(new AssistancesDvo(1, app.getString(R.string.activity_plan_assistances_title_1), R.drawable.assistances_1));
        data.add(new AssistancesDvo(2, app.getString(R.string.activity_plan_assistances_title_2), R.drawable.assistances_2));
        data.add(new AssistancesDvo(3, app.getString(R.string.activity_plan_assistances_title_3), R.drawable.assistances_3));
        data.add(new AssistancesDvo(4, app.getString(R.string.activity_plan_assistances_title_4), R.drawable.assistances_4));
        data.add(new AssistancesDvo(5, app.getString(R.string.activity_plan_assistances_title_5), R.drawable.assistances_5));
        data.add(new AssistancesDvo(6, app.getString(R.string.activity_plan_assistances_title_6), R.drawable.assistances_6));

        return data;
    }

    private List<MotivationDvo> getTriggers(App app) {
        List<MotivationDvo> data = new ArrayList<>();
        data.add(new MotivationDvo(1, app.getString(R.string.activity_plan_triggers_title_1), R.drawable.plan_triggers_1));
        data.add(new MotivationDvo(2, app.getString(R.string.activity_plan_triggers_title_2), R.drawable.plan_triggers_2));
        data.add(new MotivationDvo(3, app.getString(R.string.activity_plan_triggers_title_3), R.drawable.plan_triggers_3));
        data.add(new MotivationDvo(4, app.getString(R.string.activity_plan_triggers_title_4), R.drawable.plan_triggers_4));
        data.add(new MotivationDvo(5, app.getString(R.string.activity_plan_triggers_title_5), R.drawable.plan_triggers_5));
        data.add(new MotivationDvo(6, app.getString(R.string.activity_plan_triggers_title_6), R.drawable.plan_triggers_6));
        data.add(new MotivationDvo(7, app.getString(R.string.activity_plan_triggers_title_7), R.drawable.plan_triggers_7));
        data.add(new MotivationDvo(8, app.getString(R.string.activity_plan_triggers_title_8), R.drawable.plan_triggers_8));
        data.add(new MotivationDvo(9, app.getString(R.string.activity_plan_triggers_title_9), R.drawable.plan_triggers_9));
        data.add(new MotivationDvo(10, app.getString(R.string.activity_plan_triggers_title_10), R.drawable.plan_triggers_10));
        data.add(new MotivationDvo(11, app.getString(R.string.activity_plan_triggers_title_11), R.drawable.plan_triggers_11));
        data.add(new MotivationDvo(12, app.getString(R.string.activity_plan_triggers_title_12), R.drawable.plan_triggers_12));
        data.add(new MotivationDvo(13, app.getString(R.string.activity_plan_triggers_title_13), R.drawable.plan_triggers_13));
        data.add(new MotivationDvo(14, app.getString(R.string.activity_plan_triggers_title_14), R.drawable.plan_triggers_14));

        return data;
    }


}
