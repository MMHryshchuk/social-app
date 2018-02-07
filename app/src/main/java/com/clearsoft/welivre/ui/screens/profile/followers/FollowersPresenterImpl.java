package com.clearsoft.welivre.ui.screens.profile.followers;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 01.08.17.
 */

public class FollowersPresenterImpl extends BasePresenter<FollowersView> implements FollowersPresenter {

    App app;
    UserUseCase userUseCase;
    private final String userId;

    public FollowersPresenterImpl(App app, UserUseCase userUseCase, String userId) {
        this.app = app;
        this.userUseCase = userUseCase;
        this.userId = userId;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(userUseCase.getAllFollowers(userId,(long) -1).subscribe(
                next -> {
                    getView().showData(next);
                },
                error -> {

                }
        ));
    }
}
