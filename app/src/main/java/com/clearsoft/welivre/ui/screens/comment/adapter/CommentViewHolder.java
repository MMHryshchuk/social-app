package com.clearsoft.welivre.ui.screens.comment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.utils.DateUtils;
import com.clearsoft.welivre.ui.dvo.CommentDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 15.07.17.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.comment_item_user_image)
    ImageView vAvatar;
    @BindView(R.id.comment_item_user_name)
    TextView vName;
    @BindView(R.id.comment_item_time)
    TextView vTime;
    @BindView(R.id.comment_item_comment_txt)
    TextView vCommentTxt;

    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void init(CommentDvo dvo){
        vName.setText(dvo.getUserName());
        vCommentTxt.setText(dvo.getCommentTxt());
        vTime.setText(DateUtils.getPrettyTimeFormat(dvo.getTimestamp(), vTime.getContext()));
    }
}
