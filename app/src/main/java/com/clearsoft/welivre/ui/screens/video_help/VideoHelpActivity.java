package com.clearsoft.welivre.ui.screens.video_help;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.VideoHelpComponent;
import com.clearsoft.welivre.ui.screens.video_help.adapter.VideoHelpAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 25.07.17.
 */

public class VideoHelpActivity extends PanelActivity implements VideoHelpView {

    @BindView(R.id.activity_video_help_recycler)
    RecyclerView vRecyclerView;

    @Inject
    VideoHelpPresenter mVideoHelpPresenter;

    private VideoHelpAdapter mVideoHelpAdapter;


    public static void start(Activity activity){
        Intent intent = new Intent(activity, VideoHelpActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_help);
        ButterKnife.bind(this);
        setupDagger();
        initRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mVideoHelpPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoHelpPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new VideoHelpComponent.Module())
                .inject(this);
    }

    private void initRecycler(){
        mVideoHelpAdapter = new VideoHelpAdapter(this);
        vRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        vRecyclerView.setAdapter(mVideoHelpAdapter);
    }

    @Override
    public void close() {
        finish();
    }

    @OnClick(R.id.toolbar_video_help_back_btn)
    public void onCloseClick(){
        mVideoHelpPresenter.onClose();
    }


}
