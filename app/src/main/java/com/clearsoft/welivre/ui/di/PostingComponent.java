package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.domain.use_cases.UploadUseCase;
import com.clearsoft.welivre.ui.screens.posting.PostingActivity;
import com.clearsoft.welivre.ui.screens.posting.PostingPresenter;
import com.clearsoft.welivre.ui.screens.posting.PostingPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 12.07.17.
 */
@Subcomponent(modules = PostingComponent.Module.class)
@PerActivity
public interface PostingComponent {

    void inject(PostingActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        PostingPresenter providePostingPresenter(App app, FeedUseCase feedUseCase,UploadUseCase uploadUseCase){
            return new PostingPresenterImpl(app, feedUseCase,uploadUseCase);
        }
    }
}
