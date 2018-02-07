package com.clearsoft.welivre.ui.screens.plan.p_motivation;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.dvo.MotivationDvo;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 19.07.17.
 */

public class PMotivationPresenterImpl extends BasePresenter<PMotivationView> implements PMotivationPresenter{

    App app;
    PlanUseCase planUseCase;

    public PMotivationPresenterImpl(App app, PlanUseCase planUseCase) {
        this.app = app;
        this.planUseCase = planUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(planUseCase.getMotivationsToRecycle(app).subscribe(
                next -> {
                    getView().showData(next);
                }
        ));
    }

    @Override
    public void onNextClick(PlanActivity activity,String motivationIds) {
        activity.openPersonalMotivationFragment(motivationIds);
    }

    @Override
    public void onBackClick(PlanActivity activity) {
        activity.openDateFragment();
    }
}
