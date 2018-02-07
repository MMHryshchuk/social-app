package com.clearsoft.welivre.ui.screens.profile.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.dvo.AboutDvo;
import com.clearsoft.welivre.ui.dvo.MyPlanDvo;
import com.clearsoft.welivre.ui.screens.comment.CommentActivity;
import com.clearsoft.welivre.ui.screens.my_plan.adapter.AssistancesAdapter;
import com.clearsoft.welivre.ui.screens.my_plan.adapter.CravingsAdapter;
import com.clearsoft.welivre.ui.screens.plan.p_motivation.adapter.PMotivationAdapter;
import com.clearsoft.welivre.ui.screens.popup.AboutMoreActivity;
import com.clearsoft.welivre.ui.screens.profile.edit_about.EditAboutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 01.08.17.
 */

public class AboutFragment extends ViewMvpFragment<AboutPresenter> implements AboutView {

    private static final String ARGS_ME = "ARGS_ME";

    @BindView(R.id.profile_about_fragment_txt)
    TextView vAboutTxt;
    @BindView(R.id.profile_about_fragment_like_count)
    TextView vLikeCount;
    @BindView(R.id.profile_about_fragment_comment_count)
    TextView vCommentCount;
    @BindView(R.id.profile_about_fragment_like_img)
    ImageView vLikeImg;
    @BindView(R.id.profile_about_fragment_like_lay)
    LinearLayout vLikeBtn;
    @BindView(R.id.profile_about_fragment_more_btn)
    ImageView vMoreBtn;

    @BindView(R.id.profile_about_fragment_progress)
    ProgressBar vProgres;
    @BindView(R.id.profile_about_fragment_assis_recycler)
    RecyclerView vAssisRecycler;
    @BindView(R.id.profile_about_fragment_cravings_recycler)
    RecyclerView vCravingsRecycler;
    @BindView(R.id.profile_about_fragment_triggers_recycler)
    RecyclerView vTriggersRecycler;
    @BindView(R.id.profile_about_fragment_motivations_recycler)
    RecyclerView vMotivationsRecycler;
    @BindView(R.id.profile_about_fragment_plan_info)
    LinearLayout vPlanInfo;

    private PMotivationAdapter mMotivationAdapter;
    private CravingsAdapter mCravingsAdapter;
    private AssistancesAdapter mAssistancesAdapter;
    private PMotivationAdapter mTriggersAdapter;
    private boolean isMProfile;

    public static AboutFragment newInstance(boolean isMy) {

        Bundle args = new Bundle();
        args.putBoolean(ARGS_ME, isMy);
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_about_fragment, container, false);
        ButterKnife.bind(this, view);
            isMProfile = getArguments().getBoolean(ARGS_ME);
        vPlanInfo.setVisibility(isMProfile ? View.VISIBLE : View.GONE);
        if (isMProfile)
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

    private void initRecycler() {
        mMotivationAdapter = new PMotivationAdapter(getActivity(),false);
        vMotivationsRecycler.setNestedScrollingEnabled(false);
        vMotivationsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        vMotivationsRecycler.setAdapter(mMotivationAdapter);

        mCravingsAdapter = new CravingsAdapter(getActivity());
        vCravingsRecycler.setNestedScrollingEnabled(false);
        vCravingsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        vCravingsRecycler.setAdapter(mCravingsAdapter);

        mAssistancesAdapter = new AssistancesAdapter(getActivity());
        vAssisRecycler.setNestedScrollingEnabled(false);
        vAssisRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        vAssisRecycler.setAdapter(mAssistancesAdapter);

        mTriggersAdapter = new PMotivationAdapter(getActivity(),false);
        vTriggersRecycler.setNestedScrollingEnabled(false);
        vTriggersRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        vTriggersRecycler.setAdapter(mTriggersAdapter);
    }

    @Override
    public void showData(AboutDvo dvo) {
        vMoreBtn.setVisibility(dvo.isMyProfile() ? View.VISIBLE : View.GONE);
        if (StringUtils.isNullEmpty(dvo.getUserAboutTxt())) {
            vAboutTxt.setText(getString(R.string.fragment_about_hint));
            vAboutTxt.setOnClickListener(v -> {
                EditAboutActivity.start(getActivity());
            });
        }
        vAboutTxt.setText(dvo.getUserAboutTxt());
        vLikeCount.setText(dvo.getAboutLikeCount());
        vCommentCount.setText(dvo.getAboutCommentCount());
        if (dvo.isLiked()) {
            vLikeImg.setImageResource(R.drawable.ic_like_red);
        } else {
            vLikeImg.setImageResource(R.drawable.ic_like_grey);
        }
        if (isMProfile){
            getPresenter().loadMyPlan();
        }
    }

    @Override
    public void showPlanData(MyPlanDvo dvo) {
        mMotivationAdapter.setData(dvo.getMotivationDvoList());
        mCravingsAdapter.setData(dvo.getCravingsDvoList());
        mAssistancesAdapter.setData(dvo.getAssistancesDvoList());
        mTriggersAdapter.setData(dvo.getTriggersDvoList());
    }

    @Override
    public void updateAbout(AboutDvo dvo) {
        if (dvo.isLiked()) {
            vLikeImg.setImageResource(R.drawable.ic_like_red);
        } else {
            vLikeImg.setImageResource(R.drawable.ic_like_grey);
        }

        vLikeCount.setText(dvo.getAboutLikeCount());
        vCommentCount.setText(dvo.getAboutCommentCount());
    }

    @Override
    public void openComment(int userId, int likesCount) {
        CommentActivity.start(getActivity(), userId, likesCount, true);
    }

    @Override
    public void update() {
        getPresenter().update();
    }

    @Override
    public void showProgress() {
        vProgres.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgres.setVisibility(View.GONE);
    }


    @OnClick(R.id.profile_about_fragment_like_lay)
    public void onLikeClick() {
        getPresenter().onLikeClick();
    }

    @OnClick(R.id.profile_about_fragment_comment_lay)
    public void onCommentClick() {
        getPresenter().onCommentClick();
    }

    @OnClick(R.id.profile_about_fragment_more_btn)
    public void onMoreClick() {
        AboutMoreActivity.startForResult(getActivity());
    }


}
