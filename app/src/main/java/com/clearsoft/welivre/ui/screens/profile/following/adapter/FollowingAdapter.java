package com.clearsoft.welivre.ui.screens.profile.following.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.FollowingDvo;
import com.clearsoft.welivre.ui.dvo.FollowingListDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 01.08.17.
 */

public class FollowingAdapter extends RecyclerView.Adapter<FollowingViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<FollowingDvo> data = new ArrayList<>();
    private OnItemClickListener listener;

    public FollowingAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public FollowingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FollowingViewHolder(mInflater.inflate(
                R.layout.profile_following_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(FollowingViewHolder holder, int position) {
        FollowingDvo dvo = getData().get(position);
        holder.init(dvo);
        holder.itemView.setOnClickListener(v -> {
            listener.openUserProfile(dvo.getUserId());
        });
        holder.vFollowBtn.setOnClickListener(v -> {
            listener.follow(dvo.getUserId(), dvo.getFollowId(), dvo.isFollowed());
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<FollowingDvo> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<FollowingDvo> getData() {
        return data;
    }

    public void updateFollow(String followId, boolean isFollow) {
        int possition = 0;
        for (FollowingDvo dvo : getData()){
            if (dvo.getFollowId().equals(followId)){
                dvo.setFollowed(isFollow);
                break;
            }
            possition ++;
        }
        notifyItemChanged(possition);

    }

    public interface OnItemClickListener {
        void openUserProfile(String userId);

        void follow(String userId, String followId, boolean isFollow);
    }
}
