package com.clearsoft.welivre.ui.screens.video_help;

import com.clearsoft.welivre.core.mvp.BasePresenter;

/**
 * Created by on 25.07.17.
 */

public class VideoHelpPresenterImpl extends BasePresenter<VideoHelpView> implements VideoHelpPresenter {

    public VideoHelpPresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onClose() {
        if (getView() == null) return;
        getView().close();
    }
}
