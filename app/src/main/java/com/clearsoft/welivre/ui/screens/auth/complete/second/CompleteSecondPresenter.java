package com.clearsoft.welivre.ui.screens.auth.complete.second;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;

/**
 * Created by on 28.06.17.
 */

public interface CompleteSecondPresenter extends Presenter<CompleteSecondView> {

    void onNextClick(CompleteRegisterActivity activity, String ciggNum, String ciggCost, String ciggTime);
}
