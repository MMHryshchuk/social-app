package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 27.07.17.
 */

public class CommentDeleteRequest {
    int user_id;
    int comment_id;

    public CommentDeleteRequest() {
    }

    public CommentDeleteRequest(int user_id, int comment_id) {
        this.user_id = user_id;
        this.comment_id = comment_id;
    }
}
