package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 10.08.17.
 */

public class AboutDto {
    String user_about;
    String about_likes;
    String about_comments;
    String about_liked;

    public AboutDto() {
    }

    public AboutDto(String user_about, String about_likes, String about_comments, String about_liked) {
        this.user_about = user_about;
        this.about_likes = about_likes;
        this.about_comments = about_comments;
        this.about_liked = about_liked;
    }

    public String getUserAbout() {
        return user_about;
    }

    public String getAboutLikes() {
        return about_likes;
    }

    public String getAboutComments() {
        return about_comments;
    }

    public String getAboutLiked() {
        return about_liked;
    }
}

