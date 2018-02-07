package com.clearsoft.welivre.ui.screens.posting;

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
import com.clearsoft.welivre.ui.di.PostingComponent;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.yalantis.ucrop.UCrop;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 12.07.17.
 */

public class PostingActivity extends BaseActivity implements PostingView {

    public static int RESULT_CAMERA_PERMISSION = 1333;
    public static int RESULT_CAMERA_IMAGE = 3;


    @BindView(R.id.activit_posting_progress_frame)
    FrameLayout vProgressFrame;
    @BindView(R.id.activity_posting_post_txt)
    EditText vPostTxt;
    @BindView(R.id.activity_posting_sos_btn)
    LinearLayout vSosLay;
    @BindView(R.id.activity_posting_image_frame)
    FrameLayout vImageFrame;
    @BindView(R.id.activity_posting_image_post)
    ImageView vImagePost;
    @BindView(R.id.activity_posting_user_name)
    TextView vAuthorName;
    @BindView(R.id.activity_posting_user_img)
    ImageView vAuthorImage;

    @Inject
    PostingPresenter mPostingPresenter;

    private StorageReference mStorage;
    private Uri url;
    private boolean sos = false;

    PreferenceRepository preferenceRepository;
    public static void start(Activity activity){
        Intent intent = new Intent(activity, PostingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);
        ButterKnife.bind(this);
        setupDagger();
        mStorage = FirebaseStorage.getInstance().getReference();
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
        vAuthorName.setText(preferenceRepository.getUserName());
        ImageLoader.load(vAuthorImage,preferenceRepository.getUserImage(),true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPostingPresenter.attachView(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mPostingPresenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CAMERA_IMAGE && resultCode == RESULT_OK ){
            mPostingPresenter.cropImage(this);
        }
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            url = UCrop.getOutput(data);
            setImageToPost(url);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RESULT_CAMERA_PERMISSION){
            String writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
            String cameraPermission = android.Manifest.permission.CAMERA;
            if (PermissionHelper.isGrantedPermissions(this,writePermission,cameraPermission)){
                mPostingPresenter.onCameraChange(this);
            }
        }
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new PostingComponent.Module())
                .inject(this);
    }
    private void setImageToPost(Uri path){
        vImageFrame.setVisibility(View.VISIBLE);
//        ImageLoader.load(vImagePost,path);
        vImagePost.setImageURI(path);
    }



    @Override
    public void closePostingView() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        finish();
    }

    @Override
    public void showImagePreview() {

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

    @Override
    public void removeImage() {
        vImageFrame.setVisibility(View.GONE);

    }


    @OnClick(R.id.activity_posting_camera)
    public void onCameraClick(){
        mPostingPresenter.checkCameraPermission(this);

    }

    @OnClick(R.id.activity_image_delete_image_post)
    public void onDeleteImageCLick(){
        mPostingPresenter.onRemoveClick();
    }

    @OnClick(R.id.activity_posting_post_btn)
    public void onPostClick(){
        String postTxt = vPostTxt.getText().toString();
        if (!StringUtils.isNullEmpty(postTxt)) {
            mPostingPresenter.onPostClick(postTxt,sos,url);

        }
    }
    @OnClick(R.id.activit_posting_close_btn)
    public void onCloseClick(){
        mPostingPresenter.onCloseClick();
    }

    @OnClick(R.id.activity_posting_sos_btn)
    public void onSosClick(){
        mPostingPresenter.onSosClick();
    }


}
