package com.clearsoft.welivre.ui.screens.settings.questionaries;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;

/**
 * Created by on 25.07.17.
 */

public class QuestionariesPresenterImpl extends BasePresenter<QuestionariesView> implements QuestionariesPresenter {

    App app;
    UserUseCase userUseCase;

    public QuestionariesPresenterImpl(App app, UserUseCase userUseCase) {
        this.app = app;
        this.userUseCase = userUseCase;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(userUseCase.getUserQuestionaries().subscribe(
                next -> {
                    getView().showData(next);

                }
        ));
    }

    @Override
    public void onCalendarClick() {
        if (getView() == null) return;
        getView().openCalendar();
    }

    @Override
    public void onGenderClick(String gender) {
        if (getView() == null) return;
        getView().showGender(gender);
    }

    @Override
    public void saveChanges(int situation, String cigaDayliNum, String cigaPackCost, String cigaWakeUpMinutes, String cigaStartAge, String birthday, String gender) {
        if (getView() == null) return;
        getView().showProgress();
        addSubscription(userUseCase.setUserSituation(situation, cigaDayliNum, cigaPackCost, cigaWakeUpMinutes, cigaStartAge, birthday, gender).subscribe(
                next -> {
                    getView().hideProgress();
                    getView().showSucces();
                }
        ));
    }
}
