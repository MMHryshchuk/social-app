package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.more.smoking.situation.SmokingSituationActivity;
import com.clearsoft.welivre.ui.screens.more.smoking.situation.SmokingSituationPresenter;
import com.clearsoft.welivre.ui.screens.more.smoking.situation.SmokingSituationPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 18.07.17.
 */

@Subcomponent(modules = SmokingSituationComponent.Module.class)
@PerActivity
public interface SmokingSituationComponent {

    void inject(SmokingSituationActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        SmokingSituationPresenter provideSmokingPresenter(App app, UserUseCase userUseCase){
            return new SmokingSituationPresenterImpl(app, userUseCase);
        }
    }
}
