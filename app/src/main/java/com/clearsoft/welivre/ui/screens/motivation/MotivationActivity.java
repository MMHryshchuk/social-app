package com.clearsoft.welivre.ui.screens.motivation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.MotivationComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 18.07.17.
 */

public class MotivationActivity extends PanelActivity implements MotivationView {


    @Inject
    MotivationPresenter mMotivationPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MotivationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMotivationPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMotivationPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new MotivationComponent.Module())
                .inject(this);
    }

    @Override
    public void close() {
        finish();
    }

    @OnClick(R.id.toolbar_more_back_btn)
    public void onBackClick(){
        mMotivationPresenter.onBackClick();
    }


}
