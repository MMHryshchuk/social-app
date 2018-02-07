package com.clearsoft.welivre.ui.screens.favorite;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 05.07.17.
 */

public interface FavoritePresenter extends Presenter<FavoriteView>{

    void onLikeClick(int postId);
    void onUnLikeClick(int postId);
    void onFavoriteClick(int postId);
    void onUnFavoriteClick(int postId);
    void onCommentClick(int postId,int postLikes);
    void update();
}
