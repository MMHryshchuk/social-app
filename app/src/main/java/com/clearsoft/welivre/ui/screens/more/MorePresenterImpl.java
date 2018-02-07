package com.clearsoft.welivre.ui.screens.more;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 05.07.17.
 */

public class MorePresenterImpl extends BasePresenter<MoreView> implements MorePresenter {

    App app;
    AuthUseCase authUseCase;
    UserUseCase userUseCase;

    public MorePresenterImpl(App app, AuthUseCase authUseCase, UserUseCase userUseCase) {
        this.app = app;
        this.authUseCase = authUseCase;
        this.userUseCase = userUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onLogoutClick() {
        addSubscription(authUseCase.logout().subscribe(
                next -> {
                    if (getView() == null) return;
                    getView().openLogin();
                },
                error -> {

                }
        ));
    }

    @Override
    public void onAwardsClick() {
        if (getView() == null) return;
        getView().openAwards();
    }

    @Override
    public void onHealthClick() {
        if (getView() == null) return;
        getView().openHealth();
    }

    @Override
    public void onSmokedClick() {
        if (getView() == null) return;
        getView().openSmoked();
    }

    @Override
    public void onMyPlanClick() {
        if (getView() == null) return;
        getView().openMyPlan();
    }

    @Override
    public void onSettingsClick() {
        if (getView() == null) return;
        getView().openSettings();
    }

    @Override
    public void onInviteClick() {
        if (getView() == null) return;
        getView().openInvite();
    }

    @Override
    public void onDeleteClick() {
        if (getView() == null) return;
//        addSubscription(userUseCase.deleteAccaunt().subscribe(o -> {
//            getView().closeAll();
//        }));
    }

    @Override
    public void onRateAppClick() {
        if (getView() == null) return;
        getView().openRateAppScreen();
    }

    @Override
    public void onAboutClick() {
        if (getView() == null)return;
        getView().openAbout();
    }
}
