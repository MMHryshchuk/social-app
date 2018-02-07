package com.clearsoft.welivre.ui.screens.profile.about;

import com.clearsoft.welivre.ui.dvo.AboutDvo;
import com.clearsoft.welivre.ui.dvo.MyPlanDvo;

/**
 * Created by on 01.08.17.
 */

public interface AboutView {

    void showData(AboutDvo dvo);
    void showPlanData(MyPlanDvo dvo);
    void updateAbout(AboutDvo dvo);
    void openComment(int userId, int likesCount);
    void update();

    void showProgress();
    void hideProgress();
}
