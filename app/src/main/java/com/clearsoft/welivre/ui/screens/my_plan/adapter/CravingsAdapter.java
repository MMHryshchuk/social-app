package com.clearsoft.welivre.ui.screens.my_plan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.CravingsDvo;
import com.clearsoft.welivre.ui.screens.my_plan.adapter.view_holder.CravingsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 09.08.17.
 */

public class CravingsAdapter extends RecyclerView.Adapter<CravingsViewHolder>{

    private Context context;
    private LayoutInflater mInflater;
    private List<CravingsDvo> data = new ArrayList<>();

    public CravingsAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public CravingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CravingsViewHolder(mInflater.inflate(
                R.layout.craving_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(CravingsViewHolder holder, int position) {
        CravingsDvo dvo = getData().get(position);
        holder.init(dvo);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CravingsDvo> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<CravingsDvo> getData(){
        return data;
    }
}
