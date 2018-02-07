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
 * Created by on 15.08.17.
 */

public class ProfileImagePopupActivity extends BaseActivity{

    public static final int REQUEST_IMAGE = 291;
    public static final int RESULT_GALLERY = 292;
    public static final int RESULT_CAMERA = 293;


    public static void startForResult(Activity activity){
        Intent intent = new Intent(activity, ProfileImagePopupActivity.class);
        activity.startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_popup);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.activity_image_popup_camera)
    public void onCameraClick(){
        setResult(RESULT_CAMERA);
        finish();
    }

    @OnClick(R.id.activity_image_popup_gallery)
    public void onGalleryClick(){
        setResult(RESULT_GALLERY);
        finish();
    }





}
