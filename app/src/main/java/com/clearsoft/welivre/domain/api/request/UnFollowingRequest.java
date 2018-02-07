package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 01.08.17.
 */

public class UnFollowingRequest {

    int user_id;
    int unfollowing_id;

    public UnFollowingRequest() {
    }

    public UnFollowingRequest(int user_id, int unfollowing_id) {
        this.user_id = user_id;
        this.unfollowing_id = unfollowing_id;
    }
}
