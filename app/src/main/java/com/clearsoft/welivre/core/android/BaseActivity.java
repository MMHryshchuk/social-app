package com.clearsoft.welivre.core.android;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;


import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.bus.IgnoreEvent;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;

import com.clearsoft.welivre.core.bus.EventBus;
import com.clearsoft.welivre.core.mvp.Presenter;

import org.greenrobot.eventbus.Subscribe;


/**
 */
public class BaseActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        App.getApp(this).getAppComponent().getBus().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppForegroundStateManager.getInstance().onActivityVisible(this);
    }

    @Override
    protected void onStop() {
        AppForegroundStateManager.getInstance().onActivityNotVisible(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getApp(this).getAppComponent().getBus().unRegister(this);
    }

    protected <F extends ViewMvpFragment> F replaceFragment(Presenter presenter, F fragment, MvpFragmentTransactionCallback fragmentTransactionCallback){
        Fragment oldFragment = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getName());
        if (oldFragment == null){
            oldFragment = fragment;
            FragmentTransaction fragmentTransaction = replaceFragment(fragment, fragment.getClass().getName());
            fragmentTransactionCallback.afterReplace(fragmentTransaction);
            fragmentTransaction.commit();
        }
        ((ViewMvpFragment)oldFragment).attachPresenter(presenter);
        return (F)oldFragment;
    }

    protected <F extends ViewMvpFragment> F replaceFragment(Presenter presenter, F fragment){
        return replaceFragment(presenter, fragment, fragmentTransaction -> {});
    }

    protected <T> Optional<T> findFragment(Class<T> tClass){
        return new Optional<>((T) getSupportFragmentManager().findFragmentByTag(tClass.getName()));
    }

    protected FragmentTransaction replaceFragment(Fragment fragment, String tag){
        return getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag);
    }

    @Subscribe
    @EventBus
    public void onEvent(IgnoreEvent ignore){

    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public interface MvpFragmentTransactionCallback {
        void afterReplace(FragmentTransaction fragmentTransaction);
    }
}
