package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 12.07.17.
 */

public class FeedActionRequest {

    int user_id;
    int post_id;

    public FeedActionRequest() {
    }

    public FeedActionRequest(int user_id, int post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
}
