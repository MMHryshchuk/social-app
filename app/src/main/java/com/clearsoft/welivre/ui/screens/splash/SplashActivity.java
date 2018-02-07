package com.clearsoft.welivre.ui.screens.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.ui.di.SplashComponent;
import com.clearsoft.welivre.ui.dvo.SplashDvo;
import com.clearsoft.welivre.ui.screens.auth.login.LoginActivity;
import com.clearsoft.welivre.ui.screens.home.HomeActivity;
import com.clearsoft.welivre.ui.screens.splash.adapter.SplashPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by on 27.06.17.
 */

public class SplashActivity extends BaseActivity implements SplashView {

    @BindView(R.id.activity_splash_view_pager)
    ViewPager vViewPager;
    @BindView(R.id.activity_splash_next_lay)
    LinearLayout vNext;
    @BindView(R.id.activity_splash_back_lay)
    LinearLayout vBack;
    @BindView(R.id.first_dot_image)
    ImageView vFirst;
    @BindView(R.id.second_dot_image)
    ImageView vSecond;
    @BindView(R.id.third_dot_image)
    ImageView vThird;
    @BindView(R.id.fourth_dot_image)
    ImageView vFourth;
    @BindView(R.id.fiveth_dot_image)
    ImageView vFiveth;

    private final int DOT_DEFOULT = R.drawable.unselected_dot_ic;
    private final int DOT_SELECTED = R.drawable.selected_dot_ic;

    @Inject
    SplashPresenter mSplashPresenter;
    private SplashPagerAdapter mPagerAdapter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, SplashActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setupDagger();
        initViewPager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSplashPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSplashPresenter.detachView();
    }


    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new SplashComponent.Module())
                .inject(this);
    }

    private void initViewPager() {
        mPagerAdapter = new SplashPagerAdapter(this);
        vViewPager.setAdapter(mPagerAdapter);
        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                showIndicatorPage(position);
                enableBack(position != 0);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void showData(List<SplashDvo> dvos) {
        mPagerAdapter.addData(dvos);
    }

    @Override
    public void openLogin() {
        LoginActivity.start(this);
        finish();
    }

    @Override
    public void showNext() {
        if (vViewPager.getCurrentItem() < mPagerAdapter.getCount() - 1) {
            vViewPager.setCurrentItem(vViewPager.getCurrentItem() + 1);
        } else {
            openLogin();
        }
    }

    @Override
    public void showPrev() {
        vViewPager.setCurrentItem(vViewPager.getCurrentItem() - 1);
    }

    @Override
    public void showIndicatorPage(int page) {
        vFirst.setImageResource(DOT_DEFOULT);
        vSecond.setImageResource(DOT_DEFOULT);
        vThird.setImageResource(DOT_DEFOULT);
        vFourth.setImageResource(DOT_DEFOULT);
        vFiveth.setImageResource(DOT_DEFOULT);
        switch (page) {
            case 0:
                vFirst.setImageResource(DOT_SELECTED);
                break;
            case 1:
                vSecond.setImageResource(DOT_SELECTED);
                break;

            case 2:
                vThird.setImageResource(DOT_SELECTED);
                break;

            case 3:
                vFourth.setImageResource(DOT_SELECTED);
                break;

            case 4:
                vFiveth.setImageResource(DOT_SELECTED);
                break;

            default:
                return;

        }
    }

    @Override
    public void enableBack(boolean enable) {
        vBack.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.activity_splash_next_lay)
    public void onNextClick() {
        mSplashPresenter.onNextClick();
    }

    @OnClick(R.id.activity_splash_back_lay)
    public void onBackCLick() {
        mSplashPresenter.onBackClick();
    }

    @OnClick(R.id.toolbar_splash_skip_btn)
    public void onSkipClick(){
        mSplashPresenter.onSkipClick();
    }
}
