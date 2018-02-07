package com.clearsoft.welivre.ui.screens.splash;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.mvp.BasePresenter;
import com.clearsoft.welivre.ui.dvo.SplashDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 27.06.17.
 */

public class SplashPresenterImpl extends BasePresenter<SplashView> implements SplashPresenter {


    private App app;

    public SplashPresenterImpl(App app) {
        this.app = app;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        List<SplashDvo> data = new ArrayList<>();
        data.add(new SplashDvo(R.drawable.splash_img_0,app.getString(R.string.splash_title_0),app.getString(R.string.splash_description_0)));
        data.add(new SplashDvo(R.drawable.splash_img_1,app.getString(R.string.splash_title_1),app.getString(R.string.splash_description_1)));
        data.add(new SplashDvo(R.drawable.splash_img_2,app.getString(R.string.splash_title_2),app.getString(R.string.splash_description_2)));
        data.add(new SplashDvo(R.drawable.splash_img_3,app.getString(R.string.splash_title_3),app.getString(R.string.splash_description_3)));
        data.add(new SplashDvo(R.drawable.splash_img_4,app.getString(R.string.splash_title_4),app.getString(R.string.splash_description_4)));
        getView().showData(data);
    }


    @Override
    public void onNextClick() {
        if (getView() == null) return;
        getView().showNext();

    }

    @Override
    public void onBackClick() {
        if (getView() == null) return;
        getView().showPrev();
    }

    @Override
    public void onSkipClick() {
        if (getView() == null) return;
        getView().openLogin();

    }
}
