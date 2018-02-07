package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 12.07.17.
 */

public class FeedShareRequest {

    String user_id;
    String post_id;
    String content_txt;

    public FeedShareRequest() {
    }

    public FeedShareRequest(String user_id, String post_id, String content_txt) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.content_txt = content_txt;
    }
}
