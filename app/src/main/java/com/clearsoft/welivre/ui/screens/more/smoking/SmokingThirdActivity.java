package com.clearsoft.welivre.ui.screens.more.smoking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 18.07.17.
 */

public class SmokingThirdActivity extends BaseActivity {


    public static void start(Activity activity){
        Intent intent = new Intent(activity, SmokingThirdActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoked_third);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.activity_smoked_third_plan_btn)
    public void onRestartPlanClick(){

    }
}
