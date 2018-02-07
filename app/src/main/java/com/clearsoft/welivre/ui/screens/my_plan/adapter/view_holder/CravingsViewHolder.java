package com.clearsoft.welivre.ui.screens.my_plan.adapter.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.ui.dvo.CravingsDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 09.08.17.
 */

public class CravingsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.craving_item_icon)
    ImageView vImage;
    @BindView(R.id.craving_item_title)
    TextView vTtitle;
    @BindView(R.id.craving_item_txt)
    TextView vTxt;


    public CravingsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void init(CravingsDvo dvo){
        vImage.setImageResource(dvo.getDrawable());
        vTtitle.setText(dvo.getTitle());
        vTxt.setText(dvo.getTxt());
    }
}
