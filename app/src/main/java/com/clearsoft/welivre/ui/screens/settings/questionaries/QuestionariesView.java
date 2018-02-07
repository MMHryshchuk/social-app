package com.clearsoft.welivre.ui.screens.settings.questionaries;

import android.widget.ImageView;

import com.clearsoft.welivre.ui.dvo.QuestionariesDvo;

/**
 * Created by on 25.07.17.
 */

public interface QuestionariesView {

    void setViewCheck(ImageView vCheck, ImageView vBg);
    void openCalendar();
    void showGender(String gender);
    void showProgress();
    void hideProgress();
    void showData(QuestionariesDvo dvo);

    void showSucces();
}
