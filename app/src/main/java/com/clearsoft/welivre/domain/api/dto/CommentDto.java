package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 26.07.17.
 */

public class CommentDto {

    String user_id;
    String user_name;
    String user_avatar;
    String comment_id;
    String comment_txt;
    String timestamp;

    public CommentDto() {
    }

    public CommentDto(String user_id, String user_name, String user_avatar, String comment_id, String comment_txt, String timestamp) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_avatar = user_avatar;
        this.comment_id = comment_id;
        this.comment_txt = comment_txt;
        this.timestamp = timestamp;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public String getComment_id() {
        return comment_id;
    }

    public String getComment_txt() {
        return comment_txt;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
