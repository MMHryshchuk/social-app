package com.clearsoft.welivre.ui.screens.auth.login;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by on 28.06.17.
 */

public interface LoginPresenter extends Presenter<LoginView> {

    void onFacebookClick();
    void onGoogleClick();
    void onLoginClick(String email, String password);
    void onForgotClick();
    void onCreateClick();
}
