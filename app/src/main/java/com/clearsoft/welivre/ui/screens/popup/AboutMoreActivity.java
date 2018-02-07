package com.clearsoft.welivre.ui.screens.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 12.08.17.
 */

public class AboutMoreActivity extends BaseActivity{

    public static final int REQUEST_MORE = 141;
    public static final int RESULT_EDIT = 143;


    public static void startForResult(Activity activity){
        Intent intent = new Intent(activity,AboutMoreActivity.class);
        activity.startActivityForResult(intent,REQUEST_MORE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_more);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_about_more_edit_lay)
    public void edit(){
        setResult(RESULT_EDIT);
        finish();
    }


}
