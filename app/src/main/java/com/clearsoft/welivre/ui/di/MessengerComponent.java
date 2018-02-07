package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.messenger.MessengerActivity;
import com.clearsoft.welivre.ui.screens.messenger.chats.ChatListPresenter;
import com.clearsoft.welivre.ui.screens.messenger.chats.ChatListPresenterImpl;
import com.clearsoft.welivre.ui.screens.messenger.contacts.ContactPresenterImpl;
import com.clearsoft.welivre.ui.screens.messenger.contacts.ContactsPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 03.07.17.
 */
@Subcomponent(modules = MessengerComponent.Module.class)
@PerActivity
public interface MessengerComponent {

   void inject(MessengerActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        ChatListPresenter provideChatListPresenter(){
            return new ChatListPresenterImpl();
        }

        @Provides
        @PerActivity
        ContactsPresenter provideContectsPresenter(){
            return new ContactPresenterImpl();
        }
    }
}
