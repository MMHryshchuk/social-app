package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 12.07.17.
 */

public class FeedCreateUpdateRequest {

    int user_id;
    int post_id;
    String comment_txt;

    public FeedCreateUpdateRequest() {
    }

    public FeedCreateUpdateRequest(int user_id, int post_id, String comment_txt) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_txt = comment_txt;
    }
}
