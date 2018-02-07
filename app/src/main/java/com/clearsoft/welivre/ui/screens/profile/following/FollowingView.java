package com.clearsoft.welivre.ui.screens.profile.following;

import com.clearsoft.welivre.ui.dvo.FollowingListDvo;

/**
 * Created by on 01.08.17.
 */

public interface FollowingView {

    void showData(FollowingListDvo dvo);

    void updateFollow(String followId, boolean isFollow);
}
