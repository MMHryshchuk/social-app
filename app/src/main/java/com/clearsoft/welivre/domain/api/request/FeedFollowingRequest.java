package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 12.07.17.
 */

public class FeedFollowingRequest {

    int user_id;
    long last_index;
    String language;

    public FeedFollowingRequest() {
    }

    public FeedFollowingRequest(int user_id, long last_index, String language) {
        this.user_id = user_id;
        this.last_index = last_index;
        this.language = language;
    }
}
