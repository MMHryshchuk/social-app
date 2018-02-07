package com.clearsoft.welivre.ui.screens.profile.posts;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.dvo.FeedDvo;

/**
 * Created by on 15.07.17.
 */

public interface PostsPresenter extends Presenter<PostsView>{

    void onLikeClick(int postId);
    void onUnLikeClick(int postId);
    void onFavoriteClick(int postId);
    void onUnFavoriteClick(int postId);
    void onCommentClick(int postId,int postLikes);

    void onMoreFeedClick(FeedDvo dvo);
}
