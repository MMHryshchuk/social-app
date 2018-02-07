package com.clearsoft.welivre.ui.screens.profile.posts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;
import com.clearsoft.welivre.ui.screens.comment.CommentActivity;
import com.clearsoft.welivre.ui.screens.home.adapter.FeedAdapter;
import com.clearsoft.welivre.ui.screens.popup.MoreFeedPopupActivity;
import com.clearsoft.welivre.ui.screens.popup.PopupActivity;
import com.clearsoft.welivre.ui.screens.profile.ProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 15.07.17.
 */

public class PostsFragment extends ViewMvpFragment<PostsPresenter> implements PostsView, FeedAdapter.OnClickListener {


    @BindView(R.id.profile_post_fragment_recycler)
    RecyclerView vRecyclerView;

    private FeedAdapter mFeedAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_post_fragment,container,false);
        ButterKnife.bind(this,view);
        initRecycler();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().detachView();
    }

    private void initRecycler(){
        mFeedAdapter = new FeedAdapter(getActivity(),this);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRecyclerView.setAdapter(mFeedAdapter);
    }

    @Override
    public void showData(FeedListDvo feedListDvo) {
        mFeedAdapter.setData(feedListDvo);
    }

    @Override
    public void updateFeedItem(FeedDvo feedDvo) {
        mFeedAdapter.updateItem(feedDvo);
    }

    @Override
    public void openComments(int postId,int postLikes) {
        CommentActivity.start(getActivity(),postId,postLikes, false);
    }

    @Override
    public void openMoreScreen(FeedDvo dvo) {
        MoreFeedPopupActivity.startForResult(getActivity(),dvo.getUserName());
    }


    @Override
    public void onLikeClick(int feedId) {
        getPresenter().onLikeClick(feedId);
    }

    @Override
    public void onUnLikeClick(int feedId) {
        getPresenter().onUnLikeClick(feedId);
    }

    @Override
    public void onFavoriteClick(int feedId) {
        getPresenter().onFavoriteClick(feedId);
    }

    @Override
    public void onUnFavoriteClick(int feedId) {
        getPresenter().onUnFavoriteClick(feedId);
    }

    @Override
    public void onMoreFeedClick(FeedDvo dvo) {
        getPresenter().onMoreFeedClick(dvo);
    }

    @Override
    public void onCommentClick(int feedId,int postLikes) {
        getPresenter().onCommentClick(feedId,postLikes);
    }

    @Override
    public void onUserClick(FeedDvo feedDvo) {
        ProfileActivity.start(getActivity(),feedDvo.getUserId());
    }


    @Override
    public void loadMore() {

    }
}
