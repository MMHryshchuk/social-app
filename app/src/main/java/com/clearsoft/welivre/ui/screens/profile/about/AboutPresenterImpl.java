package com.clearsoft.welivre.ui.screens.profile.about;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.dvo.AboutDvo;

/**
 * Created by on 01.08.17.
 */

public class AboutPresenterImpl extends BasePresenter<AboutView> implements AboutPresenter {

    App app;
    UserUseCase userUseCase;
    PlanUseCase planUseCase;
    private final int targetId;
    private AboutDvo dvo;

    public AboutPresenterImpl(App app, UserUseCase userUseCase, PlanUseCase planUseCase, int targetId) {
        this.app = app;
        this.userUseCase = userUseCase;
        this.planUseCase = planUseCase;
        this.targetId = targetId;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        addSubscription(userUseCase.getUserAbout(targetId).subscribe(
                next -> {
                    dvo = next;
                    getView().showData(next);
                }
        ));
    }


    @Override
    public void onLikeClick() {
        if (dvo.isLiked()){
            unLikeAbout();
        }else {
            likeAbout();
        }
    }

    @Override
    public void onCommentClick() {
        getView().openComment(targetId,Integer.parseInt(dvo.getAboutLikeCount()));
    }

    @Override
    public void update() {
        addSubscription(userUseCase.getUserAbout(targetId).subscribe(
                next -> {
                    dvo = next;
                    getView().showData(next);
                }
        ));
    }

    @Override
    public void loadMyPlan() {
        if (getView() == null) return;
        getView().showProgress();
        addSubscription(planUseCase.getMyPlan(app).subscribe(
                next -> {
                    getView().hideProgress();
                    getView().showPlanData(next);
                },
                error -> {

                }
        ));
    }

    private void likeAbout() {
        addSubscription(userUseCase.likeAbout(targetId).subscribe(
                next ->{
                    dvo.setLiked(true);
                    dvo.setAboutLikeCount(String.valueOf(Integer.parseInt(dvo.getAboutLikeCount())+1));
                    getView().updateAbout(dvo);
                }
        ));
    }


    private void unLikeAbout() {
        addSubscription(userUseCase.likeAbout(targetId).subscribe(
                next ->{
                    dvo.setLiked(false);
                    dvo.setAboutLikeCount(String.valueOf(Integer.parseInt(dvo.getAboutLikeCount())-1));
                    getView().updateAbout(dvo);
                }
        ));
    }


}
