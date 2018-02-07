package com.clearsoft.welivre.ui.screens.more.smoking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.ui.screens.messenger.MessengerActivity;
import com.clearsoft.welivre.ui.screens.my_plan.MyPlanActivity;
import com.clearsoft.welivre.ui.screens.posting.PostingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 18.07.17.
 */

public class SmokingSecondActivity extends BaseActivity{

    public static void start(Activity activity){
        Intent intent = new Intent(activity, SmokingSecondActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoked_second);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.activity_smoked_chat_btn)
    public void onChatClick(){
        MessengerActivity.start(this);
    }
    @OnClick(R.id.activity_smoked_promise_btn)
    public void onPromiseClick(){
        PostingActivity.start(this);
    }
    @OnClick(R.id.activity_smoked_plan_btn)
    public void onPlanClick(){
        MyPlanActivity.start(this);
    }

}
