package com.clearsoft.welivre.ui.screens.plan.p_motivation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.MotivationDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 19.07.17.
 */

public class PMotivationAdapter extends RecyclerView.Adapter<PMotivationViewHolder> {


    private Context context;
    private LayoutInflater mInflater;
    private List<MotivationDvo> data = new ArrayList<>();
    private boolean withClick;

    public PMotivationAdapter(Context context,boolean withClick) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.withClick = withClick;
    }

    @Override
    public PMotivationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PMotivationViewHolder(mInflater.inflate(
                R.layout.p_motivation_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(PMotivationViewHolder holder, int position) {
        MotivationDvo dvo = getData().get(position);
        holder.init(dvo);
        if (withClick) {
            holder.vBtnLay.setOnClickListener(v -> {
                dvo.setIsActive(!dvo.isActive());
                notifyItemChanged(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<MotivationDvo> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<MotivationDvo> getData() {
        return data;
    }

    public String getMotivationIds() {
        boolean first = true;
        String ids = "";
        for (MotivationDvo dvo : data) {
            if (dvo.isActive()) {
                if (first) {
                    ids += dvo.getId() + "";
                    first = false;
                } else {
                    ids += "," + dvo.getId();
                }

            }
        }
        return ids;
    }
}
