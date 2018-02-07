package com.clearsoft.welivre.ui.screens.profile.about;

import com.clearsoft.welivre.core.mvp.Presenter;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 01.08.17.
 */

public interface AboutPresenter extends Presenter<AboutView> {

    void onLikeClick();
    void onCommentClick();

    void update();
    void loadMyPlan();
}
