package com.clearsoft.welivre.ui.screens.plan.p_assistences;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public interface PAssistancePresenter  extends Presenter<PAssistanceView>{

    void onNextClick(PlanActivity activity,String assistancesIds);
    void onBackClick(PlanActivity activity);
}
