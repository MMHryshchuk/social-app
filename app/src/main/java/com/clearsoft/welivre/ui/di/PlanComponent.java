package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;
import com.clearsoft.welivre.ui.screens.plan.p_assistences.PAssistancePresenter;
import com.clearsoft.welivre.ui.screens.plan.p_assistences.PAssistancePresenterImpl;
import com.clearsoft.welivre.ui.screens.plan.p_cravings.PCravingsPresenter;
import com.clearsoft.welivre.ui.screens.plan.p_cravings.PCravingsPresenterImpl;
import com.clearsoft.welivre.ui.screens.plan.p_date.PDatePresenter;
import com.clearsoft.welivre.ui.screens.plan.p_date.PDatePresenterImpl;
import com.clearsoft.welivre.ui.screens.plan.p_motivation.PMotivationPresenter;
import com.clearsoft.welivre.ui.screens.plan.p_motivation.PMotivationPresenterImpl;
import com.clearsoft.welivre.ui.screens.plan.p_personal_motivation.PPersonalMotivationPresenter;
import com.clearsoft.welivre.ui.screens.plan.p_personal_motivation.PPersonalMotivationPresenterImpl;
import com.clearsoft.welivre.ui.screens.plan.p_triggers.PTriggersPresenter;
import com.clearsoft.welivre.ui.screens.plan.p_triggers.PTriggersPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 19.07.17.
 */
@Subcomponent(modules = PlanComponent.Module.class)
@PerActivity
public interface PlanComponent {

    void inject(PlanActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        PDatePresenter providePDatePresenter(App app, PlanUseCase planUseCase){
            return new PDatePresenterImpl(app, planUseCase);
        }

        @Provides
        @PerActivity
        PMotivationPresenter providePMotivationPresenter(App app, PlanUseCase planUseCase){
            return new PMotivationPresenterImpl(app, planUseCase);
        }

        @Provides
        @PerActivity
        PTriggersPresenter providePTriggersPresenter(){
            return new PTriggersPresenterImpl();
        }

        @Provides
        @PerActivity
        PCravingsPresenter providePCravingsPresenter(){
            return new PCravingsPresenterImpl();
        }

        @Provides
        @PerActivity
        PAssistancePresenter providePAssistancePresener(App app, PlanUseCase planUseCase){
            return new PAssistancePresenterImpl(app, planUseCase);
        }

        @Provides
        @PerActivity
        PPersonalMotivationPresenter providePersonalPresenter(App app, PlanUseCase planUseCase){
            return new PPersonalMotivationPresenterImpl(app, planUseCase);
        }
    }
}
