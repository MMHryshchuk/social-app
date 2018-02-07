package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 29.08.17.
 */

public class ContentFavoriteRequest {

    int user_id;
    int content_item_id;

    public ContentFavoriteRequest() {
    }

    public ContentFavoriteRequest(int user_id, int content_item_id) {
        this.user_id = user_id;
        this.content_item_id = content_item_id;
    }
}

