package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.video_help.VideoHelpActivity;
import com.clearsoft.welivre.ui.screens.video_help.VideoHelpPresenter;
import com.clearsoft.welivre.ui.screens.video_help.VideoHelpPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 25.07.17.
 */
@Subcomponent(modules = VideoHelpComponent.Module.class)
@PerActivity
public interface VideoHelpComponent {

    void inject(VideoHelpActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        VideoHelpPresenter provideVideoHelpPresenter(){
            return new VideoHelpPresenterImpl();
        }
    }
}
