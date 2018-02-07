package com.clearsoft.welivre.ui.screens.home;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 29.06.17.
 */

public interface HomePresenter extends Presenter<HomeView> {

    void onAllFeedClick();

    void onFollowingFeedClick();

    void onSosFeedClick();

    void onMyProfileClick();

    void onSavingBtnClick();

    void onUserViewClick();

    void onArticleClick();

    void onCreatePostClick();

    void onAwardsClick();

    void onLikeClick(int postId);

    void onUnLikeClick(int postId);

    void onFavoriteClick(int postId);

    void onUnFavoriteClick(int postId);

    void onCommentClick(int postId, int postLikes);

    void loadMoreAllFeeds();

    void loadMoreFollowingFeeds();

    void loadMoreSosFeeds();

    void hidePostsFromUser(String userId);

}
