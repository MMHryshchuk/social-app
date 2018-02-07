package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 01.08.17.
 */

public class FollowingRequest {

    int user_id;
    int following_id;

    public FollowingRequest() {
    }

    public FollowingRequest(int user_id, int following_id) {
        this.user_id = user_id;
        this.following_id = following_id;
    }
}
