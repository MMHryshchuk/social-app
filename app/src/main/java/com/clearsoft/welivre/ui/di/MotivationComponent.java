package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.motivation.MotivationActivity;
import com.clearsoft.welivre.ui.screens.motivation.MotivationPresenter;
import com.clearsoft.welivre.ui.screens.motivation.MotivationPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 18.07.17.
 */
@Subcomponent(modules = MotivationComponent.Module.class)
@PerActivity
public interface MotivationComponent {

    void inject(MotivationActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        MotivationPresenter provideMotivationPresenter(){
            return new MotivationPresenterImpl();
        }
    }
}
