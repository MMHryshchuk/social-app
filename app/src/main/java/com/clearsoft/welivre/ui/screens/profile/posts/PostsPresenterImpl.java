package com.clearsoft.welivre.ui.screens.profile.posts;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.utils.LanguageUtils;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.ui.dvo.FeedDvo;

/**
 * Created by on 15.07.17.
 */

public class PostsPresenterImpl extends BasePresenter<PostsView> implements PostsPresenter {

    App app;
    FeedUseCase feedUseCase;
    private final String targetId;

    public PostsPresenterImpl(App app, FeedUseCase feedUseCase, String targetId) {
        this.app = app;
        this.feedUseCase = feedUseCase;
        this.targetId = targetId;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        feedUseCase.getUserPost(targetId,-1, LanguageUtils.getLang(app)).subscribe(
                next -> {
                    getView().showData(next);
                },
                error -> {

                }
        );
    }
    @Override
    public void onLikeClick(int postId) {
        addSubscription(feedUseCase.likePost(postId).subscribe(
                next -> {
                    getView().updateFeedItem(next);
                },
                error -> {

                }
        ));
    }

    @Override
    public void onUnLikeClick(int postId) {
        addSubscription(feedUseCase.unlikePost(postId).subscribe(
                next -> {
                    getView().updateFeedItem(next);
                },
                error -> {

                }
        ));
    }

    @Override
    public void onFavoriteClick(int postId) {
        addSubscription(feedUseCase.favoritePost(postId).subscribe(
                next -> {
                    getView().updateFeedItem(next);
                },
                error -> {

                }
        ));
    }

    @Override
    public void onUnFavoriteClick(int postId) {
        addSubscription(feedUseCase.unFavoritePost(postId).subscribe(
                next -> {
                    getView().updateFeedItem(next);
                },
                error -> {

                }
        ));
    }

    @Override
    public void onCommentClick(int postId,int postLikes) {
        if (getView() == null) return;
        getView().openComments(postId,postLikes);
    }

    @Override
    public void onMoreFeedClick(FeedDvo dvo) {
        if (getView() == null)return;
        getView().openMoreScreen(dvo);
    }
}
