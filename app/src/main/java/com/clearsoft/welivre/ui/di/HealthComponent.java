package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.screens.health.HealthActivity;
import com.clearsoft.welivre.ui.screens.health.HealthPresenter;
import com.clearsoft.welivre.ui.screens.health.HealthPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 06.07.17.
 */

@Subcomponent(modules = HealthComponent.Module.class)
@PerActivity
public interface HealthComponent {

    void inject(HealthActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        HealthPresenter provideHealthPresenter(App app, PlanUseCase planUseCase){
            return new HealthPresenterImpl(app, planUseCase);
        }
    }
}
