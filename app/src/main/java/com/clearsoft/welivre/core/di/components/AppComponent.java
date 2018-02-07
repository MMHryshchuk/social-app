package com.clearsoft.welivre.core.di.components;

import com.clearsoft.welivre.core.bus.Bus;
import com.clearsoft.welivre.core.di.modules.DataModule;
import com.clearsoft.welivre.core.di.modules.RepositoriesModule;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.modules.ApiModule;
import com.clearsoft.welivre.core.di.modules.AppModule;
import com.clearsoft.welivre.core.di.modules.MappersModule;
import com.clearsoft.welivre.core.di.modules.ThreadExecutorsModule;
import com.clearsoft.welivre.core.di.modules.UseCaseModule;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.di.ArticleComponent;
import com.clearsoft.welivre.ui.di.AwardsComponent;
import com.clearsoft.welivre.ui.di.ChatComponent;
import com.clearsoft.welivre.ui.di.CommentComponent;
import com.clearsoft.welivre.ui.di.CompleteRegisterComponent;
import com.clearsoft.welivre.ui.di.EditAboutComponent;
import com.clearsoft.welivre.ui.di.FavoriteComponent;
import com.clearsoft.welivre.ui.di.HealthComponent;
import com.clearsoft.welivre.ui.di.HomeComponent;
import com.clearsoft.welivre.ui.di.LoginComponent;
import com.clearsoft.welivre.ui.di.MessengerComponent;
import com.clearsoft.welivre.ui.di.MoreComponent;
import com.clearsoft.welivre.ui.di.MotivationComponent;
import com.clearsoft.welivre.ui.di.MyPlanComponent;
import com.clearsoft.welivre.ui.di.MyTrainingComponent;
import com.clearsoft.welivre.ui.di.PlanComponent;
import com.clearsoft.welivre.ui.di.PostingComponent;
import com.clearsoft.welivre.ui.di.ProfileComponent;
import com.clearsoft.welivre.ui.di.RegisterComponent;
import com.clearsoft.welivre.ui.di.ResetComponent;
import com.clearsoft.welivre.ui.di.SettingsComponent;
import com.clearsoft.welivre.ui.di.SmokingSituationComponent;
import com.clearsoft.welivre.ui.di.SplashComponent;
import com.clearsoft.welivre.ui.di.SupportComponent;
import com.clearsoft.welivre.ui.di.TrainingComponent;
import com.clearsoft.welivre.ui.di.VideoHelpComponent;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AppModule.class,
        DataModule.class,
        ThreadExecutorsModule.class,
        ApiModule.class,
        UseCaseModule.class,
        RepositoriesModule.class,
        MappersModule.class
})
public interface AppComponent {

    SplashComponent plus(SplashComponent.Module module);
    LoginComponent plus(LoginComponent.Module module);
    RegisterComponent plus(RegisterComponent.Module module);
    ResetComponent plus(ResetComponent.Module module);
    CompleteRegisterComponent plus(CompleteRegisterComponent.Module module);
    HomeComponent plus(HomeComponent.Module module);
    ProfileComponent plus(ProfileComponent.Module module);
    ArticleComponent plus(ArticleComponent.Module module);
    AwardsComponent plus(AwardsComponent.Module module);
    MessengerComponent plus(MessengerComponent.Module module);
    FavoriteComponent plus(FavoriteComponent.Module module);
    MoreComponent plus(MoreComponent.Module module);
    HealthComponent plus(HealthComponent.Module module);
    PostingComponent plus(PostingComponent.Module module);
    CommentComponent plus(CommentComponent.Module module);
    ChatComponent plus(ChatComponent.Module module);
    SmokingSituationComponent plus(SmokingSituationComponent.Module module);
    MotivationComponent plus(MotivationComponent.Module module);
    MyTrainingComponent plus(MyTrainingComponent.Module module);
    MyPlanComponent plus(MyPlanComponent.Module module);
    PlanComponent plus(PlanComponent.Module module);
    VideoHelpComponent plus(VideoHelpComponent.Module module);
    SupportComponent plus(SupportComponent.Module module);
    TrainingComponent plus(TrainingComponent.Module module);
    SettingsComponent plus(SettingsComponent.Module module);
    EditAboutComponent plus(EditAboutComponent.Module module);


    App getApp();
    Bus getBus();
    PreferenceRepository getPreferenceRepository();


}
