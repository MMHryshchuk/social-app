package com.clearsoft.welivre.ui.screens.profile.following;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.ui.dvo.FollowingListDvo;
import com.clearsoft.welivre.ui.screens.profile.ProfileActivity;
import com.clearsoft.welivre.ui.screens.profile.following.adapter.FollowingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 01.08.17.
 */

public class FollowingFragment extends ViewMvpFragment<FollowingPresenter> implements FollowingView , FollowingAdapter.OnItemClickListener {

    @BindView(R.id.profile_following_fragment_recycler)
    RecyclerView vRecycler;

    private FollowingAdapter mFollowingAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_following_fragment, container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
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
        mFollowingAdapter = new FollowingAdapter(getActivity(),this);
        vRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRecycler.setAdapter(mFollowingAdapter);
    }

    @Override
    public void showData(FollowingListDvo dvo) {
        mFollowingAdapter.setData(dvo.getFollowingDvos());
    }

    @Override
    public void updateFollow(String followId, boolean isFollow) {
        mFollowingAdapter.updateFollow(followId, isFollow);
    }

    @Override
    public void openUserProfile(String userId) {
        ProfileActivity.start(getActivity(),userId);
    }

    @Override
    public void follow(String userId,String followId, boolean isFollow) {
        if (isFollow){
            getPresenter().unFollow(userId, followId);
        }else {
            getPresenter().follow(userId, followId);
        }
    }
}
