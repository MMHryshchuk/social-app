package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 11.08.17.
 */

public class AboutCommentRequest {

    int user_id;
    long last_index;

    public AboutCommentRequest() {
    }

    public AboutCommentRequest(int user_id, long last_index) {
        this.user_id = user_id;
        this.last_index = last_index;
    }
}

