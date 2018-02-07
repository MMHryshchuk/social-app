package com.clearsoft.welivre.ui.screens.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.R;
import com.clearsoft.welivre.domain.entities.Feed;
import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by on 29.06.17.
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int LOAD_MORE = 1;
    public static final int BODY = 2;

    private Context context;
    private LayoutInflater mInflater;
    private OnClickListener listener;
    private List<FeedDvo> feeds = new ArrayList<>();
    private boolean showLoadMore;


    public FeedAdapter(Context context, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.mInflater = LayoutInflater.from(context);
        showLoadMore = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BODY) {
            return new FeedViewHolder(mInflater.inflate(
                    R.layout.feed_item,
                    parent,
                    false), listener
            );
        } else {
            View view = mInflater.inflate(R.layout.feed_load_more_item, parent, false);
            return new LoadMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int type = getItemViewType(position);

     /*   if (position == getItemCount() -2){
            showLoadMore();
        }*/
        if (type == BODY) {
            FeedViewHolder viewHolder = (FeedViewHolder) holder;
            FeedDvo feedDvo = feeds.get(position);
            viewHolder.init(feedDvo);
        }

    }

    @Override
    public int getItemCount() {
        int size = feeds.size();
        if (showLoadMore) {
            size++;
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadMore && position == getItemCount() - 1) {
            return LOAD_MORE;
        } else {
            return BODY;
        }
    }

    public void setData(FeedListDvo listDvo) {
        feeds.clear();
        feeds.addAll(listDvo.getFeeds());
        notifyDataSetChanged();
    }

    public List<FeedDvo> getData() {
        return feeds;
    }

    public void updateItem(FeedDvo dvo) {
        List<FeedDvo> feedDvos = getData();
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

    public void showLoadMore() {
        showLoadMore = true;
        notifyDataSetChanged();
    }

    public void hideLoadMore() {
        showLoadMore = false;
        notifyDataSetChanged();
    }

    public void sort() {
        Collections.sort(feeds,
                (t1, feeds) -> Collator.getInstance().compare(feeds.getPostTimestamp(), t1.getPostTimestamp()));

    }

    public interface OnClickListener extends FeedViewHolder.FeedItemClickListener {
        void loadMore();
    }
}
