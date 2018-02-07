package com.clearsoft.welivre.ui.screens.plan.p_assistences;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.dvo.PlanDvo;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public class PAssistancePresenterImpl extends BasePresenter<PAssistanceView> implements PAssistancePresenter{

    App app;
    PlanUseCase planUseCase;

    public PAssistancePresenterImpl(App app, PlanUseCase planUseCase) {
        this.app = app;
        this.planUseCase = planUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(PlanActivity activity,String assistancesIds) {
        activity.showProgress();
        PlanDvo dvo = activity.getDvo();
        dvo.setAssistanceIds(assistancesIds);
        planUseCase.createUpdatePlan(dvo)
                .subscribe(
                        next -> {
                            activity.hideProgress();
                            getView().openMyPlan();
                        },
                        error -> {
                            activity.hideProgress();
                        }
                );
    }

    @Override
    public void onBackClick(PlanActivity activity) {
        activity.openCravingsFragment(activity.getDvo().getCravingIds());
    }
}
