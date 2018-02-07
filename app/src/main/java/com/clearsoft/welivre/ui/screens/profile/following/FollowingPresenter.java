package com.clearsoft.welivre.ui.screens.profile.following;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 01.08.17.
 */

public interface FollowingPresenter extends Presenter<FollowingView> {

    void follow(String targetId,String followId);
    void unFollow(String targetId,String followId);
}
