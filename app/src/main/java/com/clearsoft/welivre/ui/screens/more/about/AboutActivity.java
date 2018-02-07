package com.clearsoft.welivre.ui.screens.more.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.android.PanelActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 18.08.17.
 */

public class AboutActivity extends BaseActivity{

    public static void start(Activity activity){
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_about);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.toolbar_more_back_btn)
    public void onBackClick(){
        finish();
    }

}
