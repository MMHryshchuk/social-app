package com.clearsoft.welivre.ui.screens.auth.register;

import android.util.Log;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.utils.LanguageUtils;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;

/**
 * Created by on 28.06.17.
 */

public class RegisterPresenterImpl extends BasePresenter<RegisterView> implements RegisterPresenter {

    App app;
    AuthUseCase authUseCase;

    public RegisterPresenterImpl(App app, AuthUseCase authUseCase) {
        this.app = app;
        this.authUseCase = authUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onCreateClick(String email, String name, String password,String token) {
        if (getView() == null) return;
        getView().showProgress();
        addSubscription(authUseCase.register(email, name, password, LanguageUtils.getLang(app),token).subscribe(
                next -> {
                    getView().hideProgress();
                    if (StringUtils.isNullEmpty(next)){
                        getView().openCompleteRegister();
                    }else {
                        getView().showError(next);
                    }

                },
                error -> {}
        ));
    }

    @Override
    public void onHaveAccClick() {
        if (getView() == null) return;
        getView().openLogin();

    }
}
