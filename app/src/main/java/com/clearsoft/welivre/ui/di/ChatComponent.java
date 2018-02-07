package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.chat.ChatActivity;
import com.clearsoft.welivre.ui.screens.chat.ChatPresenter;
import com.clearsoft.welivre.ui.screens.chat.ChatPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 17.07.17.
 */
@Subcomponent(modules = ChatComponent.Module.class)
@PerActivity
public interface ChatComponent {

    void inject(ChatActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        ChatPresenter provideChatPresenter(){
            return new ChatPresenterImpl();
        }
    }
}
