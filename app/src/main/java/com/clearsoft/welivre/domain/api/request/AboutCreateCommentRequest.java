package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 11.08.17.
 */

public class AboutCreateCommentRequest {

    int user_id_about_owner;
    int user_id_who_comment;
    String comment_txt;

    public AboutCreateCommentRequest() {
    }

    public AboutCreateCommentRequest(int user_id_about_owner, int user_id_who_comment, String comment_txt) {
        this.user_id_about_owner = user_id_about_owner;
        this.user_id_who_comment = user_id_who_comment;
        this.comment_txt = comment_txt;
    }
}
