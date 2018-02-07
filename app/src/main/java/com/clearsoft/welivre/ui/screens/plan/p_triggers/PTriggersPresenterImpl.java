package com.clearsoft.welivre.ui.screens.plan.p_triggers;

import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public class PTriggersPresenterImpl extends BasePresenter<PTriggersView> implements PTriggersPresenter {

    public PTriggersPresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(PlanActivity activity, String triggerIds) {
        activity.openCravingsFragment(triggerIds);
    }

    @Override
    public void onBackClick(PlanActivity activity) {
        activity.openPersonalMotivationFragment(activity.getDvo().getMotivationIds());
    }
}
