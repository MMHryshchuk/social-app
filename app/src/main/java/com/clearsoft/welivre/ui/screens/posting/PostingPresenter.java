package com.clearsoft.welivre.ui.screens.posting;

import android.net.Uri;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 12.07.17.
 */

public interface PostingPresenter extends Presenter<PostingView> {

    void checkCameraPermission(PostingActivity activity);
    void onCameraChange(PostingActivity activity);
    void onPostClick(String  postTxt,boolean sos,Uri img);
    void cropImage(PostingActivity activity);
    void onCloseClick();
    void onSosClick();

    void onRemoveClick();
}
