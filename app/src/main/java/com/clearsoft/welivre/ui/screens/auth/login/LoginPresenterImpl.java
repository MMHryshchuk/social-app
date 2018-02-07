package com.clearsoft.welivre.ui.screens.auth.login;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.rx.SimpleSubscriber;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;

import org.reactivestreams.Subscriber;

/**
 * Created by on 28.06.17.
 */

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter {

    App app;
    AuthUseCase authUseCase;

    public LoginPresenterImpl(App app, AuthUseCase authUseCase) {
        this.app = app;
        this.authUseCase = authUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onFacebookClick() {

    }

    @Override
    public void onGoogleClick() {
        if (getView() == null) return;
        getView().loginWithGoogle();
    }

    @Override
    public void onLoginClick(String email, String password) {
        if (getView() == null) return;
        getView().showProgress();


        addSubscription(authUseCase.login(email, password).subscribe(
                next -> {
                    if (StringUtils.isNullEmpty(next)) {

                        getView().openHome();
                        getView().hideProgress();
                    } else {
                        getView().hideProgress();
                        getView().showError(next);
                    }

                },
                error -> {
                    getView().hideProgress();
                }


                )
        );


    }

    @Override
    public void onForgotClick() {
        if (getView() == null) return;
        getView().openReset();
    }

    @Override
    public void onCreateClick() {
        if (getView() == null) return;
        getView().openRegister();
    }


}
