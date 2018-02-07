package com.clearsoft.welivre.ui.screens.more.smoking.situation;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 18.07.17.
 */

public interface SmokingSituationPresenter extends Presenter<SmokingSituationView>{

    void onNextClick(String situation);
}
