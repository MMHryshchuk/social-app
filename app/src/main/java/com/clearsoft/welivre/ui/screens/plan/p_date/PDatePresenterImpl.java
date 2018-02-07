package com.clearsoft.welivre.ui.screens.plan.p_date;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

/**
 * Created by on 19.07.17.
 */

public class PDatePresenterImpl extends BasePresenter<PDateView> implements PDatePresenter {

    App app;
    PlanUseCase planUseCase;

    public PDatePresenterImpl(App app, PlanUseCase planUseCase) {
        this.app = app;
        this.planUseCase = planUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(PlanActivity activity, String date, long milisec) {
        activity.openMotivationFragment(date,milisec);
    }

    @Override
    public void onDateTxtClick() {
        if (getView() == null) return;
        getView().openCalendar();
    }
}
