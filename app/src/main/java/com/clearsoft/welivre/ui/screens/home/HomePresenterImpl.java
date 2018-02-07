package com.clearsoft.welivre.ui.screens.home;

import android.util.Log;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.core.rx.SimpleSubscriber;
import com.clearsoft.welivre.core.utils.LanguageUtils;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;

/**
 * Created by on 29.06.17.
 */

public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter {

    App app;
    FeedUseCase feedUseCase;

    private boolean canLoadMoreAll = false;
    private boolean canLoadMoreFollowing= false;
    private boolean canLoadMoreSos = false;
    private long lastAllPage = -1;
    private long lastFollowingPage = -1;
    private long lastSosPage = -1;

    public HomePresenterImpl(App app, FeedUseCase feedUseCase) {
        this.app = app;
        this.feedUseCase = feedUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();

        addSubscription(feedUseCase.getAllPost(LanguageUtils.getLang(app),lastAllPage).subscribe(
                next -> {
                    lastAllPage = next.getPage();
                    canLoadMoreAll = lastAllPage > -1;
                    getView().showData(next);


                },
                error -> {
                }
        ));

        getView().setTabNonActive();
        getView().setTabAllActive();

    }


    @Override
    public void onAllFeedClick() {
        addSubscription(feedUseCase.getAllPost(LanguageUtils.getLang(app),-1).subscribe(
                next -> {
                    Log.d("SUKABLEAT", "SUKA");
                    lastAllPage = next.getPage();
                    canLoadMoreAll = lastAllPage > -1;
                    getView().showData(next);


                },
                error -> {
                    Log.d("SUKABLEAT",error.toString());

                }
        ));
        getView().setTabNonActive();
        getView().setTabAllActive();
//        getView().showData();
    }

    @Override
    public void onFollowingFeedClick() {

        addSubscription(feedUseCase.getFollowingPost(-1,LanguageUtils.getLang(app)).subscribe(feedListDvo -> {
            getView().showData(feedListDvo);
        }));
        getView().setTabNonActive();
        getView().setTabFollowingActive();
//        getView().showData();
    }

    @Override
    public void onSosFeedClick() {

        addSubscription(feedUseCase.getSosPost(LanguageUtils.getLang(app),-1).subscribe(
                next -> {
                    getView().showData(next);
                },
                error -> {
                }
        ));
        getView().setTabNonActive();
        getView().setTabSosActive();
//        getView().showData();
    }

    @Override
    public void onMyProfileClick() {
        if (getView() == null) return;
        getView().openUserProfile();
    }

    @Override
    public void onSavingBtnClick() {
        if (getView() == null) return;
        getView().showDetailUserView();
    }

    @Override
    public void onUserViewClick() {
        if (getView() == null) return;
        getView().showUserView();
    }

    @Override
    public void onArticleClick() {
        if (getView() == null) return;
        getView().openArticle();
    }

    @Override
    public void onCreatePostClick() {
        if (getView() == null) return;
        getView().openPostingScreen();
    }

    @Override
    public void onAwardsClick() {
        if (getView() == null) return;
        getView().openAwards();
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
    public void loadMoreAllFeeds() {
        if (!canLoadMoreAll) return;
        canLoadMoreAll = false;
        getView().showLoadMoreProgress();

        addSubscription(feedUseCase.getAllPost(LanguageUtils.getLang(app),lastAllPage).subscribe(
                next -> {
                    lastAllPage = next.getPage();
                    getView().hideLoadMoreProgress();
                    getView().showData(next);
                    canLoadMoreAll = lastAllPage > -1;
                },
                error -> {
                    canLoadMoreAll = false;
                    getView().hideLoadMoreProgress();
                }
        ));
    }

    @Override
    public void loadMoreFollowingFeeds() {
        if (!canLoadMoreFollowing) return;
        canLoadMoreFollowing = false;
        getView().showLoadMoreProgress();



        addSubscription(feedUseCase.getFollowingPost(lastFollowingPage,LanguageUtils.getLang(app)).subscribe(
                next -> {
                    lastFollowingPage = next.getPage();
                    getView().hideLoadMoreProgress();
                    getView().showData(next);
                },
                error -> {
                    canLoadMoreAll = false;
                    getView().hideLoadMoreProgress();
                }
        ));
    }

    @Override
    public void loadMoreSosFeeds() {
        if (!canLoadMoreSos) return;
        canLoadMoreSos = false;
        getView().showLoadMoreProgress();

        addSubscription(feedUseCase.getSosPost(LanguageUtils.getLang(app),lastSosPage).subscribe(
                next -> {
                    lastSosPage = next.getPage();
                    getView().hideLoadMoreProgress();
                    getView().showData(next);
                },
                error -> {
                    canLoadMoreSos = false;
                    getView().hideLoadMoreProgress();
                }
        ));
    }

    @Override
    public void hidePostsFromUser(String userId) {
        addSubscription(feedUseCase.hideFromUserAndGetPosts(userId).subscribe(
                next -> {
                    getView().showData(next);
                }
        ));
    }
}
