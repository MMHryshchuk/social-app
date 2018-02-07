package com.clearsoft.welivre.ui.screens.auth.login;

/**
 * Created by on 28.06.17.
 */

public interface LoginView {
    void showError(String error);

    void openHome();

    void openReset();

    void openRegister();

    void loginWithGoogle();

    void showProgress();

    void hideProgress();
}
