package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.training.TrainingActivity;
import com.clearsoft.welivre.ui.screens.training.TrainingPresenter;
import com.clearsoft.welivre.ui.screens.training.TrainingPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 25.07.17.
 */
@Subcomponent(modules = TrainingComponent.Module.class)
@PerActivity
public interface TrainingComponent {

    void inject(TrainingActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        TrainingPresenter provideTrainingPresenter(){
            return new TrainingPresenterImpl();
        }
    }
}
