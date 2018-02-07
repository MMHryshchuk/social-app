package com.clearsoft.welivre.ui.screens.plan.p_cravings;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public interface PCravingsPresenter extends Presenter<PCravingsView> {
    void onNextClick(PlanActivity activity,String assistanceIds);
    void onBackClick(PlanActivity activity);
}
