package com.clearsoft.welivre.ui.screens.messenger.chats.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 03.07.17.
 */

public class ChatListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.chat_list_item_image)
    ImageView vImage;
    @BindView(R.id.chat_list_item_name)
    TextView vName;
    @BindView(R.id.chat_list_item_message)
    TextView vMessage;
    @BindView(R.id.chat_list_item_time)
    TextView vTime;

    public ChatListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
