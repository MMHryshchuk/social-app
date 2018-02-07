package com.clearsoft.welivre.ui.screens.home;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.di.HomeComponent;
import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;
import com.clearsoft.welivre.ui.screens.article.ArticleActivity;
import com.clearsoft.welivre.ui.screens.awards.AwardsActivity;
import com.clearsoft.welivre.ui.screens.comment.CommentActivity;
import com.clearsoft.welivre.ui.screens.home.adapter.FeedAdapter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;
import com.clearsoft.welivre.ui.screens.popup.MoreFeedPopupActivity;
import com.clearsoft.welivre.ui.screens.popup.PopupActivity;
import com.clearsoft.welivre.ui.screens.posting.PostingActivity;
import com.clearsoft.welivre.ui.screens.profile.ProfileActivity;

import java.sql.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 29.06.17.
 */

public class HomeActivity extends PanelActivity implements HomeView, FeedAdapter.OnClickListener {

    private final static int ALL = 214;
    private final static int FOLLOWING = 215;
    private final static int SOS = 216;

    @BindView(R.id.activity_home_feed_recycler)
    RecyclerView vFeedRecycler;
    @BindView(R.id.home_activity_tab_all)
    TextView vAll;
    @BindView(R.id.home_activity_tab_following)
    TextView vFollowing;
    @BindView(R.id.home_activity_tab_sos)
    TextView vSos;
    @BindView(R.id.home_activity_tab_all_divider)
    View vAllDivider;
    @BindView(R.id.home_activity_tab_following_divider)
    View vFollowingDivider;
    @BindView(R.id.home_activity_tab_sos_divider)
    View vSosDivider;
    @BindView(R.id.home_user_view)
    RelativeLayout vUserView;
    @BindView(R.id.home_user_detail_view)
    RelativeLayout vDetailUserView;
    @BindView(R.id.home_user_view_name_label)
    TextView vUserName;
    @BindView(R.id.home_user_detail_title)
    TextView vDetailUserName;
    @BindView(R.id.home_user_view_avatar_image)
    ImageView vUserImage;
    @BindView(R.id.activity_home_fab)
    FloatingActionButton vFab;
    @BindView(R.id.home_user_detail_profile_avatar)
    ImageView vUserImageDetail;
    @BindView(R.id.create_plan_button_view)
    RelativeLayout vCreatePlanBtn;

    @Inject
    PreferenceRepository preferenceRepository;
    @Inject
    HomePresenter mHomePresenter;
    private FeedAdapter mFeedAdapter;
    private int activeTab;
    private int feedId;
    private String userFeedId;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
        setupDagger();
        initRecycler();
        vUserName.setText(preferenceRepository.getUserName());
        if (!StringUtils.isNullEmpty(preferenceRepository.getUserImage()))
            ImageLoader.load(vUserImage,preferenceRepository.getUserImage(),true);
        String hello = "<u>Good Morning!</u> " + preferenceRepository.getUserName();
        vDetailUserName.setText(Html.fromHtml(hello));
//        vFab = (FloatingActionButton) findViewById(R.id.activity_home_fab);
        vFab.setOnClickListener(v -> {
            mHomePresenter.onCreatePostClick();
        });
        activeTab = ALL;
        if (preferenceRepository.getUserPlanId() == 0){
            showDetailUserView();
            if (!StringUtils.isNullEmpty(preferenceRepository.getUserImage()))
                ImageLoader.load(vUserImageDetail,preferenceRepository.getUserImage(),true);
            vCreatePlanBtn.setOnClickListener(v -> {
                PlanActivity.start(this);
            });
        }else {
            showUserView();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHomePresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHomePresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (activeTab) {
            case ALL:
                mHomePresenter.onAllFeedClick();
                break;
            case FOLLOWING:
                mHomePresenter.onFollowingFeedClick();
                break;
            case SOS:
                mHomePresenter.onSosFeedClick();
                break;
        }

        if (preferenceRepository.getUserPlanId() == 0){
            showDetailUserView();
        }else {
            showUserView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MoreFeedPopupActivity.REQUEST_MORE) {
            if (resultCode == MoreFeedPopupActivity.RESULT_HIDE) {
                mHomePresenter.hidePostsFromUser(userFeedId);
            } else if (resultCode == MoreFeedPopupActivity.RESULT_REPORT) {
                Toast.makeText(this, "on ReportClick", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new HomeComponent.Module())
                .inject(this);

    }

    private void initRecycler() {
        mFeedAdapter = new FeedAdapter(this, this);
//        vFeedRecycler.setNestedScrollingEnabled(false);
        vFeedRecycler.setLayoutManager(new LinearLayoutManager(this));
        vFeedRecycler.setAdapter(mFeedAdapter);
        vFeedRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int pos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (pos >= mFeedAdapter.getItemCount() - 2) {
                    switch (activeTab) {
                        case ALL:
                            mHomePresenter.loadMoreAllFeeds();
                            break;
                        case FOLLOWING:
                            mHomePresenter.loadMoreFollowingFeeds();
                            break;
                        case SOS:
                            mHomePresenter.loadMoreSosFeeds();
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void setTabNonActive() {
        vAll.setTextColor(getResources().getColor(R.color.colorTextSecondary));
        vFollowing.setTextColor(getResources().getColor(R.color.colorTextSecondary));
        vSos.setTextColor(getResources().getColor(R.color.colorTextSecondary));
        vAllDivider.setVisibility(View.GONE);
        vFollowingDivider.setVisibility(View.GONE);
        vSosDivider.setVisibility(View.GONE);
    }

    @Override
    public void setTabAllActive() {
        vAll.setTextColor(getResources().getColor(R.color.colorTextRed));
        vAllDivider.setVisibility(View.VISIBLE);
        activeTab = ALL;

    }

    @Override
    public void setTabFollowingActive() {
        vFollowing.setTextColor(getResources().getColor(R.color.colorTextRed));
        vFollowingDivider.setVisibility(View.VISIBLE);
        activeTab = FOLLOWING;

    }

    @Override
    public void setTabSosActive() {
        vSos.setTextColor(getResources().getColor(R.color.colorTextRed));
        vSosDivider.setVisibility(View.VISIBLE);
        activeTab = SOS;
    }

    @Override
    public void showData(FeedListDvo dvo) {
        mFeedAdapter.setData(dvo);
        mFeedAdapter.sort();
    }

    @Override
    public void openUserProfile() {
        ProfileActivity.start(this,preferenceRepository.getUserId());
    }

    @Override
    public void showDetailUserView() {
        vUserView.setVisibility(View.GONE);
        vDetailUserView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUserView() {
        vUserView.setVisibility(View.VISIBLE);
        vDetailUserView.setVisibility(View.GONE);
    }

    @Override
    public void openArticle() {
        ArticleActivity.start(this);
    }

    @Override
    public void openAwards() {
        AwardsActivity.start(this);
    }

    @Override
    public void openPostingScreen() {
        PostingActivity.start(this);
    }

    @Override
    public void updateFeedItem(FeedDvo feedDvo) {
        mFeedAdapter.updateItem(feedDvo);
    }

    @Override
    public void openComments(int postId, int postLikes) {
        CommentActivity.start(this, postId, postLikes, false);
    }

    @Override
    public void showLoadMoreProgress() {
        mFeedAdapter.showLoadMore();
    }

    @Override
    public void hideLoadMoreProgress() {
        mFeedAdapter.hideLoadMore();
    }


    @OnClick(R.id.home_activity_tab_all)
    public void onAllClick() {
        mHomePresenter.onAllFeedClick();
    }

    @OnClick(R.id.home_activity_tab_following)
    public void onFollowingClick() {
        mHomePresenter.onFollowingFeedClick();
    }

    @OnClick(R.id.home_activity_tab_sos)
    public void onSosClick() {
        mHomePresenter.onSosFeedClick();
    }

    @OnClick(R.id.home_user_view_user_btn)
    public void onUserClick() {
        mHomePresenter.onMyProfileClick();
    }

    @OnClick(R.id.home_user_view_saving_btn)
    public void onSavingBtnClick() {
//        mHomePresenter.onSavingBtnClick();
    }

    @OnClick(R.id.home_user_detail_view)
    public void onDetailViewClick() {
//        mHomePresenter.onUserViewClick();
    }

    @OnClick(R.id.activity_home_article)
    public void onArticleClick() {
        mHomePresenter.onArticleClick();
    }

    @OnClick(R.id.home_user_view_awards)
    public void onAwardsClick() {
        mHomePresenter.onAwardsClick();
    }


    @Override
    public void onLikeClick(int feedId) {
        mHomePresenter.onLikeClick(feedId);
    }

    @Override
    public void onUnLikeClick(int feedId) {
        mHomePresenter.onUnLikeClick(feedId);
    }

    @Override
    public void onFavoriteClick(int feedId) {
        mHomePresenter.onFavoriteClick(feedId);
    }

    @Override
    public void onUnFavoriteClick(int feedId) {
        mHomePresenter.onUnFavoriteClick(feedId);
    }

    @Override
    public void onMoreFeedClick(FeedDvo dvo) {
        userFeedId = dvo.getUserId();
        MoreFeedPopupActivity.startForResult(this, dvo.getUserName());
    }

    @Override
    public void onCommentClick(int feedId, int postLikes) {
        mHomePresenter.onCommentClick(feedId, postLikes);
        this.feedId = feedId;
    }

    @Override
    public void onUserClick(FeedDvo feedDvo) {
        ProfileActivity.start(this, feedDvo.getUserId());
    }


    @Override
    public void loadMore() {
        switch (activeTab) {
            case ALL:
                mHomePresenter.loadMoreAllFeeds();
                break;
            case FOLLOWING:
                mHomePresenter.loadMoreFollowingFeeds();
                break;
            case SOS:
                mHomePresenter.loadMoreSosFeeds();
                break;
        }
    }


    /*@OnClick(R.id.create_plan_button_view)
    pub*/
}
