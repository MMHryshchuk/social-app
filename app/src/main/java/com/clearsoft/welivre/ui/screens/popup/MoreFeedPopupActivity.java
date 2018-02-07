package com.clearsoft.welivre.ui.screens.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 07.08.17.
 */

public class MoreFeedPopupActivity extends BaseActivity{

    public static final int REQUEST_MORE = 641;
    public static final int RESULT_HIDE = 642;
    public static final int RESULT_REPORT = 643;


    public static final String EXTRA_NAME = "EXTRA_NAME";

    @BindView(R.id.activity_popup_txt_1)
    TextView vText;

    public static void startForResult(Activity activity,String name){
        Intent intent = new Intent(activity,MoreFeedPopupActivity.class);
        intent.putExtra(EXTRA_NAME,name);
        activity.startActivityForResult(intent,REQUEST_MORE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_feed_popup);
        ButterKnife.bind(this);
        vText.setText("Hide all from " + getIntent().getStringExtra(EXTRA_NAME));
    }

    @OnClick(R.id.activity_more_feed_popup_hide)
    public void hide(){
        setResult(RESULT_HIDE);
        finish();
    }

    @OnClick(R.id.activity_more_feed_popup_report)
    public void report(){
        setResult(RESULT_REPORT);
        finish();
    }

    @OnClick(R.id.activity_popup_free_frame)
    public void closeClick(){
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
