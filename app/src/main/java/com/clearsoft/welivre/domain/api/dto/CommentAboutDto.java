package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 12.08.17.
 */

public class CommentAboutDto {

    String comment_id;
    String comment_text;
    String comment_timestamp;
    String user_id;
    String user_email;
    String user_name;
    String user_avatar;
    String user_type;
    String user_about;
    String user_device;
    String user_token;
    String user_language;

    public CommentAboutDto() {
    }

    public CommentAboutDto(String comment_id,
                           String comment_text,
                           String comment_timestamp,
                           String user_id,
                           String user_email,
                           String user_name,
                           String user_avatar,
                           String user_type,
                           String user_about,
                           String user_device,
                           String user_token,
                           String user_language
    ) {
        this.comment_id = comment_id;
        this.comment_text = comment_text;
        this.comment_timestamp = comment_timestamp;
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_avatar = user_avatar;
        this.user_type = user_type;
        this.user_about = user_about;
        this.user_device = user_device;
        this.user_token = user_token;
        this.user_language = user_language;
    }

    public String getCommentText() {
        return comment_text;
    }

    public String getCommentTimestamp() {
        return comment_timestamp;
    }

    public String getUserId() {
        return user_id;
    }

    public String getUserEmail() {
        return user_email;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserAvatar() {
        return user_avatar;
    }

    public String getUserType() {
        return user_type;
    }

    public String getUserAbout() {
        return user_about;
    }

    public String getUserDevice() {
        return user_device;
    }

    public String getCommentId() {
        return comment_id;
    }

    public String getUserToken() {
        return user_token;
    }

    public String getUserLanguage() {
        return user_language;
    }
}
