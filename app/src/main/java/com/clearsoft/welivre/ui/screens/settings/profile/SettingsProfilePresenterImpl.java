package com.clearsoft.welivre.ui.screens.settings.profile;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.utils.BitmapHelper;
import com.clearsoft.welivre.core.utils.CachedFilesBank;
import com.clearsoft.welivre.core.utils.CachedFilesRepository;
import com.clearsoft.welivre.core.utils.PermissionHelper;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.screens.posting.PostingActivity;
import com.clearsoft.welivre.ui.screens.settings.SettingsActivity;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

/**
 * Created by on 25.07.17.
 */

public class SettingsProfilePresenterImpl extends BasePresenter<SettingsProfileView> implements SettingsProfilePresenter {

    App app;
    UserUseCase userUseCase;
    Uri uri;

    public SettingsProfilePresenterImpl(App app, UserUseCase userUseCase) {
        this.app = app;
        this.userUseCase = userUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(userUseCase.getUserProfileInfo().subscribe(
                next -> {
                    getView().setData(next);
                }
        ));
    }


    @Override
    public void checkCameraPermission(SettingsActivity settingsActivity) {
        String writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String cameraPermission = android.Manifest.permission.CAMERA;
        if (!PermissionHelper.isGrantedPermissions(settingsActivity,writePermission,cameraPermission)){
            PermissionHelper.showRequestPermissionPopup(settingsActivity,SettingsActivity.RESULT_CAMERA_PERMISSION,writePermission,cameraPermission);
        } else {
            onCameraChange(settingsActivity);
        }
    }

    @Override
    public void checkGalleryPermission(SettingsActivity settingsActivity) {
        String readPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (!PermissionHelper.isGrantedPermissions(settingsActivity,readPermission)){
            PermissionHelper.showRequestPermissionPopup(settingsActivity,SettingsActivity.RESULT_GALERY_PERMISSION,readPermission);
        } else {
            onGalleryChange(settingsActivity);
        }
    }

    @Override
    public void onGalleryChange(SettingsActivity settingsActivity) {
        Intent intent = new Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        settingsActivity.startActivityForResult(intent,SettingsActivity.RESULT_GALLERY_IMAGE);
    }

    @Override
    public void onCameraChange(SettingsActivity settingsActivity) {
        File file = CachedFilesBank.getInstance(settingsActivity).createImageCacheFile();
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        uri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(settingsActivity.getPackageManager()) != null) {
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            uri = settingsActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            settingsActivity.startActivityForResult(intent, SettingsActivity.RESULT_CAMERA_IMAGE);
        } else {
            Log.d("loEror", "onCameraChange: DDDeerrror");
        }
    }

    @Override
    public void update(Uri galleryUri) {
        if (getView() == null) return;
        if (uri == null){
            uri = galleryUri;
            getView().updateImg(uri);

        }else {
            getView().updateImg(uri);
        }
    }

    @Override
    public void cropImage(SettingsActivity activity, Uri uriToCrop) {
        if (uriToCrop != null) {
            uri = uriToCrop;
        }
        File file = CachedFilesBank.getInstance(activity).createImageCacheFile();
        UCrop.of(uri, Uri.fromFile(file))
                .withAspectRatio(1, 1)
                .withMaxResultSize(1080, 1080)
                .start(activity);
    }

    @Override
    public void updateUserImage(Uri uri) {
       /* if (getView() == null) return;
        addSubscription(userUseCase.uploadAvatar( BitmapHelper.getRealPathFromURI(app,uri)).subscribe(
                next -> {
                    getView().updateImg(next);
                }
        ));*/
       this.uri = uri;
        getView().updateImg(this.uri);
    }

    @Override
    public void saveUserInfo(String name, String email, String password, String about) {
        if (getView() == null) return;
        getView().showProgress();
        addSubscription(userUseCase.updateUser(name,email,password,about, BitmapHelper.getRealPathFromURI(app,uri)).subscribe(
                next -> {
                    getView().hideProgress();
                    uri = null;
                    getView().setData(next);
                    getView().updatePassword(password);
                }
        ));
    }
}
