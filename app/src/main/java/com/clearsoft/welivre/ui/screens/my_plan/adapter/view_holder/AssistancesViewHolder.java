package com.clearsoft.welivre.ui.screens.my_plan.adapter.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.AssistancesDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 09.08.17.
 */

public class AssistancesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.assistences_item_icon)
    ImageView vIcon;
    @BindView(R.id.assistances_item_tittle)
    TextView vTitle;

    public AssistancesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(AssistancesDvo dvo){
        vIcon.setImageResource(dvo.getDrawable());
        vTitle.setText(dvo.getName());
    }
}
