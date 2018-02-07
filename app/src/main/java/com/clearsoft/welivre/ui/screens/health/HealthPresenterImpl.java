package com.clearsoft.welivre.ui.screens.health;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;

/**
 * Created by on 06.07.17.
 */

public class HealthPresenterImpl extends BasePresenter<HealthView> implements HealthPresenter {

    App app;
    PlanUseCase planUseCase;


    public HealthPresenterImpl(App app, PlanUseCase planUseCase) {
        this.app = app;
        this.planUseCase = planUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(planUseCase.getHealth().subscribe(
                next -> {
                    getView().showData(next);
                }
        ));
    }
}
