package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 01.08.17.
 */

public class FollowDto {

    String follow_id;
    String follow_timestamp;
    String user_id;
    String user_name;
    String user_email;
    String user_language;
    String user_followings;
    String user_followers;
    String user_avatar;
    String user_posts;
    String user_followed;

    public FollowDto() {
    }

    public FollowDto(String follow_id, String follow_timestamp, String user_id, String user_name, String user_email, String user_language, String user_followings, String user_followers, String user_avatar, String user_posts, String user_followed) {
        this.follow_id = follow_id;
        this.follow_timestamp = follow_timestamp;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_language = user_language;
        this.user_followings = user_followings;
        this.user_followers = user_followers;
        this.user_avatar = user_avatar;
        this.user_posts = user_posts;
        this.user_followed = user_followed;
    }

    public String getFollowId() {
        return follow_id;
    }

    public String getFollowTimestamp() {
        return follow_timestamp;
    }

    public String getUserId() {
        return user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserEmail() {
        return user_email;
    }

    public String getUserLanguage() {
        return user_language;
    }

    public String getUserFollowings() {
        return user_followings;
    }

    public String getUserFollowers() {
        return user_followers;
    }

    public String getUserAvatar() {
        return user_avatar;
    }

    public String getUserPosts() {
        return user_posts;
    }

    public String getUserFollowed() {
        return user_followed;
    }
}
