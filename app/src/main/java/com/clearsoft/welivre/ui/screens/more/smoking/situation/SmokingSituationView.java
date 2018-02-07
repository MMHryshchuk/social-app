package com.clearsoft.welivre.ui.screens.more.smoking.situation;

import android.widget.ImageView;

/**
 * Created by on 18.07.17.
 */

public interface SmokingSituationView {

    void setViewCheck(ImageView vCheck, ImageView vBg);
    void showProgress();
    void hideProgress();
    void openNext();
}
