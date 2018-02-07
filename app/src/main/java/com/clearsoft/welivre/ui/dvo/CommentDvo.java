package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 26.07.17.
 */

public class CommentDvo {

    private long userId;
    private String userName;
    private String userAvatar;
    private long commentId;
    private String commentTxt;
    private long timestamp;

    public CommentDvo() {
    }

    public CommentDvo(long userId, String userName, String userAvatar, long commentId, String commentTxt, long timestamp) {
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.commentId = commentId;
        this.commentTxt = commentTxt;
        this.timestamp = timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentTxt() {
        return commentTxt;
    }

    public void setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
