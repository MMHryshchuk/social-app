package com.clearsoft.welivre.ui.screens.more;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 05.07.17.
 */

public interface MorePresenter extends Presenter<MoreView> {
    void onLogoutClick();
    void onAwardsClick();

    void onHealthClick();
    void onSmokedClick();
    void onMyPlanClick();
    void onSettingsClick();
    void onInviteClick();
    void onDeleteClick();
    void onRateAppClick();

    void onAboutClick();
}
