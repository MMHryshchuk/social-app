package com.clearsoft.welivre.ui.screens.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by on 17.07.17.
 */

public class MyMessageViewHolder extends RecyclerView.ViewHolder{


    public MyMessageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
