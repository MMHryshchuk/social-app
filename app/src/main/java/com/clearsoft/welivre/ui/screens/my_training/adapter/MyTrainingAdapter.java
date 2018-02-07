package com.clearsoft.welivre.ui.screens.my_training.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;

/**
 * Created by on 18.07.17.
 */

public class MyTrainingAdapter extends RecyclerView.Adapter<MyTrainingViewHolder> {


    private Context context;
    private LayoutInflater mInflater;

    public MyTrainingAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyTrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyTrainingViewHolder(mInflater.inflate(
                R.layout.my_training_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(MyTrainingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }
}
