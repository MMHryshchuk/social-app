package com.clearsoft.welivre.ui.screens.messenger.contacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.ui.screens.messenger.contacts.adapter.ContactAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 03.07.17.
 */

public class ContactsFragment extends ViewMvpFragment<ContactsPresenter> implements ContactsView {

    @BindView(R.id.contacts_fragment_followers_btn)
    TextView vFollowers;
    @BindView(R.id.contacts_fragment_following_btn)
    TextView vFollowing;
    @BindView(R.id.contacts_fragment_recycler)
    RecyclerView vRecyclerView;
    @BindView(R.id.contacts_fragment_follow_txt)
    TextView vFollowTxt;

    private ContactAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        setActiveFollowersBtn();
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
        mAdapter = new ContactAdapter(getActivity());
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void setActiveFollowersBtn() {
        vFollowTxt.setText("Followers");
        vFollowers.setActivated(true);
        vFollowers.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        vFollowing.setActivated(false);
        vFollowing.setTextColor(getResources().getColor(R.color.colorTextWhiteShad));

    }

    @Override
    public void setActiveFollowingBtn() {
        vFollowTxt.setText("Following");
        vFollowing.setActivated(true);
        vFollowing.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        vFollowers.setActivated(false);
        vFollowers.setTextColor(getResources().getColor(R.color.colorTextWhiteShad));
    }

    @OnClick(R.id.contacts_fragment_followers_btn)
    public void onFollowersClick(){
        setActiveFollowersBtn();
    }

    @OnClick(R.id.contacts_fragment_following_btn)
    public void onFollowingClick(){
        setActiveFollowingBtn();
    }
}
