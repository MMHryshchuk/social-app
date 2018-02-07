package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 26.07.17.
 */

public class CommentRequest {

    int user_id;
    int post_id;
    long last_index;

    public CommentRequest() {
    }

    public CommentRequest(int user_id, int post_id, long last_index) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.last_index = last_index;
    }
}
