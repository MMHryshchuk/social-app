package com.clearsoft.welivre.ui.screens.profile;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 01.08.17.
 */

public class ProfilePresenterImpl extends BasePresenter<ProfileView> implements ProfilePresenter {

    App app;
    UserUseCase userUseCase;

    private final String userId;

    public ProfilePresenterImpl(App app, UserUseCase userUseCase, String userId) {
        this.app = app;
        this.userUseCase = userUseCase;
        this.userId = userId;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(userUseCase.getSpecificUserInfo(Integer.parseInt(userId)).subscribe(
                next -> {
                    getView().showData(next);
                }

        ));
    }

    @Override
    public void onFollowClick() {
        addSubscription(userUseCase.follow(userId).subscribe(
                next -> {
                    getView().setUnFollow();
                },
                error -> {

                }
        ));
    }

    @Override
    public void onUnFollowClick() {
        addSubscription(userUseCase.unFollow(userId).subscribe(
                next -> {
                    getView().setFollow();
                },
                error -> {

                }
        ));
    }
}
