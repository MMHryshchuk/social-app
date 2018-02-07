package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 10.08.17.
 */

public class AboutDvo {

    private String userAboutTxt;
    private String aboutLikeCount;
    private String aboutCommentCount;
    private boolean isLiked;
    private boolean isMyProfile;
    private PlanDvo planDvo;

    public AboutDvo() {
    }

    public AboutDvo(String userAboutTxt, String aboutLikeCount, String aboutCommentCount) {
        this.userAboutTxt = userAboutTxt;
        this.aboutLikeCount = aboutLikeCount;
        this.aboutCommentCount = aboutCommentCount;
        this.isLiked = false;
    }

    public AboutDvo(String userAboutTxt, String aboutLikeCount, String aboutCommentCount, boolean isLiked) {
        this.userAboutTxt = userAboutTxt;
        this.aboutLikeCount = aboutLikeCount;
        this.aboutCommentCount = aboutCommentCount;
        this.isLiked = isLiked;
        this.isMyProfile = false;
    }

    public AboutDvo(String userAboutTxt, String aboutLikeCount, String aboutCommentCount, boolean isLiked, boolean isMyProfile) {
        this.userAboutTxt = userAboutTxt;
        this.aboutLikeCount = aboutLikeCount;
        this.aboutCommentCount = aboutCommentCount;
        this.isLiked = isLiked;
        this.isMyProfile = isMyProfile;
    }

    public String getUserAboutTxt() {
        return userAboutTxt;
    }

    public void setUserAboutTxt(String userAboutTxt) {
        this.userAboutTxt = userAboutTxt;
    }

    public String getAboutLikeCount() {
        return aboutLikeCount;
    }

    public void setAboutLikeCount(String aboutLikeCount) {
        this.aboutLikeCount = aboutLikeCount;
    }

    public String getAboutCommentCount() {
        return aboutCommentCount;
    }

    public void setAboutCommentCount(String aboutCommentCount) {
        this.aboutCommentCount = aboutCommentCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isMyProfile() {
        return isMyProfile;
    }
}
