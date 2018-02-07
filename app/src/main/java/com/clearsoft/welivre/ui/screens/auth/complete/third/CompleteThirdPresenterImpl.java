package com.clearsoft.welivre.ui.screens.auth.complete.third;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.dvo.CompleteRegDvo;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;

/**
 * Created by on 28.06.17.
 */

public class CompleteThirdPresenterImpl extends BasePresenter<CompleteThirdView> implements CompleteThirdPresenter {

    App app;
    UserUseCase userUseCase;

    public CompleteThirdPresenterImpl(App app, UserUseCase userUseCase) {
        this.app = app;
        this.userUseCase = userUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onCalendarClick() {
        if (getView() == null) return;
        getView().openCalendar();
    }

    @Override
    public void onGenderClick(String gender) {
        if (getView() == null) return;
        getView().showGender(gender);

    }

    @Override
    public void onNextClick(CompleteRegisterActivity activity,
                            String sigaStartYear,
                            String birthday,
                            String gender) {
        if (getView() == null) return;
        getView().showProgress();
        CompleteRegDvo dvo = activity.getRegDvo();
        addSubscription(userUseCase.setUserSituation(dvo.getSituation(),
                dvo.getCigaDayliNum()
                ,dvo.getCigaPackCost(),
                dvo.getCigaWakeUpMinutes(),
                sigaStartYear,
                birthday
                ,gender
        ).subscribe(
                next -> {
                    getView().openHome();
                    getView().hideProgress();
                },
                error -> {
                    getView().hideProgress();

                }
        ));



    }
}
