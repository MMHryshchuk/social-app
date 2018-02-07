package com.clearsoft.welivre.ui.screens.plan.p_motivation;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public interface PMotivationPresenter extends Presenter<PMotivationView> {

    void onNextClick(PlanActivity activity, String motivationIds);
    void onBackClick(PlanActivity activity);
}
