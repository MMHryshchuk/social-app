package com.clearsoft.welivre.ui.screens.article;

import com.clearsoft.welivre.core.mvp.BasePresenter;

/**
 * Created by on 30.06.17.
 */

public class ArticlePresenterImpl extends BasePresenter<ArticleView> implements ArticlePresenter {

    public ArticlePresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onBackClick() {
        if (getView() == null) return;
        getView().close();
    }
}
