package com.clearsoft.welivre.ui.screens.support;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.utils.BitmapHelper;
import com.clearsoft.welivre.core.utils.CachedFilesBank;
import com.clearsoft.welivre.core.utils.LanguageUtils;
import com.clearsoft.welivre.core.utils.PermissionHelper;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.ui.screens.posting.PostingActivity;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

import static android.R.attr.maxHeight;
import static android.R.attr.maxWidth;

/**
 * Created by on 25.07.17.
 */

public class SupportPresenterImpl extends BasePresenter<SupportView> implements SupportPresenter {

    App app;
    FeedUseCase feedUseCase;
    private Uri uri;


    public SupportPresenterImpl(App app, FeedUseCase feedUseCase) {
        this.app = app;
        this.feedUseCase = feedUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }




    @Override
    public void checkCameraPermission(SupportActivity activity) {
        String writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String cameraPermission = android.Manifest.permission.CAMERA;
        if (!PermissionHelper.isGrantedPermissions(activity, writePermission, cameraPermission)) {
            PermissionHelper.showRequestPermissionPopup(activity, PostingActivity.RESULT_CAMERA_PERMISSION, writePermission, cameraPermission);
        } else {
            onCameraChange(activity);
        }
    }

    @Override
    public void onCameraChange(SupportActivity activity) {
        File file = CachedFilesBank.getInstance(activity).createImageCacheFile();
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = Uri.fromFile(file);
        this.uri = uri;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        activity.startActivityForResult(cameraIntent, PostingActivity.RESULT_CAMERA_IMAGE);
    }

    @Override
    public void onPostClick(String postTxt, boolean sos, Uri img) {
        if (getView() == null) return;
        getView().showProgress();

        addSubscription(feedUseCase.newPost(postTxt, true, BitmapHelper.getRealPathFromURI(app,uri), LanguageUtils.getLang(app)).subscribe(
                next -> {
                    getView().hideProgress();
                    getView().closePostingView();
                },
                error -> {
                    getView().hideProgress();

                }
        ));
    }

    @Override
    public void cropImage(SupportActivity activity) {
        File file = CachedFilesBank.getInstance(activity).createImageCacheFile();
        UCrop.of(uri, Uri.fromFile(file))
                .withAspectRatio(1, 1)
                .withMaxResultSize(maxWidth, maxHeight)
                .start(activity);
    }

    @Override
    public void onCloseClick() {
        if (getView() == null) return;
        getView().closePostingView();
    }

    @Override
    public void onSosClick() {
        if (getView() == null) return;
        getView().setSosActivated();
    }
}
