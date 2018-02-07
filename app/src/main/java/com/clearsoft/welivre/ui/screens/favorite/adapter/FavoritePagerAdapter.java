package com.clearsoft.welivre.ui.screens.favorite.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.SplashDvo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by on 05.07.17.
 */

public class FavoritePagerAdapter extends PagerAdapter {


    FrameLayout authorsFrame;
    FrameLayout categoriesFrame;
    private final List<FrameLayout> mFramesList = new ArrayList<>();
    private final List<String> mFrameTittleList = new ArrayList<>();

    public FavoritePagerAdapter() {
    }

    public Object instantiateItem(ViewGroup container, int position) {
        return mFramesList.get(position);
    }

    @Override
    public int getCount() {
        return mFramesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFrameTittleList.get(position);
    }

    public void addFrame(List<FrameLayout> frames , List<String> titles){
        mFramesList.clear();
        mFrameTittleList.clear();
        mFramesList.addAll(frames);
        mFrameTittleList.addAll(titles);

    }
}
