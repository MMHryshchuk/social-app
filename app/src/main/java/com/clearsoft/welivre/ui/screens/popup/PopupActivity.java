package com.clearsoft.welivre.ui.screens.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 05.07.17.
 */

public class PopupActivity extends BaseActivity {

    public static final int REQUEST_MORE = 1972;
    public static final int RESULT_CHAT = 1973;
    public static final int REQUEST_COMMENT = 1974;
    public static final int RESULT_FOLLOW = 1975;
    public static final int RESULT_EDIT = 1976;
    public static final int RESULT_DELETE = 1977;

    private static final String REQUEST_TYPE = "REQUEST_TYPE";

    @BindView(R.id.activity_popup_icon_1)
    ImageView icon1;
    @BindView(R.id.activity_popup_icon_2)
    ImageView icon2;
    @BindView(R.id.activity_popup_txt_1)
    TextView text1;
    @BindView(R.id.activity_popup_txt_2)
    TextView text2;
    private int request;

    public static void startForResult(Activity activity,int request){
        Intent intent = new Intent(activity, PopupActivity.class);
        intent.putExtra(REQUEST_TYPE,request);
        activity.startActivityForResult(intent,request);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        ButterKnife.bind(this);
        request = getIntent().getIntExtra(REQUEST_TYPE,0);
        switch (request){
            case REQUEST_COMMENT:
                text1.setText("Edit");
                text2.setText("Delete");

        }

    }

    @OnClick(R.id.activity_popup_frame1)
    public void onChatClick(){
        switch (request){
            case REQUEST_COMMENT:
                setResult(RESULT_EDIT);
        }
        finish();
    }
    @OnClick(R.id.activity_popup_frame2)
    public void onFollowClick(){
        switch (request){
            case REQUEST_COMMENT:
                setResult(RESULT_DELETE);
        }
        finish();
    }
    @OnClick(R.id.activity_popup_free_frame)
    public void onFreeFrameClick(){
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
