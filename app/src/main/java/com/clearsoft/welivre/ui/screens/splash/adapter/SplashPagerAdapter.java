package com.clearsoft.welivre.ui.screens.splash.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.ui.dvo.SplashDvo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by on 27.06.17.
 */

public class SplashPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mInfalter;
    private final List<SplashDvo> mData = new ArrayList<>();

    public SplashPagerAdapter(Context context) {
        this.context = context;
        this.mInfalter = LayoutInflater.from(context);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInfalter.inflate(R.layout.splash_item,container,false);
        SplashDvo dvo = mData.get(position);
        ImageView image = ButterKnife.findById(view,R.id.splash_item_img);
        TextView title = ButterKnife.findById(view,R.id.splash_item_title);
        TextView description = ButterKnife.findById(view,R.id.splash_item_description);
        container.addView(view);
        image.setImageResource(dvo.getImage());
        title.setText(dvo.getTittle());
        description.setText(dvo.getDescriptions());
        return view;
    }

    @Override
    public int getCount() {
        return mData.size();
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
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void addData(List<SplashDvo> data){
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }
}
