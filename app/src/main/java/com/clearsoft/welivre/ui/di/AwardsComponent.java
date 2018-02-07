package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.awards.AwardsActivity;
import com.clearsoft.welivre.ui.screens.awards.AwardsPresenter;
import com.clearsoft.welivre.ui.screens.awards.AwardsPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 02.07.17.
 */
@Subcomponent(modules = AwardsComponent.Module.class)
@PerActivity
public interface AwardsComponent {

    void inject(AwardsActivity activity);

    @dagger.Module
    class Module{


        @Provides
        @PerActivity
        AwardsPresenter provideAwardsPresenter(){
            return new AwardsPresenterImpl();
        }
    }
}
