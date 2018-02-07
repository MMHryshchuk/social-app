package com.clearsoft.welivre.ui.screens.favorite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.domain.entities.Feed;
import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;
import com.clearsoft.welivre.ui.screens.favorite.adapter.holders.FavPostViewHolder;
import com.clearsoft.welivre.ui.screens.home.adapter.FeedViewHolder;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by on 05.07.17.
 */

public class FavPostAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private Context context;
    private LayoutInflater mInfaler;
    private List<FeedDvo> data =  new ArrayList<>();
    private FeedViewHolder.FeedItemClickListener listener;


    public FavPostAdapter(Context context) {
        this.context = context;
        this.mInfaler = LayoutInflater.from(context);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeedViewHolder(mInfaler.inflate(
                R.layout.feed_item,
                parent,
                false),listener
        );
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        FeedDvo feedDvo = data.get(position);
        holder.init(feedDvo);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<FeedDvo> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    public void updateItem(FeedDvo dvo) {
        List<FeedDvo> feedDvos = data;
        int position = 0;
        for (FeedDvo feedDvo : feedDvos) {
            if (feedDvo.getPostId().equals(dvo.getPostId())) {
                feedDvo.setPostLiked(dvo.getPostLiked());
                feedDvo.setPostFavorited(dvo.getPostFavorited());
                feedDvo.setPostLikes(dvo.getPostLikes());
                feedDvo.setPostFavorites(dvo.getPostFavorites());
                break;
            }
            position++;
        }
        notifyItemChanged(position);
    }

    public void sort() {
        Collections.sort(data,
                (t1, feeds) -> Collator.getInstance().compare(feeds.getPostTimestamp(), t1.getPostTimestamp()));

    }

    public interface OnClickListener extends FeedViewHolder.FeedItemClickListener {

    }
}
