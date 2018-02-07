package com.clearsoft.welivre.ui.screens.messenger.contacts.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by on 06.07.17.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {


    public ContactViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
