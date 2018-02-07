package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 13.07.17.
 */

public class FeedDvo {

    private String postId;
    private String postContentTxt;
    private String postContentImg;
    private String postSos;
    private String postTimestamp;
    private String postFavorites;
    private String postComments;
    private String postLikes;
    private String postShares;
    private String postLiked;
    private String postFavorited;
    private String userName;
    private String userAvatar;
    private String userId;
    private String userFollowers;
    private String userFollowings;
    private String userPosts;



    public FeedDvo() {
    }

    public FeedDvo(String postId,
                   String postContentTxt,
                   String postContentImg,
                   String postSos,
                   String postTimestamp,
                   String postFavorites,
                   String postComments,
                   String postLikes,
                   String postShares,
                   String postLiked,
                   String postFavorited,
                   String userName,
                   String userAvatar,
                   String userId,
                   String userFollowers,
                   String userFollowings,
                   String userPosts
    ) {
        this.postId = postId;
        this.postContentTxt = postContentTxt;
        this.postContentImg = postContentImg;
        this.postSos = postSos;
        this.postTimestamp = postTimestamp;
        this.postFavorites = postFavorites;
        this.postComments = postComments;
        this.postLikes = postLikes;
        this.postShares = postShares;
        this.postLiked = postLiked;
        this.postFavorited = postFavorited;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.userId = userId;
        this.userFollowers = userFollowers;
        this.userFollowings = userFollowings;
        this.userPosts = userPosts;
    }

    public String getPostId() {
        return postId;
    }
    public int getPostIdInt(){
        return Integer.parseInt(postId);
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostContentTxt() {
        return postContentTxt;
    }

    public void setPostContentTxt(String postContentTxt) {
        this.postContentTxt = postContentTxt;
    }

    public String getPostContentImg() {
        return postContentImg;
    }

    public void setPostContentImg(String postContentImg) {
        this.postContentImg = postContentImg;
    }

    public String getPostSos() {
        return postSos;
    }

    public void setPostSos(String postSos) {
        this.postSos = postSos;
    }

    public String getPostTimestamp() {
        return postTimestamp;
    }
    public Long getPostTimestampLong() {
        return Long.parseLong(postTimestamp);
    }

    public void setPostTimestamp(String postTimestamp) {
        this.postTimestamp = postTimestamp;
    }

    public String getPostFavorites() {
        return postFavorites;
    }

    public void setPostFavorites(String postFavorites) {
        this.postFavorites = postFavorites;
    }

    public String getPostComments() {
        return postComments;
    }

    public void setPostComments(String postComments) {
        this.postComments = postComments;
    }

    public String getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(String postLikes) {
        this.postLikes = postLikes;
    }

    public String getPostShares() {
        return postShares;
    }

    public void setPostShares(String postShares) {
        this.postShares = postShares;
    }

    public String getPostLiked() {
        return postLiked;
    }

    public void setPostLiked(String postLiked) {
        this.postLiked = postLiked;
    }

    public String getPostFavorited() {
        return postFavorited;
    }

    public void setPostFavorited(String postFavorited) {
        this.postFavorited = postFavorited;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }



    public boolean isMyLike() {
        return postLiked.equals("1");
    }


    public boolean isMyFavorite() {
        return postFavorited.equals("1");
    }


    public boolean isSos() {
        return postSos.equals("2");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(String userPosts) {
        this.userPosts = userPosts;
    }
}
