package com.clearsoft.welivre.core.android;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;


/**
 * Created by vladimir on 07.06.16.
 */
public class BaseFragment extends Fragment {

    protected void setupToolbar(Toolbar toolbar, String text, int icon, View.OnClickListener onNavigationClickListener){
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(text);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(onNavigationClickListener);
    }

    protected void setupToolbarSecondary(Toolbar toolbar, String text, int icon,int iconRight , View.OnClickListener onNavigationClickListener, View.OnClickListener iconClickListener){

    }
}
