package com.clearsoft.welivre.ui.screens.more;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.MoreComponent;
import com.clearsoft.welivre.ui.screens.auth.login.LoginActivity;
import com.clearsoft.welivre.ui.screens.awards.AwardsActivity;
import com.clearsoft.welivre.ui.screens.health.HealthActivity;
import com.clearsoft.welivre.ui.screens.more.about.AboutActivity;
import com.clearsoft.welivre.ui.screens.more.invite.InviteActivity;
import com.clearsoft.welivre.ui.screens.more.smoking.situation.SmokingSituationActivity;
import com.clearsoft.welivre.ui.screens.my_plan.MyPlanActivity;
import com.clearsoft.welivre.ui.screens.settings.SettingsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 05.07.17.
 */

public class MoreActivity extends PanelActivity implements MoreView {

    @Inject
    MorePresenter mMorePresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MoreActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMorePresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMorePresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new MoreComponent.Module())
                .inject(this);
    }

    @Override
    public void openLogin() {
        LoginActivity.start(this);
        finish();
    }

    @Override
    public void openAwards() {
        AwardsActivity.start(this);
    }

    @Override
    public void openHealth() {
        HealthActivity.start(this);
    }

    @Override
    public void openSmoked() {
        SmokingSituationActivity.start(this);
    }

    @Override
    public void openMyPlan() {
        MyPlanActivity.start(this);
    }

    @Override
    public void openInvite() {
        InviteActivity.start(this);
    }

    @Override
    public void openSettings() {
        SettingsActivity.start(this);
    }

    @Override
    public void closeAll() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void openRateAppScreen() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void openAbout() {
        AboutActivity.start(this);
    }

    @OnClick(R.id.toolbar_more_back_btn)
    public void closeMore(){
        finish();
    }


    @OnClick(R.id.more_about_view)
    public void onAbout(){
        mMorePresenter.onAboutClick();
    }

    @OnClick(R.id.more_rate_view)
    public void rateApp(){
        mMorePresenter.onRateAppClick();
    }

    @OnClick(R.id.more_logout_view)
    public void onLogoutClick(){
        mMorePresenter.onLogoutClick();
    }

    @OnClick(R.id.more_awards_view)
    public void onAwardsClick(){
        mMorePresenter.onAwardsClick();
    }

    @OnClick(R.id.more_health_view)
    public void onHealthClick(){
        mMorePresenter.onHealthClick();
    }

    @OnClick(R.id.more_smoked_view)
    public void onSmokedClick(){
        mMorePresenter.onSmokedClick();
    }

    @OnClick(R.id.more_my_plan_view)
    public void onPlanClick(){
        mMorePresenter.onMyPlanClick();
    }

    @OnClick(R.id.more_settings_view)
    public void onSetiingsClick(){
        mMorePresenter.onSettingsClick();
    }
    @OnClick(R.id.more_invite_view)
    public void onInviteClick(){
        mMorePresenter.onInviteClick();
    }

    @OnClick(R.id.more_delete_account_view)
    public void onDeleteAccClick(){
        mMorePresenter.onDeleteClick();
    }
}
