package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.bus.Bus;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.profile.edit_about.EditAboutActivity;
import com.clearsoft.welivre.ui.screens.profile.edit_about.EditAboutPresenter;
import com.clearsoft.welivre.ui.screens.profile.edit_about.EditAboutPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 11.08.17.
 */

@Subcomponent(modules = EditAboutComponent.Module.class)
@PerActivity
public interface EditAboutComponent {

    void inject(EditAboutActivity aboutActivity);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        EditAboutPresenter provideEditAboutPresenter(App app, UserUseCase userUseCase, Bus bus) {
            return new EditAboutPresenterImpl(app, userUseCase, bus);
        }
    }
}
