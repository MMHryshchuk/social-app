package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 12.07.17.
 */

public class FeedItemDvo {

    private String name;
    private String image;
    private String datePost;
    private String postImage;
    private String postText;
    private String postLikeCount;
    private String postCommentCount;
    private String postFavoriteCount;
    private String postShareCount;
    private boolean isMyLike;
    private boolean isMyFavorite;

    public FeedItemDvo() {
    }

    public FeedItemDvo(String name,
                       String image,
                       String datePost,
                       String postImage,
                       String postText,
                       String postLikeCount,
                       String postCommentCount,
                       String postFavoriteCount,
                       String postShareCount,
                       boolean isMyLike,
                       boolean isMyFavorite
    ) {
        this.name = name;
        this.image = image;
        this.datePost = datePost;
        this.postImage = postImage;
        this.postText = postText;
        this.postLikeCount = postLikeCount;
        this.postCommentCount = postCommentCount;
        this.postFavoriteCount = postFavoriteCount;
        this.postShareCount = postShareCount;
        this.isMyLike = isMyLike;
        this.isMyFavorite = isMyFavorite;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDatePost() {
        return datePost;
    }

    public String getPostImage() {
        return postImage;
    }

    public String getPostText() {
        return postText;
    }

    public String getPostLikeCount() {
        return postLikeCount;
    }

    public String getPostCommentCount() {
        return postCommentCount;
    }

    public String getPostFavoriteCount() {
        return postFavoriteCount;
    }

    public String getPostShareCount() {
        return postShareCount;
    }

    public boolean isMyLike() {
        return isMyLike;
    }

    public boolean isMyFavorite() {
        return isMyFavorite;
    }
}
