package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 17.08.17.
 */

public class SpecUserInfoDto {

    String user_id;
    String user_email;
    String user_name;
    String user_avatar;
    String user_type;
    String user_about;
    String user_device;
    String user_token;
    String user_language;
    String user_timestamp;
    String user_following;
    String user_followers;
    String user_followed;
    String user_posts;
    String user_plan;
    String user_situation;
    String user_ciga_daily_num;
    String user_ciga_pack_cost;
    String user_ciga_wakeup_minutes;
    String user_ciga_start_age;
    String user_birthday;
    String user_gender;
    String about_likes;
    String about_comments;
    String about_liked;

    public SpecUserInfoDto() {
    }

    public SpecUserInfoDto(String user_id,
                           String user_email,
                           String user_name,
                           String user_avatar,
                           String user_type,
                           String user_about,
                           String user_device,
                           String user_token,
                           String user_language,
                           String user_timestamp,
                           String user_following,
                           String user_followers,
                           String user_followed,
                           String user_posts,
                           String user_plan,
                           String user_situation,
                           String user_ciga_daily_num,
                           String user_ciga_pack_cost,
                           String user_ciga_wakeup_minutes,
                           String user_ciga_start_age,
                           String user_birthday,
                           String user_gender,
                           String about_likes,
                           String about_comments,
                           String about_liked
    ) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_avatar = user_avatar;
        this.user_type = user_type;
        this.user_about = user_about;
        this.user_device = user_device;
        this.user_token = user_token;
        this.user_language = user_language;
        this.user_timestamp = user_timestamp;
        this.user_following = user_following;
        this.user_followers = user_followers;
        this.user_followed = user_followed;
        this.user_posts = user_posts;
        this.user_plan = user_plan;
        this.user_situation = user_situation;
        this.user_ciga_daily_num = user_ciga_daily_num;
        this.user_ciga_pack_cost = user_ciga_pack_cost;
        this.user_ciga_wakeup_minutes = user_ciga_wakeup_minutes;
        this.user_ciga_start_age = user_ciga_start_age;
        this.user_birthday = user_birthday;
        this.user_gender = user_gender;
        this.about_likes = about_likes;
        this.about_comments = about_comments;
        this.about_liked = about_liked;
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

    public String getUserAvatat() {
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

    public String getUserToken() {
        return user_token;
    }

    public String getUserLanguage() {
        return user_language;
    }

    public String getUserTimestamp() {
        return user_timestamp;
    }

    public String getUserFollowing() {
        return user_following;
    }

    public String getUserFollowers() {
        return user_followers;
    }

    public String getUserFollowed() {
        return user_followed;
    }

    public String getUserPosts() {
        return user_posts;
    }

    public String getUserPlan() {
        return user_plan;
    }

    public String getUserSituation() {
        return user_situation;
    }

    public String getUserCigaDailyNum() {
        return user_ciga_daily_num;
    }

    public String getUserCigaPackCost() {
        return user_ciga_pack_cost;
    }

    public String getUserCigaWakeupMinutes() {
        return user_ciga_wakeup_minutes;
    }

    public String getUserCigaStartAge() {
        return user_ciga_start_age;
    }

    public String getUserBirthday() {
        return user_birthday;
    }

    public String getUserGender() {
        return user_gender;
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

