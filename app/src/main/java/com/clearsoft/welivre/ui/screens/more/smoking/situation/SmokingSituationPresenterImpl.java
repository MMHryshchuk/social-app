package com.clearsoft.welivre.ui.screens.more.smoking.situation;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 18.07.17.
 */

public class SmokingSituationPresenterImpl extends BasePresenter<SmokingSituationView> implements SmokingSituationPresenter {

    App app;
    UserUseCase userUseCase;

    public SmokingSituationPresenterImpl(App app, UserUseCase userUseCase) {
        this.app = app;
        this.userUseCase = userUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(String situation) {
        if (getView() == null) return;
        getView().showProgress();
        addSubscription(userUseCase.setSmokingStatus(situation).subscribe(
                next -> {
                    getView().hideProgress();
                    getView().openNext();
                },
                error -> {
                    getView().hideProgress();

                }
        ));
    }
}
