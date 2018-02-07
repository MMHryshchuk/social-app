package com.clearsoft.welivre.ui.dvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 31.07.17.
 */

public class CommentsListDvo {

    private List<CommentDvo> commentDvos ;
    private long lastPage;

    public CommentsListDvo(List<CommentDvo> commentDvos) {
        this.commentDvos = commentDvos;
        this.lastPage = -1;
    }

    public CommentsListDvo(List<CommentDvo> commentDvos, long lastPage) {
        this.commentDvos = commentDvos;
        this.lastPage = lastPage;
    }

    public List<CommentDvo> getCommentDvos() {
        return commentDvos;
    }

    public void setCommentDvos(List<CommentDvo> commentDvos) {
        this.commentDvos = commentDvos;
    }

    public long getLastPage() {
        return lastPage;
    }

    public void setLastPage(long lastPage) {
        this.lastPage = lastPage;
    }
}
