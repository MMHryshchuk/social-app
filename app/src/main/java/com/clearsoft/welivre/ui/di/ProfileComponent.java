package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.profile.ProfileActivity;
import com.clearsoft.welivre.ui.screens.profile.ProfilePresenter;
import com.clearsoft.welivre.ui.screens.profile.ProfilePresenterImpl;
import com.clearsoft.welivre.ui.screens.profile.about.AboutPresenter;
import com.clearsoft.welivre.ui.screens.profile.about.AboutPresenterImpl;
import com.clearsoft.welivre.ui.screens.profile.followers.FollowersPresenter;
import com.clearsoft.welivre.ui.screens.profile.followers.FollowersPresenterImpl;
import com.clearsoft.welivre.ui.screens.profile.following.FollowingPresenter;
import com.clearsoft.welivre.ui.screens.profile.following.FollowingPresenterImpl;
import com.clearsoft.welivre.ui.screens.profile.posts.PostsPresenter;
import com.clearsoft.welivre.ui.screens.profile.posts.PostsPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 30.06.17.
 */
@Subcomponent(modules = ProfileComponent.Module.class)
@PerActivity
public interface ProfileComponent {

    void inject(ProfileActivity activity);

    @dagger.Module
    class Module{

        private final String userId;

        public Module(String userId) {
            this.userId = userId;
        }

        @Provides
        @PerActivity
        ProfilePresenter provideProfilePresenter(App app, UserUseCase userUseCase){
            return new ProfilePresenterImpl(app, userUseCase,userId);
        }

        @Provides
        @PerActivity
        PostsPresenter providePostsPresenter(App app, FeedUseCase feedUseCase){
            return new PostsPresenterImpl(app, feedUseCase, userId);
        }

        @Provides
        @PerActivity
        FollowersPresenter provideFollowersPresenter(App app, UserUseCase userUseCase){
            return new FollowersPresenterImpl(app, userUseCase,userId);
        }

        @Provides
        @PerActivity
        FollowingPresenter provideFollowingPresenter(App app, UserUseCase userUseCase){
            return new FollowingPresenterImpl(app, userUseCase,userId);
        }

        @Provides
        @PerActivity
        AboutPresenter provideAboutPresenter(App app, UserUseCase userUseCase, PlanUseCase planUseCase){
            return new AboutPresenterImpl(app, userUseCase, planUseCase,Integer.parseInt(userId));
        }
    }
}
