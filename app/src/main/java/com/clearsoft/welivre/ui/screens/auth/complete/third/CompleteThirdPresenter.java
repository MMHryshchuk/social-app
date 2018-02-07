package com.clearsoft.welivre.ui.screens.auth.complete.third;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.dvo.CompleteRegDvo;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;

/**
 * Created by on 28.06.17.
 */

public interface CompleteThirdPresenter extends Presenter<CompleteThirdView> {

    void onCalendarClick();
    void onGenderClick(String gender);
    void onNextClick(CompleteRegisterActivity activity,
            String cigaStartAge,
            String birthday,
            String gender
            );
}
