package com.clearsoft.welivre.ui.screens.comment;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 15.07.17.
 */

public interface CommentPresenter extends Presenter<CommentView> {

    void postComment(String txt,int post_id);
    void deleteComment(int commentId);
    void loadMoreComment();
}
