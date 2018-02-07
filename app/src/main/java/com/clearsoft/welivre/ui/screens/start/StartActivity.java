package com.clearsoft.welivre.ui.screens.start;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.screens.home.HomeActivity;
import com.clearsoft.welivre.ui.screens.splash.SplashActivity;

import javax.inject.Inject;

/**
 * Created by on 04.07.17.
 */

public class StartActivity extends BaseActivity {

    @Inject
    protected PreferenceRepository preferenceRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
        String name = preferenceRepository.getUserName();
        String token = preferenceRepository.getAccessToken();
        if (StringUtils.isNullEmpty(preferenceRepository.getAccessToken())){
            SplashActivity.start(this);
        }else {
            HomeActivity.start(this);
        }
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
