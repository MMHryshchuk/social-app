package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.my_training.MyTrainingActivity;
import com.clearsoft.welivre.ui.screens.my_training.MyTrainingPresenter;
import com.clearsoft.welivre.ui.screens.my_training.MyTrainingPresenterImpl;

import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 18.07.17.
 */

@Subcomponent(modules = MyTrainingComponent.Module.class)
@PerActivity
public interface MyTrainingComponent {

    void inject(MyTrainingActivity activity);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        MyTrainingPresenter provideTrainingPresenter() {
            return new MyTrainingPresenterImpl();
        }
    }
}
