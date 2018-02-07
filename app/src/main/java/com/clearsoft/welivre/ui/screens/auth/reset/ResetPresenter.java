package com.clearsoft.welivre.ui.screens.auth.reset;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 28.06.17.
 */

public interface ResetPresenter extends Presenter<ResetView>{

    void onResetClick(String email);
    void onBackClick();
}
