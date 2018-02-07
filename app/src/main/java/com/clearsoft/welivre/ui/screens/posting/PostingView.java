package com.clearsoft.welivre.ui.screens.posting;

/**
 * Created by on 12.07.17.
 */

public interface PostingView {
    void closePostingView();
    void showImagePreview();
    void showProgress();
    void hideProgress();

    void setSosActivated();

    void removeImage();
}
