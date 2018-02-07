package com.clearsoft.welivre.ui.screens.messenger.contacts;

import com.clearsoft.welivre.core.mvp.BasePresenter;

/**
 * Created by on 03.07.17.
 */

public class ContactPresenterImpl extends BasePresenter<ContactsView> implements ContactsPresenter {

    public ContactPresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        getView().setActiveFollowersBtn();
    }
}
