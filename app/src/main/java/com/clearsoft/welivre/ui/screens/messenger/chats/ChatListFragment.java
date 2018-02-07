package com.clearsoft.welivre.ui.screens.messenger.chats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.ui.screens.messenger.chats.adapter.ChatListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 03.07.17.
 */

public class ChatListFragment extends ViewMvpFragment<ChatListPresenter> implements ChatListView {

    @BindView(R.id.chat_list_fragment_recycler)
    RecyclerView vRecycler;

    private ChatListAdapter mChatAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_list_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
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

    private void initRecyclerView(){
        vRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mChatAdapter = new ChatListAdapter(getActivity());
        vRecycler.setAdapter(mChatAdapter);
    }
}
