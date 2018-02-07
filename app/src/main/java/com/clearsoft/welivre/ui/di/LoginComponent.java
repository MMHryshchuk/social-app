package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.ui.screens.auth.login.LoginActivity;
import com.clearsoft.welivre.ui.screens.auth.login.LoginPresenter;
import com.clearsoft.welivre.ui.screens.auth.login.LoginPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 28.06.17.
 */
@Subcomponent(modules = LoginComponent.Module.class)
@PerActivity
public interface LoginComponent {

    void inject(LoginActivity activity);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        LoginPresenter provideLoginPresenter(App app, AuthUseCase authUseCase) {
            return new LoginPresenterImpl(app, authUseCase);
        }
    }
}
