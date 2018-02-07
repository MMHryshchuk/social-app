package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 17.08.17.
 */

public class ProfileDvo {

    private int userId;
    private String useremail;
    private String userName;
    private String userAvatar;
    private String userAbout;
    private String userFollowing;
    private String userFollowers;
    private String userFollowed;
    private String userPosts;
    private String userPlan;
    private String userCigaDailNum;
    private String userCigaPackCost;
    private String userCigaStartAge;
    private String aboutLikes;
    private String aboutComments;
    private String aboutLiked;
    private boolean isFollowed;

    public ProfileDvo() {
    }

    public ProfileDvo(int userId,
                      String useremail,
                      String userName,
                      String userAvatar,
                      String userAbout,
                      String userFollowing,
                      String userFollowers,
                      String userFollowed,
                      String userPosts,
                      String userPlan,
                      String userCigaDailNum,
                      String userCigaPackCost,
                      String userCigaStartAge,
                      String aboutLikes,
                      String aboutComments,
                      String aboutLiked
    ) {
        this.userId = userId;
        this.useremail = useremail;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.userAbout = userAbout;
        this.userFollowing = userFollowing;
        this.userFollowers = userFollowers;
        this.userFollowed = userFollowed;
        this.userPosts = userPosts;
        this.userPlan = userPlan;
        this.userCigaDailNum = userCigaDailNum;
        this.userCigaPackCost = userCigaPackCost;
        this.userCigaStartAge = userCigaStartAge;
        this.aboutLikes = aboutLikes;
        this.aboutComments = aboutComments;
        this.aboutLiked = aboutLiked;
        this.isFollowed = userFollowed.equals("1");
    }

    public int getUserId() {
        return userId;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public String getUserFollowing() {
        return userFollowing;
    }

    public String getUserFollowers() {
        return userFollowers;
    }

    public String getUserFollowed() {
        return userFollowed;
    }

    public String getUserPosts() {
        return userPosts;
    }

    public String getUserPlan() {
        return userPlan;
    }

    public String getUserCigaDailNum() {
        return userCigaDailNum;
    }

    public String getUserCigaPackCost() {
        return userCigaPackCost;
    }

    public String getUserCigaStartAge() {
        return userCigaStartAge;
    }

    public String getAboutLikes() {
        return aboutLikes;
    }

    public String getAboutComments() {
        return aboutComments;
    }

    public String getAboutLiked() {
        return aboutLiked;
    }

    public boolean isFollowed() {
        return isFollowed;
    }
}
