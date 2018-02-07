package com.clearsoft.welivre.ui.screens.support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.core.utils.PermissionHelper;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.di.SupportComponent;
import com.clearsoft.welivre.ui.screens.posting.PostingPresenter;
import com.yalantis.ucrop.UCrop;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 25.07.17.
 */

public class SupportActivity extends BaseActivity implements SupportView {

    public static int RESULT_CAMERA_PERMISSION = 1333;
    public static int RESULT_CAMERA_IMAGE = 3;


    @BindView(R.id.activity_request_support_progress_frame)
    FrameLayout vProgressFrame;
    @BindView(R.id.activity_request_support_post_txt)
    EditText vPostTxt;
    @BindView(R.id.activity_request_support_sos_btn)
    LinearLayout vSosLay;
    @BindView(R.id.activity_request_support_img_frame)
    FrameLayout vImageFrame;
    @BindView(R.id.activity_request_support_post_img)
    ImageView vImagePost;
    @BindView(R.id.activity_request_support_user_name)
    TextView vAuthorName;
    @BindView(R.id.activity_request_support_user_img)
    ImageView vAuthorImage;


    private Uri url;
    private boolean sos = false;
    @Inject
    PreferenceRepository preferenceRepository;

    @Inject
    SupportPresenter mSupportPresenter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SupportActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_support);
        ButterKnife.bind(this);
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
                vSosLay.setActivated(true);
        setupDagger();
        vAuthorName.setText(preferenceRepository.getUserName());
        ImageLoader.load(vAuthorImage, preferenceRepository.getUserImage(),true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSupportPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSupportPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new SupportComponent.Module())
                .inject(this);
    }

    private void setImageToPost(String path) {
        vImageFrame.setVisibility(View.VISIBLE);
        ImageLoader.load(vImagePost, path,false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CAMERA_IMAGE && resultCode == RESULT_OK) {
            mSupportPresenter.cropImage(this);
        }
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            url = UCrop.getOutput(data);
            setImageToPost(url.getPath());
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RESULT_CAMERA_PERMISSION) {
            String writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
            String cameraPermission = android.Manifest.permission.CAMERA;
            if (PermissionHelper.isGrantedPermissions(this, writePermission, cameraPermission)) {
                mSupportPresenter.onCameraChange(this);
            }
        }
    }


    @Override
    public void closePostingView() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        finish();
    }

    @Override
    public void showImagePreview() {
        vImageFrame.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        vProgressFrame.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgressFrame.setVisibility(View.GONE);
    }

    @Override
    public void setSosActivated() {
        vSosLay.setActivated(!vSosLay.isActivated());
        sos = vSosLay.isActivated();
    }

    @OnClick(R.id.activity_request_support_camera_btn)
    public void onCameraClick() {
        mSupportPresenter.checkCameraPermission(this);
    }

    @OnClick(R.id.activity_request_support_delete_img)
    public void onDeleteImageCLick() {
        vImageFrame.setVisibility(View.GONE);
    }

    @OnClick(R.id.activity_request_support_user_post_btn)
    public void onPostClick() {
        String postTxt = vPostTxt.getText().toString();
        if (!StringUtils.isNullEmpty(postTxt)) {
            mSupportPresenter.onPostClick(postTxt, sos, url);
        }
    }

    @OnClick(R.id.activity_request_support_close)
    public void onCloseClick() {
        mSupportPresenter.onCloseClick();
    }

    @OnClick(R.id.activity_request_support_delete_img)
    public void deleteImage(){
        vImageFrame.setVisibility(View.GONE);
    }

    @OnClick(R.id.activity_request_support_sos_btn)
    public void onSosClick() {
//        mSupportPresenter.onSosClick();
    }
}
