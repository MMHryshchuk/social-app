package com.clearsoft.welivre.ui.screens.profile.edit_about;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.bus.Bus;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.events.AboutEvent;

import javax.inject.Inject;

/**
 * Created by on 11.08.17.
 */

public class EditAboutPresenterImpl extends BasePresenter<EditAboutView> implements EditAboutPresenter {

    App app;
    UserUseCase userUseCase;
    Bus bus;

    @Inject
    public EditAboutPresenterImpl(App app, UserUseCase userUseCase, Bus bus) {
        this.app = app;
        this.userUseCase = userUseCase;
        this.bus = bus;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void saveAbout(String aboutTxt) {
        if (getView() == null) return;
        getView().showProgress();
        addSubscription(userUseCase.createUpdateAbout(aboutTxt).subscribe(
                next -> {
                    bus.post(new AboutEvent("Succes"));
                    getView().hideProgress();
                    getView().closeEdit();

                }
        ));
    }
}
