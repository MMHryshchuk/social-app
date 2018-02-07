package com.clearsoft.welivre.ui.screens.more.smoking.situation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.SmokingSituationComponent;
import com.clearsoft.welivre.ui.screens.more.smoking.SmokingSecondActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 17.07.17.
 */

public class SmokingSituationActivity extends PanelActivity implements SmokingSituationView{

    @BindView(R.id.activity_situation_choice_1)
    RelativeLayout vCheckLay1;
    @BindView(R.id.activity_situation_choice_2)
    RelativeLayout vCheckLay2;
    @BindView(R.id.activity_situation_choice_3)
    RelativeLayout vCheckLay3;
    @BindView(R.id.activity_situation_choice_4)
    RelativeLayout vCheckLay4;
    @BindView(R.id.activity_situation_choice_1_check_box)
    ImageView vCheck1;
    @BindView(R.id.activity_situation_choice_2_check_box)
    ImageView vCheck2;
    @BindView(R.id.activity_situation_choice_3_check_box)
    ImageView vCheck3;
    @BindView(R.id.activity_situation_choice_4_check_box)
    ImageView vCheck4;

    @BindView(R.id.activity_situation_choice_bg_1)
    ImageView vBg1;
    @BindView(R.id.activity_situation_choice_bg_2)
    ImageView vBg2;
    @BindView(R.id.activity_situation_choice_bg_3)
    ImageView vBg3;
    @BindView(R.id.activity_situation_choice_bg_4)
    ImageView vBg4;

    @BindView(R.id.activity_smoked_first_progress_frame)
    FrameLayout vProgress;

    private final int CHECK_BOX_CHECK = R.drawable.situation_check_cell_ic;
    private final int CHECK_BOX_UNCHECK = R.drawable.situation_uncheck_ic;
    private String situation;

    @Inject
    SmokingSituationPresenter mSmokingPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity,SmokingSituationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        ButterKnife.bind(this);
        setupDagger();
        setupClickListeners();
        vCheck1.setImageResource(CHECK_BOX_CHECK);
        vBg1.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSmokingPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSmokingPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new SmokingSituationComponent.Module())
                .inject(this);
    }


    private void setupClickListeners(){
        vCheckLay1.setOnClickListener(v -> {
            setViewCheck(vCheck1,vBg1);
            situation = getResources().getString(R.string.complete_first_choice_1);

        });
        vCheckLay2.setOnClickListener(v ->{
            setViewCheck(vCheck2,vBg2);
            situation = getResources().getString(R.string.complete_first_choice_2);
        });
        vCheckLay3.setOnClickListener(v -> {
            setViewCheck(vCheck3,vBg3);
            situation = getResources().getString(R.string.complete_first_choice_3);
        });
        vCheckLay4.setOnClickListener(v -> {
            setViewCheck(vCheck4,vBg4);
            situation = getResources().getString(R.string.complete_first_choice_4);
        });



    }

    @Override
    public void setViewCheck(ImageView vCheck, ImageView vBg) {
        vCheck1.setImageResource(CHECK_BOX_UNCHECK);
        vBg1.setVisibility(View.GONE);
        vCheck2.setImageResource(CHECK_BOX_UNCHECK);
        vBg2.setVisibility(View.GONE);
        vCheck3.setImageResource(CHECK_BOX_UNCHECK);
        vBg3.setVisibility(View.GONE);
        vCheck4.setImageResource(CHECK_BOX_UNCHECK);
        vBg4.setVisibility(View.GONE);


        vCheck.setImageResource(CHECK_BOX_CHECK);
        vBg.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void openNext() {
        SmokingSecondActivity.start(this);
        finish();
    }

    @OnClick(R.id.activity_situation_next)
    public void onNextClick(){
        mSmokingPresenter.onNextClick(situation);
    }

    @OnClick(R.id.toolbar_more_back_btn)
    public void closeSituation(){
        finish();
    }

}
