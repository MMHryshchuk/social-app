package com.clearsoft.welivre.ui.screens.favorite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.screens.favorite.adapter.holders.FavArticleViewHolder;

/**
 * Created by on 05.07.17.
 */

public class FavArticleAdapter extends RecyclerView.Adapter<FavArticleViewHolder>{


    private Context context;
    private LayoutInflater mInflater;

    public FavArticleAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public FavArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavArticleViewHolder(mInflater.inflate(
                R.layout.article_item,
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(FavArticleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
