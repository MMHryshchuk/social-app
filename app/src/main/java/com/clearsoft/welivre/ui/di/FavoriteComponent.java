package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.ui.screens.favorite.FavoriteActivity;
import com.clearsoft.welivre.ui.screens.favorite.FavoritePresenter;
import com.clearsoft.welivre.ui.screens.favorite.FavoritePresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 05.07.17.
 */
@Subcomponent(modules = FavoriteComponent.Module.class)
@PerActivity
public interface FavoriteComponent {


    void inject(FavoriteActivity activity);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        FavoritePresenter provideFavoritePresenter(App app, FeedUseCase feedUseCase) {
            return new FavoritePresenterImpl(app, feedUseCase);
        }


    }
}