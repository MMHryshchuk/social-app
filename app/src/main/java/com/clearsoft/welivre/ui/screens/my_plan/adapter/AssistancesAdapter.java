package com.clearsoft.welivre.ui.screens.my_plan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.AssistancesDvo;
import com.clearsoft.welivre.ui.screens.my_plan.adapter.view_holder.AssistancesViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 09.08.17.
 */

public class AssistancesAdapter extends RecyclerView.Adapter<AssistancesViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<AssistancesDvo> data = new ArrayList<>();

    public AssistancesAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public AssistancesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AssistancesViewHolder(mInflater.inflate(
                R.layout.assistances_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(AssistancesViewHolder holder, int position) {
        AssistancesDvo dvo = getData().get(position);
        holder.init(dvo);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<AssistancesDvo> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<AssistancesDvo> getData(){
        return data;
    }
}
