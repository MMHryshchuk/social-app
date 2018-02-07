package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.settings.SettingsActivity;
import com.clearsoft.welivre.ui.screens.settings.privacy.PrivacyPresenter;
import com.clearsoft.welivre.ui.screens.settings.privacy.PrivacyPresenterImpl;
import com.clearsoft.welivre.ui.screens.settings.profile.SettingsProfilePresenter;
import com.clearsoft.welivre.ui.screens.settings.profile.SettingsProfilePresenterImpl;
import com.clearsoft.welivre.ui.screens.settings.questionaries.QuestionariesPresenter;
import com.clearsoft.welivre.ui.screens.settings.questionaries.QuestionariesPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 25.07.17.
 */
@Subcomponent(modules = SettingsComponent.Module.class)
@PerActivity
public interface SettingsComponent {

    void inject(SettingsActivity activity);
    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        SettingsProfilePresenter provideSettingsProfilePresenter(App app, UserUseCase userUseCase){
            return new SettingsProfilePresenterImpl(app, userUseCase);
        }

        @Provides
        @PerActivity
        QuestionariesPresenter provideQuestionariesPresenter(App app, UserUseCase userUseCase){
            return new QuestionariesPresenterImpl(app, userUseCase);
        }

        @Provides
        @PerActivity
        PrivacyPresenter providePrivacyPresenter(){
            return new PrivacyPresenterImpl();
        }
    }
}
