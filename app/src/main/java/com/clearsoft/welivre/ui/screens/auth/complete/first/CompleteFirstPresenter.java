package com.clearsoft.welivre.ui.screens.auth.complete.first;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;
import com.clearsoft.welivre.ui.screens.auth.register.RegisterActivity;

/**
 * Created by on 28.06.17.
 */

public interface CompleteFirstPresenter extends Presenter<CompleteFirstView> {
    void onNextClick(CompleteRegisterActivity activity, int situation);
}
