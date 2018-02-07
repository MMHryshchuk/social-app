package com.clearsoft.welivre.ui.screens.support;

import android.net.Uri;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.posting.PostingActivity;

/**
 * Created by on 25.07.17.
 */

public interface SupportPresenter extends Presenter<SupportView>{

    void checkCameraPermission(SupportActivity activity);
    void onCameraChange(SupportActivity activity);
    void onPostClick(String  postTxt,boolean sos,Uri img);
    void cropImage(SupportActivity activity);
    void onCloseClick();
    void onSosClick();
}
