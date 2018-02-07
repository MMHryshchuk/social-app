package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 01.08.17.
 */

public class FollowersRequest {

    int user_id;
    long last_index;

    public FollowersRequest() {
    }

    public FollowersRequest(int user_id, long last_index) {
        this.user_id = user_id;
        this.last_index = last_index;
    }
}
