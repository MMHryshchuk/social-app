package com.clearsoft.welivre.ui.screens.profile.posts;

import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;

/**
 * Created by on 15.07.17.
 */

public interface PostsView {
    void showData(FeedListDvo feedListDvo);

    void updateFeedItem(FeedDvo feedDvo);

    void openComments(int postId,int postLikes);

    void openMoreScreen(FeedDvo dvo);
}
