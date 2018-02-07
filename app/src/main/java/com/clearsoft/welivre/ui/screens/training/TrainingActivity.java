package com.clearsoft.welivre.ui.screens.training;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.TrainingComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by on 25.07.17.
 */

public class TrainingActivity extends PanelActivity implements TrainingView{

    @Inject
    TrainingPresenter mTrainingPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTrainingPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTrainingPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new TrainingComponent.Module())
                .inject(this);
    }
}
