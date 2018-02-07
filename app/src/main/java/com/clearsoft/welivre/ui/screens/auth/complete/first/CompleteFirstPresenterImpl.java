package com.clearsoft.welivre.ui.screens.auth.complete.first;

import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;
import com.clearsoft.welivre.ui.screens.auth.register.RegisterActivity;

/**
 * Created by on 28.06.17.
 */

public class CompleteFirstPresenterImpl extends BasePresenter<CompleteFirstView> implements CompleteFirstPresenter {

    public CompleteFirstPresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(CompleteRegisterActivity activity, int situation) {
        if (getView() == null) return;
        activity.showSecondFragment(situation);
    }
}
