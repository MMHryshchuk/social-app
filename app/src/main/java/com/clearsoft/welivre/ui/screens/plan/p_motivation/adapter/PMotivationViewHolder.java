package com.clearsoft.welivre.ui.screens.plan.p_motivation.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.MotivationDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 19.07.17.
 */

public class PMotivationViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.p_motivation_item_image)
    ImageView vImage;
    @BindView(R.id.p_motivation_item_name)
    TextView vText;
    @BindView(R.id.p_motivation_item_btn_lay)
    public RelativeLayout vBtnLay;

    public PMotivationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void init(MotivationDvo dvo){
        vImage.setImageResource(dvo.getImage());
        vText.setText(dvo.getName());
        vBtnLay.setBackgroundResource(dvo.isActive() ? R.drawable.my_trigger_ic_bg_selected : R.drawable.my_trigger_ic_bg);

    }

}
