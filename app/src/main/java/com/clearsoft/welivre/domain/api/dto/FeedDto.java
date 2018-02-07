package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 13.07.17.
 */

public class FeedDto {

    String post_id;
    String post_content_txt;
    String post_content_img;
    String post_sos;
    String post_timestamp;
    String post_favorites;
    String post_comments;
    String post_likes;
    String post_shares;
    String post_liked;
    String post_favorited;
    String user_id;
    String user_name;
    String user_avatar;
    String user_followers;
    String user_followings;
    String user_posts;

    public FeedDto() {
    }

    public String getPostId() {
        return post_id;
    }

    public String getPost_contentTxt() {
        return post_content_txt;
    }

    public String getPost_contentImg() {
        return post_content_img;
    }

    public String getPostSos() {
        return post_sos;
    }

    public String getPostTimestamp() {
        return post_timestamp;
    }

    public String getPostFavorites() {
        return post_favorites;
    }

    public String getPostComments() {
        return post_comments;
    }

    public String getPostLikes() {
        return post_likes;
    }

    public String getPostShares() {
        return post_shares;
    }

    public String getPostLiked() {
        return post_liked;
    }

    public String getPostFavorited() {
        return post_favorited;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserAvatar() {
        return user_avatar;
    }

    public String getUserId() {
        return user_id;
    }

    public String getUserFollowers() {
        return user_followers;
    }

    public String getUserFollowings() {
        return user_followings;
    }

    public String getUserPosts() {
        return user_posts;
    }
}
