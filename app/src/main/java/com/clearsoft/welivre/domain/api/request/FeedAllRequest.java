package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 04.07.17.
 */

public class FeedAllRequest {

    int user_id;
    long last_index;
    String language;

    public FeedAllRequest() {
    }

    public FeedAllRequest(int user_id, long last_index, String language) {
        this.user_id = user_id;
        this.last_index = last_index;
        this.language = language;
    }
}
