package com.clearsoft.welivre.ui.screens.settings.profile;

import android.net.Uri;

import com.clearsoft.welivre.ui.dvo.SettingsProfileDvo;

/**
 * Created by on 25.07.17.
 */

public interface SettingsProfileView {

    void setData(SettingsProfileDvo dvo);
    void updateImg(Uri path);
    void showProgress();
    void hideProgress();
    void updatePassword(String password);
}
