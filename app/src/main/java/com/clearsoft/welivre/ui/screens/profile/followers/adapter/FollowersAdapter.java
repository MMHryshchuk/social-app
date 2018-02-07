package com.clearsoft.welivre.ui.screens.profile.followers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.domain.entities.Follow;
import com.clearsoft.welivre.ui.dvo.FollowingDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 01.08.17.
 */

public class FollowersAdapter extends RecyclerView.Adapter<FollowersViewHolder> {


    private Context context;
    private LayoutInflater mInflater;
    private List<FollowingDvo> data = new ArrayList<>();
    private OnItemClickListener listener;

    public FollowersAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public FollowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FollowersViewHolder(mInflater.inflate(
                R.layout.profile_followers_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(FollowersViewHolder holder, int position) {
        FollowingDvo dvo = getData().get(position);
        holder.init(dvo);
        holder.itemView.setOnClickListener(v -> {
            listener.onUserClick(dvo.getUserId());
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

    public interface OnItemClickListener {
        void onUserClick(String userId);
    }
}
