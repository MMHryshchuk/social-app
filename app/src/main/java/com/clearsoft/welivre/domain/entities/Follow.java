package com.clearsoft.welivre.domain.entities;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "FOLLOW".
 */
public class Follow {

    private String followId;
    private String followTimestamp;
    private String userId;
    private String userName;
    private String userEmail;
    private String userLanguage;
    private String userFollowings;
    private String userFollowers;
    private String userAvatar;
    private String userPosts;
    private String userFollowed;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Follow() {
    }

    public Follow(String followId, String followTimestamp, String userId, String userName, String userEmail, String userLanguage, String userFollowings, String userFollowers, String userAvatar, String userPosts, String userFollowed) {
        this.followId = followId;
        this.followTimestamp = followTimestamp;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLanguage = userLanguage;
        this.userFollowings = userFollowings;
        this.userFollowers = userFollowers;
        this.userAvatar = userAvatar;
        this.userPosts = userPosts;
        this.userFollowed = userFollowed;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getFollowTimestamp() {
        return followTimestamp;
    }

    public void setFollowTimestamp(String followTimestamp) {
        this.followTimestamp = followTimestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getUserFollowings() {
        return userFollowings;
    }

    public void setUserFollowings(String userFollowings) {
        this.userFollowings = userFollowings;
    }

    public String getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(String userFollowers) {
        this.userFollowers = userFollowers;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(String userPosts) {
        this.userPosts = userPosts;
    }

    public String getUserFollowed() {
        return userFollowed;
    }

    public void setUserFollowed(String userFollowed) {
        this.userFollowed = userFollowed;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}