package com.clearsoft.welivre.ui.screens.my_plan;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;

/**
 * Created by on 18.07.17.
 */

public class MyPlanPresenterImpl extends BasePresenter<MyPlanView> implements MyPlanPresenter {

    App app;
    PlanUseCase planUseCase;

    public MyPlanPresenterImpl(App app, PlanUseCase planUseCase) {
        this.app = app;
        this.planUseCase = planUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(planUseCase.getMyPlan(app).subscribe(
                next -> {
                    getView().showData(next);
                },
                error -> {

                }
        ));
    }

    @Override
    public void onResume() {
        addSubscription(planUseCase.getMyPlan(app).subscribe(
                next -> {
                    getView().showData(next);

                },
                error -> {

                }
        ));
    }
}
