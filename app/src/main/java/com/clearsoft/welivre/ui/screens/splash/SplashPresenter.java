package com.clearsoft.welivre.ui.screens.splash;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 27.06.17.
 */

public interface SplashPresenter extends Presenter<SplashView> {

    void onNextClick();
    void onBackClick();
    void onSkipClick();
}
