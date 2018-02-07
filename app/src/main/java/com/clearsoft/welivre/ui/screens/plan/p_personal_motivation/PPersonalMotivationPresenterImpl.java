package com.clearsoft.welivre.ui.screens.plan.p_personal_motivation;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public class PPersonalMotivationPresenterImpl extends BasePresenter<PPersonalMotivationView> implements PPersonalMotivationPresenter{

    App app;
    PlanUseCase planUseCase;

    public PPersonalMotivationPresenterImpl(App app, PlanUseCase planUseCase) {
        this.app = app;
        this.planUseCase = planUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(PlanActivity activity) {
        activity.openTriggersFragment();
    }

    @Override
    public void onBackClick(PlanActivity activity) {
        activity.openMotivationFragment(activity.getDvo().getDate(),activity.getDvo().getMilisec());
    }
}
