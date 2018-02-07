package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.comment.CommentActivity;
import com.clearsoft.welivre.ui.screens.comment.CommentPresenter;
import com.clearsoft.welivre.ui.screens.comment.CommentPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 15.07.17.
 */

@Subcomponent(modules = CommentComponent.Module.class)
@PerActivity
public interface CommentComponent {

    void inject(CommentActivity activity);

    @dagger.Module
    class Module {

        private final int postId;
        private final boolean isAbout;

        public Module(int postId, boolean isAbout) {
            this.postId = postId;
            this.isAbout = isAbout;
        }

        @Provides
        @PerActivity
        CommentPresenter provideCommentPresenter(
                App app,
                FeedUseCase feedUseCase,
                UserUseCase userUseCase
        ) {
            return new CommentPresenterImpl(app, feedUseCase, userUseCase, postId, isAbout);
        }
    }
}
