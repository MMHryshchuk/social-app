package com.clearsoft.welivre.ui.screens.comment;

import com.clearsoft.welivre.ui.dvo.CommentDvo;

import java.util.List;

/**
 * Created by on 15.07.17.
 */

public interface CommentView {

    void showData(List<CommentDvo> data);
    void clearField();
}
