package com.clearsoft.welivre.ui.screens.comment;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 15.07.17.
 */

public class CommentPresenterImpl extends BasePresenter<CommentView> implements CommentPresenter {

    App app;
    FeedUseCase feedUseCase;
    UserUseCase userUseCase;
    private final int postId;
    private final boolean isAbout;

    private boolean canLoadMore = false;
    private long lastPage = -1;

    public CommentPresenterImpl(App app,
                                FeedUseCase feedUseCase,
                                UserUseCase userUseCase,
                                int postId,
                                boolean isAbout
    ) {
        this.app = app;
        this.feedUseCase = feedUseCase;
        this.userUseCase = userUseCase;
        this.postId = postId;
        this.isAbout = isAbout;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        if (isAbout) {
            addSubscription(userUseCase.getAboutComments(postId, lastPage).subscribe(
                    next -> {
                        getView().showData(next.getCommentDvos());
                        lastPage = next.getLastPage();
                        canLoadMore = lastPage > -1;
                    },
                    error -> {

                    }
            ));
        } else {
            addSubscription(feedUseCase.getComments(postId, lastPage).subscribe(
                    next -> {
                        getView().showData(next.getCommentDvos());
                        lastPage = next.getLastPage();
                        canLoadMore = lastPage > -1;
                    },
                    error -> {

                    }
            ));
        }
    }


    @Override
    public void postComment(String txt, int post_id) {
        if (isAbout) {
            addSubscription(userUseCase.aboutPostComment(post_id, txt).subscribe(
                    next -> {
                        if (getView() == null) return;
                        getView().clearField();
                        getComents();

                    },
                    error -> {

                    }
            ));
        } else {
            addSubscription(feedUseCase.postComment(txt, post_id).subscribe(
                    next -> {
                        if (getView() == null) return;
                        getView().clearField();
                        getComents();

                    },
                    error -> {

                    }
            ));
        }
    }

    @Override
    public void deleteComment(int commentId) {

    }

    @Override
    public void loadMoreComment() {
        if (!canLoadMore) return;
        canLoadMore = false;
//        getView().showLoadMoreProgress();

        if (isAbout) {
            addSubscription(userUseCase.getAboutComments(postId, lastPage).subscribe(
                    next -> {
                        lastPage = next.getLastPage();
                        getView().showData(next.getCommentDvos());
                        canLoadMore = lastPage > -1;
                    },
                    error -> {
                        canLoadMore = false;
                    }
            ));
        } else {
            addSubscription(feedUseCase.getComments(postId, lastPage).subscribe(
                    next -> {
                        lastPage = next.getLastPage();
                        getView().showData(next.getCommentDvos());
                        canLoadMore = lastPage > -1;
                    },
                    error -> {
                        canLoadMore = false;
                    }
            ));

        }
    }

    private void getComents() {
        if (isAbout) {
            addSubscription(userUseCase.getAboutComments(postId, lastPage).subscribe(
                    next -> {
                        getView().showData(next.getCommentDvos());
                        lastPage = next.getLastPage();
                        canLoadMore = lastPage > -1;
                    },
                    error -> {

                    }
            ));
        } else {
            addSubscription(feedUseCase.getComments(postId, lastPage).subscribe(
                    next -> {
                        lastPage = next.getLastPage();
                        getView().showData(next.getCommentDvos());
                        canLoadMore = lastPage > -1;
                    },
                    error -> {

                    }
            ));
        }
    }
}
