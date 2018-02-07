package com.clearsoft.welivre.ui.screens.profile;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 01.08.17.
 */

public interface ProfilePresenter extends Presenter<ProfileView> {

    void onFollowClick();
    void onUnFollowClick();
}
