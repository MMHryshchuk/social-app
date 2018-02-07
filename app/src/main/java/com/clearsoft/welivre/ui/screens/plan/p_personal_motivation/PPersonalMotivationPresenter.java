package com.clearsoft.welivre.ui.screens.plan.p_personal_motivation;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public interface PPersonalMotivationPresenter extends Presenter<PPersonalMotivationView> {

    void onNextClick(PlanActivity activity);
    void onBackClick(PlanActivity activity);
}
