package com.clearsoft.welivre.ui.screens.comment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.dvo.CommentDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 15.07.17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {


    private Context context;
    private LayoutInflater mInflater;
    private List<CommentDvo> data = new ArrayList<>();
    private OnCommentClickListener listener;

    public CommentAdapter(Context context,OnCommentClickListener listener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(mInflater.inflate(
                R.layout.comment_item,
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        CommentDvo dvo = getData().get(position);
        holder.init(dvo);
        holder.itemView.setOnLongClickListener(v -> {
            listener.onCommentLongClick(dvo);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setData(List<CommentDvo> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<CommentDvo> getData(){
        return data;
    }

    public interface OnCommentClickListener{
        void onCommentLongClick(CommentDvo dvo);
    }
}
