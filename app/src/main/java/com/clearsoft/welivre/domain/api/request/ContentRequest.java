package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 29.08.17.
 */

public class ContentRequest {

    int user_id;
    String language;

    public ContentRequest() {
    }

    public ContentRequest(int user_id, String language) {
        this.user_id = user_id;
        this.language = language;
    }
}
