package com.clearsoft.welivre.ui.screens.profile.edit_about;

import com.clearsoft.welivre.core.mvp.Presenter;

/**
 * Created by on 11.08.17.
 */

public interface EditAboutPresenter extends Presenter<EditAboutView>{

    void saveAbout(String aboutTxt);
}
