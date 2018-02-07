package com.clearsoft.welivre.ui.screens.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.HealthComponent;
import com.clearsoft.welivre.ui.dvo.HealthDvo;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 06.07.17.
 */

public class HealthActivity extends PanelActivity implements HealthView {

    @BindView(R.id.activity_health_progress_bar_1)
    CircularProgressBar vProgress1;
    @BindView(R.id.activity_health_progress_bar_2)
    CircularProgressBar vProgress2;
    @BindView(R.id.activity_health_progress_bar_3)
    CircularProgressBar vProgress3;
    @BindView(R.id.activity_health_progress_bar_4)
    CircularProgressBar vProgress4;
    @BindView(R.id.activity_health_progress_bar_5)
    CircularProgressBar vProgress5;
    @BindView(R.id.activity_health_progress_bar_6)
    CircularProgressBar vProgress6;
    @BindView(R.id.activity_health_progress_bar_7)
    CircularProgressBar vProgress7;
    @BindView(R.id.activity_health_progress_bar_8)
    CircularProgressBar vProgress8;
    @BindView(R.id.activity_health_progress_bar_9)
    CircularProgressBar vProgress9;
    @BindView(R.id.activity_health_progress_bar_10)
    CircularProgressBar vProgress10;
    @BindView(R.id.activity_health_progress_percent_1)
    TextView vProgressPercent1;
    @BindView(R.id.activity_health_progress_percent_2)
    TextView vProgressPercent2;
    @BindView(R.id.activity_health_progress_percent_3)
    TextView vProgressPercent3;
    @BindView(R.id.activity_health_progress_percent_4)
    TextView vProgressPercent4;
    @BindView(R.id.activity_health_progress_percent_5)
    TextView vProgressPercent5;
    @BindView(R.id.activity_health_progress_percent_6)
    TextView vProgressPercent6;
    @BindView(R.id.activity_health_progress_percent_7)
    TextView vProgressPercent7;
    @BindView(R.id.activity_health_progress_percent_8)
    TextView vProgressPercent8;
    @BindView(R.id.activity_health_progress_percent_9)
    TextView vProgressPercent9;
    @BindView(R.id.activity_health_progress_percent_10)
    TextView vProgressPercent10;


    @BindView(R.id.toolbar_title)
    Toolbar vToolbar;


    @Inject
    HealthPresenter mHealthPresenter;


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, HealthActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ButterKnife.bind(this);
        setupDagger();
        initToolbar();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mHealthPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHealthPresenter.detachView();
    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new HealthComponent.Module())
                .inject(this);
    }

    private void initToolbar() {
        TextView vTitle = ButterKnife.findById(vToolbar, R.id.toolbar_title_text);
        vTitle.setText("Health");
    }

    @Override
    public void showData(HealthDvo dvo) {
        setProgressColor(vProgress1, vProgressPercent1, dvo.getMin20());
        setProgressColor(vProgress2, vProgressPercent2, dvo.getHour2());
        setProgressColor(vProgress3, vProgressPercent3, dvo.getHour8());
        setProgressColor(vProgress4, vProgressPercent4, dvo.getHour12());
        setProgressColor(vProgress5, vProgressPercent5, dvo.getDays2());
        setProgressColor(vProgress6, vProgressPercent6, dvo.getWeek3());
        setProgressColor(vProgress7, vProgressPercent7, dvo.getMonth1());
        setProgressColor(vProgress8, vProgressPercent8, dvo.getYear1());
        setProgressColor(vProgress9, vProgressPercent9, dvo.getYear2());
        setProgressColor(vProgress10, vProgressPercent10, dvo.getYear10());
    }

    private void setProgressColor(CircularProgressBar progressBar, TextView percent, int value) {
        progressBar.setProgress(value);
        percent.setText(value+"%");
        if (value> 0 && value < 30){
            progressBar.setColor(ContextCompat.getColor(this, R.color.coloProgress1));
        }else if (value > 30 && value < 60){
            progressBar.setColor(ContextCompat.getColor(this, R.color.coloProgress2));
        } else if (value > 60 && value < 100) {
            progressBar.setColor(ContextCompat.getColor(this, R.color.coloProgress3));
        } else if (value == 100) {
            progressBar.setColor(ContextCompat.getColor(this, R.color.coloProgress4));
        }
    }
}
