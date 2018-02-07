package com.clearsoft.welivre.ui.screens.plan.p_triggers;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public interface PTriggersPresenter extends Presenter<PTriggersView> {

    void onNextClick(PlanActivity activity,String triggerIds);
    void onBackClick(PlanActivity activity);
}
