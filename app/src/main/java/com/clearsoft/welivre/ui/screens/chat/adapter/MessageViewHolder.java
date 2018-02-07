package com.clearsoft.welivre.ui.screens.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by on 17.07.17.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder{



    public MessageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }
}
