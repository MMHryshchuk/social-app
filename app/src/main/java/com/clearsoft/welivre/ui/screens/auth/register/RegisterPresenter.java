package com.clearsoft.welivre.ui.screens.auth.register;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 28.06.17.
 */

public interface RegisterPresenter extends Presenter<RegisterView> {

    void onCreateClick(String email, String name, String password,String token);

    void onHaveAccClick();
}
