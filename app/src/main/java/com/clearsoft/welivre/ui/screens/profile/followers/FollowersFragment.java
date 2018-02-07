package com.clearsoft.welivre.ui.screens.profile.followers;

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
import com.clearsoft.welivre.ui.screens.profile.followers.adapter.FollowersAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 01.08.17.
 */

public class FollowersFragment extends ViewMvpFragment<FollowersPresenter> implements FollowersView, FollowersAdapter.OnItemClickListener {

    @BindView(R.id.profile_followers_fragment_recycler)
    RecyclerView vRecycler;

    private FollowersAdapter mFollowersAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_followers_fragment,container,false);
        ButterKnife.bind(this,view);
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
        mFollowersAdapter = new FollowersAdapter(getActivity(),this);
        vRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRecycler.setAdapter(mFollowersAdapter);
    }


    @Override
    public void showData(FollowingListDvo dvo) {
        mFollowersAdapter.setData(dvo.getFollowingDvos());
    }

    @Override
    public void onUserClick(String userId) {
        ProfileActivity.start(getActivity(),userId);
    }
}
