package com.clearsoft.welivre.ui.screens.profile.following.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.dvo.FollowingDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 01.08.17.
 */

public class FollowingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.profile_following_item_follow_btn)
    public TextView vFollowBtn;
    @BindView(R.id.profile_following_item_image)
    ImageView vImage;
    @BindView(R.id.profile_following_item_name)
    TextView vName;
    @BindView(R.id.profile_following_item_followers_count)
    TextView vFollowersCount;


    public FollowingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void init(FollowingDvo dvo){
        if (!StringUtils.isNullEmpty(dvo.getUserImage()))
        ImageLoader.load(vImage,dvo.getUserImage(),true);
        vName.setText(dvo.getUserName());
        vFollowersCount.setText(dvo.getUserFollowers() + " Friends");
        vFollowBtn.setText(dvo.isFollowed()? "UNFOLLOW" : "FOLLOW");
    }
}
