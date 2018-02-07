package com.clearsoft.welivre.ui.screens.auth.register;

/**
 * Created by on 28.06.17.
 */

public interface RegisterView {
    void openLogin();
    void openCompleteRegister();
    void showError(String message);
    void showProgress();
    void hideProgress();
}
