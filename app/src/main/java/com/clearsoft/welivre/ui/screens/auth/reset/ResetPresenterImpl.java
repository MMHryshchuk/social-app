package com.clearsoft.welivre.ui.screens.auth.reset;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.utils.LanguageUtils;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;

/**
 * Created by on 28.06.17.
 */

public class ResetPresenterImpl extends BasePresenter<ResetView> implements ResetPresenter {

    App app;
    AuthUseCase authUseCase;

    public ResetPresenterImpl(App app, AuthUseCase authUseCase) {
        this.app = app;
        this.authUseCase = authUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onResetClick(String email) {
        addSubscription(authUseCase.reset(email, LanguageUtils.getLang(app)).subscribe(
                next -> getView().openLogin(),
                error -> {

                }
        ));
    }


    @Override
    public void onBackClick() {
        if (getView() == null) return;
        getView().openLogin();

    }
}
