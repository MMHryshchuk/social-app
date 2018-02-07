package com.clearsoft.welivre.ui.screens.messenger.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;

/**
 * Created by on 06.07.17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private Context context;
    private LayoutInflater mInflater;

    public ContactAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(mInflater.inflate(
                R.layout.contact_item,
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
