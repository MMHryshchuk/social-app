package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.ui.screens.support.SupportActivity;
import com.clearsoft.welivre.ui.screens.support.SupportPresenter;
import com.clearsoft.welivre.ui.screens.support.SupportPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 25.07.17.
 */
@Subcomponent(modules = SupportComponent.Module.class)
@PerActivity
public interface SupportComponent {

    void inject(SupportActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        SupportPresenter provideSupportPresenter(App app, FeedUseCase feedUseCase){
            return new SupportPresenterImpl(app, feedUseCase);
        }
    }
}
