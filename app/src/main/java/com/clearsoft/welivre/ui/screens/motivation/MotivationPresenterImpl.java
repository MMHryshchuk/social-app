package com.clearsoft.welivre.ui.screens.motivation;

import com.clearsoft.welivre.core.mvp.BasePresenter;

/**
 * Created by on 18.07.17.
 */

public class MotivationPresenterImpl extends BasePresenter<MotivationView> implements MotivationPresenter {

    public MotivationPresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onBackClick() {
        if (getView() == null)return;
        getView().close();
    }
}
