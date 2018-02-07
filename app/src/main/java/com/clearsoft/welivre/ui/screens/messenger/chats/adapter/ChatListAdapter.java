package com.clearsoft.welivre.ui.screens.messenger.chats.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;

/**
 * Created by on 03.07.17.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListViewHolder> {

    private Context context;
    private LayoutInflater mInflater;

    public ChatListAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ChatListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatListViewHolder(mInflater.inflate(
                R.layout.chat_list_item,
                parent
                ,false)
        );
    }

    @Override
    public void onBindViewHolder(ChatListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
