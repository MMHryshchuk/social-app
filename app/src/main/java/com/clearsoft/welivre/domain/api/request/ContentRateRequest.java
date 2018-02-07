package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 29.08.17.
 */

public class ContentRateRequest {

    int user_id;
    int content_item_id;
    String rate;

    public ContentRateRequest() {
    }

    public ContentRateRequest(int user_id, int content_item_id, String rate) {
        this.user_id = user_id;
        this.content_item_id = content_item_id;
        this.rate = rate;
    }
}
