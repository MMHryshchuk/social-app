package com.clearsoft.welivre.ui.screens.plan.p_date;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public interface PDatePresenter extends Presenter<PDateView> {

    void onNextClick(PlanActivity activity, String date, long milisecond);
    void onDateTxtClick();
}
