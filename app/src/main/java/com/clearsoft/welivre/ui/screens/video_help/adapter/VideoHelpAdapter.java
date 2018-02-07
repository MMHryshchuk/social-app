package com.clearsoft.welivre.ui.screens.video_help.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;

/**
 * Created by on 25.07.17.
 */

public class VideoHelpAdapter extends RecyclerView.Adapter<VideoHelpViewHolder> {

    private Context context;
    private LayoutInflater mInflater;

    public VideoHelpAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public VideoHelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoHelpViewHolder(mInflater.inflate(
                R.layout.video_help_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(VideoHelpViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
