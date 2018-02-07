package com.clearsoft.welivre.ui.screens.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.android.Optional;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.MessengerComponent;
import com.clearsoft.welivre.ui.screens.messenger.chats.ChatListFragment;
import com.clearsoft.welivre.ui.screens.messenger.chats.ChatListPresenter;
import com.clearsoft.welivre.ui.screens.messenger.contacts.ContactsFragment;
import com.clearsoft.welivre.ui.screens.messenger.contacts.ContactsPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 03.07.17.
 */

public class MessengerActivity extends PanelActivity {

    public static final int FRAGMENT_CHAT = 112;
    public static final int FRAGMENT_CONTACT = 113;
    public static final String EXTRA_FRAGMENT = "EXTRA_FRAGMENT";

    @BindView(R.id.activity_messenger_chat_txt)
    TextView vChatTxt;
    @BindView(R.id.activity_messenger_contact_txt)
    TextView vContactTxt;
    @BindView(R.id.activity_messenger_chat_divider)
    View vChatDivider;
    @BindView(R.id.activity_messenger_contact_divider)
    View vContactDivider;

    @Inject
    ChatListPresenter mChatPresenter;
    @Inject
    ContactsPresenter mContactsPresenter;
    private int currentFragment = 0;

    public static void start(Activity activity){
        Intent intent = new Intent(activity,MessengerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);
        setupDagger();

        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(EXTRA_FRAGMENT,-1) == FRAGMENT_CHAT) {
                replaceFragment(mChatPresenter,new ChatListFragment());
                setChatBtnActive();
                currentFragment = FRAGMENT_CHAT;

            } else if (savedInstanceState.getInt(EXTRA_FRAGMENT,-1) == FRAGMENT_CONTACT) {
                replaceFragment(mContactsPresenter, new ContactsFragment());
                setContactBtnActive();
                currentFragment = FRAGMENT_CONTACT;
            }

        }else {
            replaceFragment(mChatPresenter,new ChatListFragment());
            currentFragment = FRAGMENT_CHAT;

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Optional<ChatListFragment> chatFragmentOptional = findFragment(ChatListFragment.class);
        Optional<ContactsFragment> contacFragmentOptional = findFragment(ContactsFragment.class);
        if (chatFragmentOptional.isFragment()) {
            currentFragment = FRAGMENT_CHAT;
        } else if (contacFragmentOptional.isFragment()) {
            currentFragment = FRAGMENT_CONTACT;
        }
        outState.putInt(EXTRA_FRAGMENT, currentFragment);
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new MessengerComponent.Module())
                .inject(this);
    }

    @OnClick(R.id.activity_messenger_chat_btn)
    public void onChatClick(){
        if (currentFragment == FRAGMENT_CHAT)return;
        replaceFragment(mChatPresenter,new ChatListFragment());
        setChatBtnActive();
        currentFragment = FRAGMENT_CHAT;
    }

    @OnClick(R.id.activity_messenger_contact_btn)
    public void onContactClick(){
        if (currentFragment == FRAGMENT_CONTACT)return;
        replaceFragment(mContactsPresenter,new ContactsFragment());
        setContactBtnActive();
        currentFragment = FRAGMENT_CONTACT;
    }

    private void setChatBtnActive(){
//        vChatTxt.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
//        vContactTxt.setTextColor(getResources().getColor(R.color.colorTextSecondary));
        vChatDivider.setVisibility(View.VISIBLE);
        vContactDivider.setVisibility(View.GONE);
    }

    private void setContactBtnActive(){
//        vChatTxt.setTextColor(getResources().getColor(R.color.colorTextSecondary));
//        vContactTxt.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        vContactDivider.setVisibility(View.VISIBLE);
        vChatDivider.setVisibility(View.GONE);
    }
}
