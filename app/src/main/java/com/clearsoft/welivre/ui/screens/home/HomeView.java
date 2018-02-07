package com.clearsoft.welivre.ui.screens.home;

import android.view.View;
import android.widget.TextView;

import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;

/**
 * Created by on 29.06.17.
 */

public interface HomeView {
    void setTabNonActive();
    void setTabAllActive();
    void setTabFollowingActive();
    void setTabSosActive();
    void showData(FeedListDvo dvo);
    void openUserProfile();
    void showDetailUserView();
    void showUserView();
    void openArticle();

    void openAwards();

    void openPostingScreen();
    void updateFeedItem(FeedDvo feedDvo);

    void openComments(int postId, int postLikes);

    void showLoadMoreProgress();
    void hideLoadMoreProgress();
}
