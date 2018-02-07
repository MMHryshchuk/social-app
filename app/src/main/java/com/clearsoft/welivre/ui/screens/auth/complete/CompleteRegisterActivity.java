package com.clearsoft.welivre.ui.screens.auth.complete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.ui.di.CompleteRegisterComponent;
import com.clearsoft.welivre.ui.dvo.CompleteRegDvo;
import com.clearsoft.welivre.ui.screens.auth.complete.first.CompleteFirstFragment;
import com.clearsoft.welivre.ui.screens.auth.complete.first.CompleteFirstPresenter;
import com.clearsoft.welivre.ui.screens.auth.complete.second.CompleteSecondFragment;
import com.clearsoft.welivre.ui.screens.auth.complete.second.CompleteSecondPresenter;
import com.clearsoft.welivre.ui.screens.auth.complete.third.CompleteThirdFragment;
import com.clearsoft.welivre.ui.screens.auth.complete.third.CompleteThirdPresenter;
import com.clearsoft.welivre.ui.screens.auth.login.LoginActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by on 28.06.17.
 */

public class CompleteRegisterActivity extends BaseActivity {

    @Inject
    CompleteFirstPresenter mFirstPresenter;
    @Inject
    CompleteSecondPresenter mSecondPresenter;
    @Inject
    CompleteThirdPresenter mThirdPresenter;

    private CompleteRegDvo dvo;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, CompleteRegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_register);
        setupDagger();
        ButterKnife.bind(this);
        dvo = new CompleteRegDvo();
        replaceFragment(mFirstPresenter, new CompleteFirstFragment());

    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new CompleteRegisterComponent.Module())
                .inject(this);
    }

    public void showSecondFragment(int situation) {
        dvo.setSituation(situation);
        replaceFragment(mSecondPresenter, new CompleteSecondFragment());
    }

    public void showThirdFragment(String ciggNum, String ciggCost, String ciggTime) {
        dvo.setCigaDayliNum(ciggNum);
        dvo.setCigaPackCost(ciggCost);
        dvo.setCigaWakeUpMinutes(ciggTime);
        replaceFragment(mThirdPresenter, new CompleteThirdFragment());
    }

    public CompleteRegDvo getRegDvo(){
        return dvo;
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof CompleteFirstFragment) {
            finish();
        } else if (currentFragment instanceof CompleteSecondFragment)
            replaceFragment(mFirstPresenter, new CompleteFirstFragment());
        else if (currentFragment instanceof CompleteThirdFragment)
            replaceFragment(mSecondPresenter, new CompleteSecondFragment());
    }

}
