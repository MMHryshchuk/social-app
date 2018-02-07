package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.more.MoreActivity;
import com.clearsoft.welivre.ui.screens.more.MorePresenter;
import com.clearsoft.welivre.ui.screens.more.MorePresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 05.07.17.
 */
@Subcomponent(modules = MoreComponent.Module.class)
@PerActivity
public interface MoreComponent {

    void inject(MoreActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        MorePresenter provideMorePresenter(App app, AuthUseCase authUseCase, UserUseCase userUseCase){
            return new MorePresenterImpl(app, authUseCase, userUseCase);
        }
    }
}
