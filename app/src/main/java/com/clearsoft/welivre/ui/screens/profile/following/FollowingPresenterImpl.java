package com.clearsoft.welivre.ui.screens.profile.following;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 01.08.17.
 */

public class FollowingPresenterImpl extends BasePresenter<FollowingView> implements FollowingPresenter {

    App app;
    UserUseCase userUseCase;
    private final String userId;

    public FollowingPresenterImpl(App app, UserUseCase userUseCase, String userId) {
        this.app = app;
        this.userUseCase = userUseCase;
        this.userId = userId;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(userUseCase.getAllFollowings(userId,(long)-1).subscribe(
                next -> {
                    getView().showData(next);
                },
                error -> {

                }
        ));
    }

    @Override
    public void follow(String targetId,String followId) {
        addSubscription(userUseCase.follow(userId).subscribe(
                next -> {
                    getView().updateFollow(followId,true);
                },
                error -> {

                }
        ));
    }

    @Override
    public void unFollow(String targetId,String followId) {
        addSubscription(userUseCase.unFollow(userId).subscribe(
                next -> {
                    getView().updateFollow(followId,false);
                },
                error -> {

                }
        ));
    }
}
