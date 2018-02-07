package com.clearsoft.welivre.ui.screens.profile;

import com.clearsoft.welivre.ui.dvo.ProfileDvo;

/**
 * Created by on 01.08.17.
 */

public interface ProfileView {

    void setFollow();
    void setUnFollow();
    void showData(ProfileDvo dvo);
}
