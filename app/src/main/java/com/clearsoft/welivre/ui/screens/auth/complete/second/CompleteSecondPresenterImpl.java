package com.clearsoft.welivre.ui.screens.auth.complete.second;

import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;

/**
 * Created by on 28.06.17.
 */

public class CompleteSecondPresenterImpl extends BasePresenter<CompleteSecondView> implements CompleteSecondPresenter {

    public CompleteSecondPresenterImpl() {
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onNextClick(CompleteRegisterActivity activity,String ciggNum, String ciggCost, String ciggTime) {
        activity.showThirdFragment(ciggNum, ciggCost, ciggTime);
    }
}
