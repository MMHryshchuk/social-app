package com.clearsoft.welivre.ui.screens.plan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.ui.di.PlanComponent;
import com.clearsoft.welivre.ui.dvo.PlanDvo;
import com.clearsoft.welivre.ui.screens.plan.p_assistences.PAssistanceFragment;
import com.clearsoft.welivre.ui.screens.plan.p_assistences.PAssistancePresenter;
import com.clearsoft.welivre.ui.screens.plan.p_cravings.PCravingsFragment;
import com.clearsoft.welivre.ui.screens.plan.p_cravings.PCravingsPresenter;
import com.clearsoft.welivre.ui.screens.plan.p_date.PDateFragment;
import com.clearsoft.welivre.ui.screens.plan.p_date.PDatePresenter;
import com.clearsoft.welivre.ui.screens.plan.p_motivation.PMotivationFragment;
import com.clearsoft.welivre.ui.screens.plan.p_motivation.PMotivationPresenter;
import com.clearsoft.welivre.ui.screens.plan.p_personal_motivation.PPersonalMotivationFragment;
import com.clearsoft.welivre.ui.screens.plan.p_personal_motivation.PPersonalMotivationPresenter;
import com.clearsoft.welivre.ui.screens.plan.p_triggers.PTriggersFragment;
import com.clearsoft.welivre.ui.screens.plan.p_triggers.PTriggersPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 19.07.17.
 */

public class PlanActivity extends BaseActivity {

    private final static int FRAGMENT_DATE = 381;
    private final static int FRAGMENT_MOTIVATION= 382;
    private final static int FRAGMENT_P_MOTIVATION = 383;
    private final static int FRAGMENT_CRAVINGS = 384;
    private final static int FRAGMENT_TRIGGERS = 385;
    private final static int FRAGMENT_ASISTANCE= 386;

    @BindView(R.id.activity_plan_progress_frame)
    FrameLayout vProgressFrame;

    @Inject
    PDatePresenter mPDatePresenter;
    @Inject
    PMotivationPresenter mPMotivationPresenter;
    @Inject
    PAssistancePresenter mPAssistancePresenter;
    @Inject
    PCravingsPresenter mPCravingPresenter;
    @Inject
    PTriggersPresenter mPTRiggersPresenter;
    @Inject
    PPersonalMotivationPresenter mPPersonalPresenter;
    private PlanDvo dvo;
    private int currentFragment;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, PlanActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ButterKnife.bind(this);
        setupDagger();
        replaceFragment(mPDatePresenter, new PDateFragment());
        currentFragment = FRAGMENT_DATE;
        dvo = new PlanDvo();

    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new PlanComponent.Module())
                .inject(this);
    }


    public void openMotivationFragment(String date, long milisec) {
        dvo.setDate(date);
        dvo.setMilisec(milisec);
        replaceFragment(mPMotivationPresenter, new PMotivationFragment());
        currentFragment = FRAGMENT_MOTIVATION;

    }

    public void openTriggersFragment() {
        replaceFragment(mPTRiggersPresenter, new PTriggersFragment());
        currentFragment = FRAGMENT_TRIGGERS;

    }

    public void openCravingsFragment(String triggerIds) {
        dvo.setTroggerIds(triggerIds);
        replaceFragment(mPCravingPresenter, new PCravingsFragment());
        currentFragment = FRAGMENT_CRAVINGS;
    }

    public void openAssistancesFragment(String cravingIds) {
        dvo.setCravingIds(cravingIds);
        replaceFragment(mPAssistancePresenter, new PAssistanceFragment());
        currentFragment = FRAGMENT_ASISTANCE;
    }

    public void openDateFragment() {
        replaceFragment(mPDatePresenter, new PDateFragment());
        currentFragment = FRAGMENT_DATE;

    }

    public void openPersonalMotivationFragment(String motivationIds) {
        dvo.setMotivationIds(motivationIds);
        replaceFragment(mPPersonalPresenter, new PPersonalMotivationFragment());
        currentFragment = FRAGMENT_P_MOTIVATION;
    }

    public PlanDvo getDvo() {
        return dvo;
    }

    public void showProgress() {
        vProgressFrame.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        vProgressFrame.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        switch (currentFragment){
            case FRAGMENT_DATE:
                finish();
                break;
            case FRAGMENT_MOTIVATION:
                openDateFragment();
                break;

            case FRAGMENT_P_MOTIVATION:
                openMotivationFragment(dvo.getMotivationIds(),dvo.getMilisec());
                break;

            case FRAGMENT_TRIGGERS:
                openPersonalMotivationFragment("");
                break;

            case FRAGMENT_CRAVINGS:
                openTriggersFragment();
                break;

            case FRAGMENT_ASISTANCE:
                openCravingsFragment(dvo.getCravingIds());
                break;

        }
    }
}
