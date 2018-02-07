package com.clearsoft.welivre.ui.dvo;

import java.util.List;

/**
 * Created by on 01.08.17.
 */

public class FollowingListDvo {

    List<FollowingDvo> followingDvos;

    public FollowingListDvo() {
    }

    public FollowingListDvo(List<FollowingDvo> followingDvos) {
        this.followingDvos = followingDvos;
    }

    public List<FollowingDvo> getFollowingDvos() {
        return followingDvos;
    }
}
