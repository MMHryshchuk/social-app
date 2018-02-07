package com.clearsoft.welivre.ui.screens.plan.p_cravings;

import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public class PCravingsPresenterImpl extends BasePresenter<PCravingsView> implements PCravingsPresenter{

    public PCravingsPresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(PlanActivity activity,String cravingIds) {
        activity.openAssistancesFragment(cravingIds);
    }

    @Override
    public void onBackClick(PlanActivity activity) {
        activity.openTriggersFragment();
    }
}
