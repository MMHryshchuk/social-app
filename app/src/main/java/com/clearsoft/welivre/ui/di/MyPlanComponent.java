package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.screens.my_plan.MyPlanActivity;
import com.clearsoft.welivre.ui.screens.my_plan.MyPlanPresenter;
import com.clearsoft.welivre.ui.screens.my_plan.MyPlanPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 18.07.17.
 */
@Subcomponent(modules = MyPlanComponent.Module.class)
@PerActivity
public interface MyPlanComponent {

    void inject(MyPlanActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        MyPlanPresenter provideMyPlanPresenter(App app, PlanUseCase planUseCase){
            return new MyPlanPresenterImpl(app, planUseCase);
        }
    }
}
