package com.clearsoft.welivre.ui.screens.settings.questionaries;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 25.07.17.
 */

public interface QuestionariesPresenter extends Presenter<QuestionariesView> {

    void onCalendarClick();
    void onGenderClick(String gender);
    void saveChanges(int situation, String cigaDayliNum, String cigaPackCost, String cigaWakeUpMinutes, String cigaStartAge, String birthday, String gender);
}
