package com.clearsoft.welivre.ui.screens.settings.profile;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.posting.PostingActivity;
import com.clearsoft.welivre.ui.screens.settings.SettingsActivity;

/**
 * Created by on 25.07.17.
 */

public interface SettingsProfilePresenter extends Presenter<SettingsProfileView>{

    void checkCameraPermission(SettingsActivity settingsActivity);
    void checkGalleryPermission(SettingsActivity settingsActivity);
    void onGalleryChange(SettingsActivity settingsActivity);
    void onCameraChange(SettingsActivity settingsActivity);

    void update(Uri uri);
    void cropImage(SettingsActivity activity,@Nullable Uri uri);
    void updateUserImage(Uri uri);

    void saveUserInfo(String name, String email, String password, String about);


}
