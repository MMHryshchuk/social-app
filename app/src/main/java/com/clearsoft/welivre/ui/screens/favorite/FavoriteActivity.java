package com.clearsoft.welivre.ui.screens.favorite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.FavoriteComponent;
import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;
import com.clearsoft.welivre.ui.screens.comment.CommentActivity;
import com.clearsoft.welivre.ui.screens.favorite.adapter.FavArticleAdapter;
import com.clearsoft.welivre.ui.screens.favorite.adapter.FavPostAdapter;
import com.clearsoft.welivre.ui.screens.favorite.adapter.FavoritePagerAdapter;
import com.clearsoft.welivre.ui.screens.home.adapter.FeedAdapter;
import com.clearsoft.welivre.ui.screens.popup.MoreFeedPopupActivity;
import com.clearsoft.welivre.ui.screens.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 05.07.17.
 */

public class FavoriteActivity extends PanelActivity implements FavoriteView, FeedAdapter.OnClickListener{


    @Inject
    FavoritePresenter mFavoritePresenter;

    protected RecyclerView vPostRecycler;
    protected RecyclerView vArticleRecycler;
    protected ProgressBar vPostProgress;
    protected ProgressBar vArticleProgress;

    @BindView(R.id.activity_favorite_tab)
    TabLayout vTab;
    @BindView(R.id.activity_favorite_view_pager)
    ViewPager vViewPager;
    @BindView(R.id.view_pager_post)
    FrameLayout vPostFrame;
    @BindView(R.id.view_pager_article)
    FrameLayout vArticleFrame;

    private FavoritePagerAdapter mPagerAdapter;
    private FeedAdapter mPostAdapter;
    private FavArticleAdapter mArticleAdapter;
    private int feedId;




    public static void start(Activity activity){
        Intent intent = new Intent(activity, FavoriteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        setupDagger();
        findView();
        initPostRecycler();
        initArticleRecycler();
        initViewPager();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFavoritePresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFavoritePresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFavoritePresenter.update();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new FavoriteComponent.Module())
                .inject(this);
    }
    public void findView(){
        vPostRecycler = ButterKnife.findById(vPostFrame,R.id.favorite_view_recycler);
        vArticleRecycler = ButterKnife.findById(vArticleFrame,R.id.favorite_view_recycler);
        vPostProgress = ButterKnife.findById(vPostFrame,R.id.favorite_view_progress);
        vArticleProgress = ButterKnife.findById(vArticleFrame,R.id.favorite_view_progress);;
    }

    public void initPostRecycler(){
        mPostAdapter = new FeedAdapter(this,this);
        vPostRecycler.setLayoutManager(new LinearLayoutManager(this));
        vPostRecycler.setAdapter(mPostAdapter);

    }

    public void initArticleRecycler(){
        mArticleAdapter = new FavArticleAdapter(this);
        vArticleRecycler.setLayoutManager(new LinearLayoutManager(this));
        vArticleRecycler.setAdapter(mArticleAdapter);

    }

    private void initViewPager() {
        vTab.setupWithViewPager(vViewPager);
        mPagerAdapter = new FavoritePagerAdapter();
        List<FrameLayout> frames = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        frames.add(vPostFrame);
        frames.add(vArticleFrame);
        titles.add(getString(R.string.favorite_tab_post));
        titles.add(getString(R.string.favorite_tab_article));
        mPagerAdapter.addFrame(frames,titles);
        vViewPager.setAdapter(mPagerAdapter);
        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    showPostRecycler();
                }else {
                    showArticleRecycler();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void showPostRecycler() {
        vViewPager.setCurrentItem(0);
    }

    @Override
    public void showArticleRecycler() {
        vViewPager.setCurrentItem(1);
    }

    @Override
    public void showPostProgress() {
        vPostProgress.setVisibility(View.VISIBLE);
        vArticleProgress.setVisibility(View.GONE);
    }

    @Override
    public void hidePostProgress() {
        vPostProgress.setVisibility(View.GONE);
        vArticleProgress.setVisibility(View.GONE);
    }

    @Override
    public void showArticleProgress() {
        vPostProgress.setVisibility(View.GONE);
        vArticleProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideArticleProgress() {
        vPostProgress.setVisibility(View.GONE);
        vArticleProgress.setVisibility(View.GONE);
    }

    @Override
    public void showData(FeedListDvo data) {
        mPostAdapter.setData(data);
    }

    @Override
    public void openComments(int postId, int postLikes) {
        CommentActivity.start(this, postId, postLikes,false);

    }

    @Override
    public void updateFeedItem(FeedDvo feedDvo) {
        mPostAdapter.updateItem(feedDvo);
    }

    @Override
    public void onLikeClick(int feedId) {
        mFavoritePresenter.onLikeClick(feedId);
    }

    @Override
    public void onUnLikeClick(int feedId) {
        mFavoritePresenter.onUnLikeClick(feedId);
    }

    @Override
    public void onFavoriteClick(int feedId) {
        mFavoritePresenter.onFavoriteClick(feedId);
    }

    @Override
    public void onUnFavoriteClick(int feedId) {
        mFavoritePresenter.onUnFavoriteClick(feedId);
    }

    @Override
    public void onMoreFeedClick(FeedDvo dvo) {
        MoreFeedPopupActivity.startForResult(this,dvo.getUserName());
    }

    @Override
    public void onCommentClick(int feedId, int postLikes) {
        mFavoritePresenter.onCommentClick(feedId, postLikes);
        this.feedId = feedId;
    }
    @Override
    public void onUserClick(FeedDvo feedDvo) {
        ProfileActivity.start(this, feedDvo.getUserId());
    }

    @Override
    public void loadMore() {

    }
}
