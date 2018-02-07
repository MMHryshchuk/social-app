package com.clearsoft.welivre.ui.screens.my_training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.MyTrainingComponent;
import com.clearsoft.welivre.ui.screens.my_training.adapter.MyTrainingAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 18.07.17.
 */

public class MyTrainingActivity extends PanelActivity implements MyTrainingView {

    @BindView(R.id.activity_my_training_recycler)
    RecyclerView vRecycler;

    @Inject
    MyTrainingPresenter mMyTrainingPresenter;

    private MyTrainingAdapter mMyTrainingAdapter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MyTrainingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_training);
        ButterKnife.bind(this);
        setupDagger();
        initRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyTrainingPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMyTrainingPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new MyTrainingComponent.Module())
                .inject(this);
    }

    private void initRecycler(){
        mMyTrainingAdapter = new MyTrainingAdapter(this);
        vRecycler.setLayoutManager(new GridLayoutManager(this,2));
        vRecycler.setAdapter(mMyTrainingAdapter);
    }
}
