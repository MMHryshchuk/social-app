package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.ui.screens.auth.register.RegisterActivity;
import com.clearsoft.welivre.ui.screens.auth.register.RegisterPresenter;
import com.clearsoft.welivre.ui.screens.auth.register.RegisterPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 28.06.17.
 */
@Subcomponent(modules = RegisterComponent.Module.class)
@PerActivity
public interface RegisterComponent {

    void inject(RegisterActivity activity);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        RegisterPresenter provideRegisterPresenter(App app, AuthUseCase authUseCase) {
            return new RegisterPresenterImpl(app, authUseCase);
        }
    }
}
