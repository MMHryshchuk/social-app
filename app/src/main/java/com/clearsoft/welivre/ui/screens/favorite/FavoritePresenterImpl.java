package com.clearsoft.welivre.ui.screens.favorite;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;

/**
 * Created by on 05.07.17.
 */

public class FavoritePresenterImpl extends BasePresenter<FavoriteView> implements FavoritePresenter{

    App app;
    FeedUseCase feedUseCase;

    public FavoritePresenterImpl(App app, FeedUseCase feedUseCase) {
        this.app = app;
        this.feedUseCase = feedUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();

        addSubscription(feedUseCase.getAllFavorites().subscribe(
                next -> {
                    getView().showData(next);
                }
        ));
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
    public void update() {
        addSubscription(feedUseCase.getAllFavorites().subscribe(
                next -> {
                    getView().showData(next);
                }
        ));
    }
}
