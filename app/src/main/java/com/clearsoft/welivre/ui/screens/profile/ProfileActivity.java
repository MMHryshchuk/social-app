package com.clearsoft.welivre.ui.screens.profile;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.Optional;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.core.bus.EventBus;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.di.ProfileComponent;
import com.clearsoft.welivre.ui.dvo.ProfileDvo;
import com.clearsoft.welivre.ui.events.AboutEvent;
import com.clearsoft.welivre.ui.screens.popup.AboutMoreActivity;
import com.clearsoft.welivre.ui.screens.profile.about.AboutFragment;
import com.clearsoft.welivre.ui.screens.profile.about.AboutPresenter;
import com.clearsoft.welivre.ui.screens.profile.edit_about.EditAboutActivity;
import com.clearsoft.welivre.ui.screens.profile.followers.FollowersFragment;
import com.clearsoft.welivre.ui.screens.profile.followers.FollowersPresenter;
import com.clearsoft.welivre.ui.screens.profile.following.FollowingFragment;
import com.clearsoft.welivre.ui.screens.profile.following.FollowingPresenter;
import com.clearsoft.welivre.ui.screens.profile.posts.PostsFragment;
import com.clearsoft.welivre.ui.screens.profile.posts.PostsPresenter;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 30.06.17.
 */

public class ProfileActivity extends PanelActivity implements ProfileView{

    public final static String EXTRA_USER_ID = "EXTRA_USER_ID";

    public final static int FRAGMENT_POSTS = 910;
    public final static int FRAGMENT_FOLLOWERS = 911;
    public final static int FRAGMENT_FOLLOWING = 912;
    public final static int FRAGMENT_ABOUT = 913;



    @BindView(R.id.toolbar_user)
    Toolbar vToolbar;
    @BindView(R.id.user_profile_send_message_btn)
    TextView vSendMessageBtn;
    @BindView(R.id.user_profile_follow)
    TextView vFollowBtn;
    @BindView(R.id.activity_profile_posts_count)
    TextView vPostsCount;
    @BindView(R.id.activity_profile_followers_count)
    TextView vFollowersCount;
    @BindView(R.id.activity_profile_following_count)
    TextView vFollowingCount;
    @BindView(R.id.activity_profile_about_img)
    ImageView vAbout;
    @BindView(R.id.activity_profile_posts_count_txt)
    TextView vPostsCountTxt;
    @BindView(R.id.activity_profile_followers_count_txt)
    TextView vFollowersCountTxt;
    @BindView(R.id.activity_profile_following_count_txt)
    TextView vFollowingCountTxt;
    @BindView(R.id.activity_profile_about_txt)
    TextView vAboutTxt;
    @BindView(R.id.post_indicator)
    ImageView vPostsIndicator;
    @BindView(R.id.followers_indicator)
    ImageView vFollowersIndicator;
    @BindView(R.id.following_indicator)
    ImageView vFollowingIndicator;
    @BindView(R.id.about_indicator)
    ImageView vAboutIndicator;
    @BindView(R.id.user_profile_avatar_image)
    ImageView vUserAvatar;
    @BindView(R.id.activity_profile_avoid_siggarete)
    TextView vAvoidSiggarette;
    @BindView(R.id.activity_profile_saving)
    TextView vSaving;
    @BindView(R.id.activity_profile_no_smokking_days)
    TextView vNoSmokingDays;


    @Inject
    PostsPresenter mPostsPresenter;
    @Inject
    FollowersPresenter mFollowersPresenter;

    @Inject
    FollowingPresenter mFollowingPresenter;

    @Inject
    AboutPresenter mAboutPresenter;

    @Inject
    ProfilePresenter mProfilePresenter;

    private int currentFragment;
    private PreferenceRepository preferenceRepository;
    private boolean isMyProfile;
    private String userId;
    private boolean isFollow;

    public static void start(Activity activity, String userId) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra(EXTRA_USER_ID,userId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupDagger();
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
        setSupportActionBar(vToolbar);
        userId = getIntent().getStringExtra(EXTRA_USER_ID);
        if (StringUtils.isNullEmpty(userId)){
            userId = preferenceRepository.getUserId();
        }
        isMyProfile = userId.equals(preferenceRepository.getUserId());
        isMyProfile(isMyProfile);
        openPosts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProfilePresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProfilePresenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AboutMoreActivity.REQUEST_MORE){
            if (resultCode == AboutMoreActivity.RESULT_EDIT){
                EditAboutActivity.start(this);
            }
        }

    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new ProfileComponent.Module(getIntent().getStringExtra(EXTRA_USER_ID)))
                .inject(this);
    }




    private void isMyProfile(boolean isMy) {
        vSendMessageBtn.setVisibility(isMy ? View.GONE : View.VISIBLE);
        vFollowBtn.setVisibility(isMy ? View.GONE : View.VISIBLE);
    }



    private void setActiveTab(TextView count, TextView countTxt, ImageView indicator, boolean isAbout){
        vPostsCount.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        vFollowersCount.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        vFollowingCount.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        vAbout.setImageResource(R.drawable.ic_user_grey);

        vPostsCountTxt.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        vFollowersCountTxt.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        vFollowingCountTxt.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        vAboutTxt.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));

        vPostsIndicator.setVisibility(View.GONE);
        vFollowersIndicator.setVisibility(View.GONE);
        vFollowingIndicator.setVisibility(View.GONE);
        vAboutIndicator.setVisibility(View.GONE);

        countTxt.setTextColor(ContextCompat.getColor(this,R.color.colorBackgroundBlue));
        indicator.setVisibility(View.VISIBLE);
        if (isAbout){
            vAbout.setImageResource(R.drawable.ic_user_blue);
        }else {
            count.setTextColor(ContextCompat.getColor(this,R.color.colorBackgroundBlue));
        }
    }


    private void openPosts(){
        currentFragment = FRAGMENT_POSTS;
        setActiveTab(vPostsCount,vPostsCountTxt,vPostsIndicator,false);
        replaceFragment(mPostsPresenter,new PostsFragment());
    }

    private void openFollowers(){
        currentFragment = FRAGMENT_FOLLOWERS;
        setActiveTab(vFollowersCount,vFollowersCountTxt,vFollowersIndicator,false);
        replaceFragment(mFollowersPresenter,new FollowersFragment());

    }

    private void openFollowing(){
        currentFragment = FRAGMENT_FOLLOWING;
        setActiveTab(vFollowingCount,vFollowingCountTxt,vFollowingIndicator,false);
        replaceFragment(mFollowingPresenter, new FollowingFragment());
    }

    private void openAbout(){
        currentFragment = FRAGMENT_ABOUT;
        setActiveTab(null,vAboutTxt,vAboutIndicator,true);
        replaceFragment(mAboutPresenter, AboutFragment.newInstance(isMyProfile));

    }


    @Override
    public void setFollow() {
        vFollowBtn.setText("FOLLOW");
    }

    @Override
    public void setUnFollow() {
        vFollowBtn.setText("UNFOLLOWING");
    }

    @Override
    public void showData(ProfileDvo dvo) {
        if (!StringUtils.isNullEmpty(dvo.getUserAvatar()))
            ImageLoader.load(vUserAvatar,dvo.getUserAvatar(),true);
        vPostsCount.setText(dvo.getUserPosts());
        vFollowersCount.setText(dvo.getUserFollowers());
        vFollowingCount.setText(dvo.getUserFollowing());
        TextView vTitle = ButterKnife.findById(vToolbar,R.id.toolbar_user_name);
        vTitle.setText(dvo.getUserName());
        vFollowBtn.setText(dvo.isFollowed() ? "UNFOLLOWING" : "FOLLOW" );
        isFollow = dvo.isFollowed();
    }


    @OnClick(R.id.toolbar_user_more_btn)
    public void onClick() {
        Toast.makeText(this, "action more", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.toolbar_user_back)
    public void onBackClick(){
        finish();
    }

    @OnClick(R.id.activity_profile_posts_btn)
    public void onTabPostsClick(){
        openPosts();
    }

    @OnClick(R.id.activity_profile_followers_btn)
    public void onTabFollowersClick(){
        openFollowers();
    }

    @OnClick(R.id.activity_profile_following_btn)
    public void onTabFollowingClick(){
        openFollowing();
    }

    @OnClick(R.id.activity_profile_about_btn)
    public void onTabAboutClick(){
        openAbout();
    }

    @OnClick(R.id.user_profile_follow)
    public void onFollowClick(){
        if (isFollow){
            isFollow = false;
            mProfilePresenter.onUnFollowClick();
        }else {
            isFollow = true;
            mProfilePresenter.onFollowClick();
        }
    }

    @EventBus
    @Subscribe
    public void onAboutUpdateEvent(AboutEvent event) {
        Optional<AboutFragment> fragment = findFragment(AboutFragment.class);
        if (fragment.isFragment() && mAboutPresenter.getView() != null) {
            mAboutPresenter.getView().update();
        }
    }

}
