package com.clearsoft.welivre.ui.screens.splash;

import com.clearsoft.welivre.ui.dvo.SplashDvo;

import java.util.List;

/**
 * Created by on 27.06.17.
 */

public interface SplashView {
    void showData(List<SplashDvo> dvos);
    void openLogin();
    void showNext();
    void showPrev();
    void showIndicatorPage(int page);
    void enableBack(boolean enable);
}
