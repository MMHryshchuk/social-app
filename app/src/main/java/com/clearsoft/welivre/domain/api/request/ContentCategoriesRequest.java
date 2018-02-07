package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 29.08.17.
 */

public class ContentCategoriesRequest {

    int user_id;
    String language;

    public ContentCategoriesRequest() {
    }

    public ContentCategoriesRequest(int user_id, String language) {
        this.user_id = user_id;
        this.language = language;
    }
}
