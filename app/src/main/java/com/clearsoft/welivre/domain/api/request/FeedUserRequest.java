package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 12.07.17.
 */

public class FeedUserRequest {

    int user_id;
    int target_id;
    long last_index;
    String language;

    public FeedUserRequest() {
    }

    public FeedUserRequest(int user_id, int target_id, long last_index, String language) {
        this.user_id = user_id;
        this.target_id = target_id;
        this.last_index = last_index;
        this.language = language;
    }
}
