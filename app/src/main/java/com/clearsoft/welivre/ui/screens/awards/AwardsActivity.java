package com.clearsoft.welivre.ui.screens.awards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.di.AwardsComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 02.07.17.
 */

public class AwardsActivity extends PanelActivity implements AwardsView{

    @BindView(R.id.toolbar_title)
    Toolbar vToolbar;
    @BindView(R.id.activity_awards_name)
    TextView vName;


    @Inject
    AwardsPresenter mAwardsPresenter;
    @Inject
    PreferenceRepository preferenceRepository;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, AwardsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        ButterKnife.bind(this);
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
        setupDagger();
        initToolbar();
        vName.setText(preferenceRepository.getUserName());

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAwardsPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAwardsPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new AwardsComponent.Module())
                .inject(this);
    }

    private void initToolbar(){
        TextView vTitle = ButterKnife.findById(vToolbar,R.id.toolbar_title_text);
        vTitle.setText("Awards");
    }
}
