package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 01.08.17.
 */

public class FollowingDvo {

    private String followId;
    private String userId;
    private String userImage;
    private String userName;
    private String userEmail;
    private String userFollowers;
    private String userFollowings;
    private String userFollowed;
    private boolean isFollowed;


    public FollowingDvo() {
    }

    public FollowingDvo(String followId,
                        String userId,
                        String userImage,
                        String userName,
                        String userEmail,
                        String userFollowers,
                        String userFollowings,
                        String userFollowed
    ) {
        this.followId = followId;
        this.userId = userId;
        this.userImage = userImage;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userFollowers = userFollowers;
        this.userFollowings = userFollowings;
        this.userFollowed = userFollowed;
        this.isFollowed = userFollowed.equals("1");
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    public String getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(String userFollowers) {
        this.userFollowers = userFollowers;
    }

    public String getUserFollowings() {
        return userFollowings;
    }

    public void setUserFollowings(String userFollowings) {
        this.userFollowings = userFollowings;
    }

    public String getUserFollowed() {
        return userFollowed;
    }

    public void setUserFollowed(String userFollowed) {
        this.userFollowed = userFollowed;
        this.isFollowed = userFollowed.equals("1");
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }
}
