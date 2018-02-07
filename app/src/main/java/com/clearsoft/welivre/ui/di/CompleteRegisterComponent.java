package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;
import com.clearsoft.welivre.ui.screens.auth.complete.first.CompleteFirstPresenter;
import com.clearsoft.welivre.ui.screens.auth.complete.first.CompleteFirstPresenterImpl;
import com.clearsoft.welivre.ui.screens.auth.complete.second.CompleteSecondPresenter;
import com.clearsoft.welivre.ui.screens.auth.complete.second.CompleteSecondPresenterImpl;
import com.clearsoft.welivre.ui.screens.auth.complete.third.CompleteThirdPresenter;
import com.clearsoft.welivre.ui.screens.auth.complete.third.CompleteThirdPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 28.06.17.
 */
@PerActivity
@Subcomponent(modules = CompleteRegisterComponent.Module.class)
public interface CompleteRegisterComponent {

    void inject(CompleteRegisterActivity activity);


    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        CompleteFirstPresenter provideFirstPresenter() {
            return new CompleteFirstPresenterImpl();
        }

        @Provides
        @PerActivity
        CompleteSecondPresenter provideSecondPresenter() {
            return new CompleteSecondPresenterImpl();
        }

        @Provides
        @PerActivity
        CompleteThirdPresenter provideThirdPresenter(App app, UserUseCase userUseCase) {
            return new CompleteThirdPresenterImpl(app, userUseCase);
        }
    }
}
