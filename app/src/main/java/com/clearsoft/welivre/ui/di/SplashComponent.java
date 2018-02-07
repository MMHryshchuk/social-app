package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.splash.SplashActivity;
import com.clearsoft.welivre.ui.screens.splash.SplashPresenter;
import com.clearsoft.welivre.ui.screens.splash.SplashPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 27.06.17.
 */
@PerActivity
@Subcomponent(modules = SplashComponent.Module.class)
public interface SplashComponent {

    void inject(SplashActivity activity);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        SplashPresenter provideSplashpresenter(App app) {
            return new SplashPresenterImpl(app);
        }
    }

}
