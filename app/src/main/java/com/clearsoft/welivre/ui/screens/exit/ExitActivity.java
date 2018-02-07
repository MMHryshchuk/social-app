package com.clearsoft.welivre.ui.screens.exit;

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

public class ExitActivity extends BaseActivity{

    public static final int REQUEST_EXIT = 887;
    public static final int RESULT_POSITIVE = 888;
    public static final int RESULT_NEGATIVE = 889;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, ExitActivity.class);
        activity.startActivityForResult(intent,REQUEST_EXIT);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_exit_positive_btn)
    public void onPositiveClick(){
        setResult(RESULT_POSITIVE);
        finish();
    }

    @OnClick(R.id.activity_exit_negative_btn)
    public void onNegativeClick(){
        setResult(RESULT_NEGATIVE);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
