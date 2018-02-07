package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.ui.screens.home.HomeActivity;
import com.clearsoft.welivre.ui.screens.home.HomePresenter;
import com.clearsoft.welivre.ui.screens.home.HomePresenterImpl;

import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 29.06.17.
 */
@Subcomponent(modules = HomeComponent.Module.class)
@PerActivity
public interface HomeComponent {

    void inject(HomeActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        HomePresenter provideHomePresenter(App app, FeedUseCase feedUseCase){
            return new HomePresenterImpl(app, feedUseCase);
        }
    }
}
