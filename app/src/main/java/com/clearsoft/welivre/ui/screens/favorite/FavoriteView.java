package com.clearsoft.welivre.ui.screens.favorite;

import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;

import java.util.List;

/**
 * Created by on 05.07.17.
 */

public interface FavoriteView  {

    void showPostRecycler();
    void showArticleRecycler();
    void showPostProgress();
    void hidePostProgress();
    void showArticleProgress();
    void hideArticleProgress();
    void showData(FeedListDvo data);

    void openComments(int postId, int postLikes);


    void updateFeedItem(FeedDvo feedDvo);
}
