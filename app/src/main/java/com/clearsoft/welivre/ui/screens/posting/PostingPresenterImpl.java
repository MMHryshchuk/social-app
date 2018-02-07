package com.clearsoft.welivre.ui.screens.posting;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.utils.BitmapHelper;
import com.clearsoft.welivre.core.utils.CachedFilesBank;
import com.clearsoft.welivre.core.utils.CachedFilesRepository;
import com.clearsoft.welivre.core.utils.LanguageUtils;
import com.clearsoft.welivre.core.utils.PermissionHelper;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.domain.use_cases.UploadUseCase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

import static android.R.attr.maxHeight;
import static android.R.attr.maxWidth;

/**
 * Created by on 12.07.17.
 */

public class PostingPresenterImpl extends BasePresenter<PostingView> implements PostingPresenter {

    App app;
    FeedUseCase feedUseCase;
    UploadUseCase uploadUseCase;

    private Uri uri;


    public PostingPresenterImpl(App app, FeedUseCase feedUseCase, UploadUseCase uploadUseCase) {
        this.app = app;
        this.feedUseCase = feedUseCase;
        this.uploadUseCase = uploadUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void checkCameraPermission(PostingActivity activity) {
        String writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String cameraPermission = android.Manifest.permission.CAMERA;
        if (!PermissionHelper.isGrantedPermissions(activity, writePermission, cameraPermission)) {
            PermissionHelper.showRequestPermissionPopup(activity, PostingActivity.RESULT_CAMERA_PERMISSION, writePermission, cameraPermission);
        } else {
            onCameraChange(activity);
        }
    }

    @Override
    public void onCameraChange(PostingActivity activity) {
        File file = CachedFilesBank.getInstance(activity).createImageCacheFile();
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        uri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            uri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            activity.startActivityForResult(intent, PostingActivity.RESULT_CAMERA_IMAGE);
        } else {
            Log.d("loEror", "onCameraChange: DDDeerrror");
        }
    }

    @Override
    public void onPostClick(String postTxt, boolean sos, Uri img) {
        if (getView() == null) return;
        getView().showProgress();
        addSubscription(feedUseCase.newPost(postTxt,sos,BitmapHelper.getRealPathFromURI(app,uri), LanguageUtils.getLang(app)).subscribe(
                next ->{
                    getView().hideProgress();
                    getView().closePostingView();
                },
                error -> {
                    getView().hideProgress();
                    Log.d("Ornono", error.toString());

                }
        ));
    }


    @Override
    public void cropImage(PostingActivity activity) {
        File file = CachedFilesBank.getInstance(activity).createImageCacheFile();
        UCrop.of(uri, Uri.fromFile(file))
                .withAspectRatio(1, 1)
                .withMaxResultSize(1080, 1080)
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

    @Override
    public void onRemoveClick() {
        if (getView() == null) return;
        uri = null;
        getView().removeImage();
    }

    private void upload(String img){
        addSubscription(uploadUseCase.uploadPostImage(img).subscribe(s -> {
            getView().hideProgress();
            getView().closePostingView();
        }));
    }

    public File getTempFile() {
        File file = null;
        try {
            String fileName = "img" + System.currentTimeMillis()+ ".png";
            file = File.createTempFile(fileName, null, app.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }
}
