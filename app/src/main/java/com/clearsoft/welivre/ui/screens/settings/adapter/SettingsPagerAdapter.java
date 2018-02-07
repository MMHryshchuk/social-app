package com.clearsoft.welivre.ui.screens.settings.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.clearsoft.welivre.ui.screens.settings.privacy.PrivacyFragment;
import com.clearsoft.welivre.ui.screens.settings.profile.SettingsProfileFragment;
import com.clearsoft.welivre.ui.screens.settings.questionaries.QuestionariesFragment;

/**
 * Created by on 25.07.17.
 */

public class SettingsPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public SettingsPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SettingsProfileFragment profile = new SettingsProfileFragment();
                return profile;
            case 1:
                QuestionariesFragment questionaries = new QuestionariesFragment();
                return questionaries;
            case 2:
                PrivacyFragment privacy = new PrivacyFragment();
                return privacy;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
