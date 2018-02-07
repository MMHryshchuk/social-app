package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.ui.screens.auth.reset.ResetActivity;
import com.clearsoft.welivre.ui.screens.auth.reset.ResetPresenter;
import com.clearsoft.welivre.ui.screens.auth.reset.ResetPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 28.06.17.
 */
@Subcomponent(modules = ResetComponent.Module.class)
@PerActivity
public interface ResetComponent {

    void inject(ResetActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        ResetPresenter provideResetPresenter(App app, AuthUseCase authUseCase){
            return new ResetPresenterImpl(app, authUseCase);
        }
    }
}
