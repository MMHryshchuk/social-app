package com.clearsoft.welivre.ui.screens.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.ui.di.ChatComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by on 17.07.17.
 */

public class ChatActivity extends BaseActivity implements ChatView{



    @Inject
    ChatPresenter mChatPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, ChatActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mChatPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mChatPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new ChatComponent.Module())
                .inject(this);
    }

    private void initToolbar(){

    }
}
