package com.clearsoft.welivre.ui.screens.my_plan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.MyPlanComponent;
import com.clearsoft.welivre.ui.dvo.MotivationDvo;
import com.clearsoft.welivre.ui.dvo.MyPlanDvo;
import com.clearsoft.welivre.ui.dvo.PlanDvo;
import com.clearsoft.welivre.ui.screens.my_plan.adapter.AssistancesAdapter;
import com.clearsoft.welivre.ui.screens.my_plan.adapter.CravingsAdapter;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;
import com.clearsoft.welivre.ui.screens.plan.p_motivation.adapter.PMotivationAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 18.07.17.
 */

public class MyPlanActivity extends PanelActivity implements MyPlanView {

    @BindView(R.id.plan_date_view_date_txt)
    TextView vDateView;
    @BindView(R.id.motivation_view_recycler)
    RecyclerView vMotivationRecycler;
    @BindView(R.id.cravings_item_recycler)
    RecyclerView vCravingsRecycler;
    @BindView(R.id.assistance_item_recycler)
    RecyclerView vAssistancesRecycler;
    @BindView(R.id.triggers_item_recycler)
    RecyclerView vTriggersRecycler;

    @Inject
    MyPlanPresenter mMyPlanPresenter;

    private PMotivationAdapter mMotivationAdapter;
    private CravingsAdapter mCravingsAdapter;
    private AssistancesAdapter mAssistancesAdapter;
    private PMotivationAdapter mTriggersAdapter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MyPlanActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        ButterKnife.bind(this);
        setupDagger();
        initRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyPlanPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMyPlanPresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyPlanPresenter.onResume();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new MyPlanComponent.Module())
                .inject(this);
    }

    private void initRecycler(){
        mMotivationAdapter = new PMotivationAdapter(this,false);
        vMotivationRecycler.setNestedScrollingEnabled(false);
        vMotivationRecycler.setLayoutManager(new GridLayoutManager(this,3));
        vMotivationRecycler.setAdapter(mMotivationAdapter);

        mCravingsAdapter = new CravingsAdapter(this);
        vCravingsRecycler.setNestedScrollingEnabled(false);
        vCravingsRecycler.setLayoutManager(new LinearLayoutManager(this));
        vCravingsRecycler.setAdapter(mCravingsAdapter);

        mAssistancesAdapter = new AssistancesAdapter(this);
        vAssistancesRecycler.setNestedScrollingEnabled(false);
        vAssistancesRecycler.setLayoutManager(new LinearLayoutManager(this));
        vAssistancesRecycler.setAdapter(mAssistancesAdapter);

        mTriggersAdapter = new PMotivationAdapter(this,false);
        vTriggersRecycler.setNestedScrollingEnabled(false);
        vTriggersRecycler.setLayoutManager(new GridLayoutManager(this,3));
        vTriggersRecycler.setAdapter(mTriggersAdapter);
    }

    @OnClick(R.id.toolbar_plan_settings_btn)
    public void onSettingsClick(){
        PlanActivity.start(this);
    }

    @Override
    public void showData(MyPlanDvo dvo) {
        vDateView.setText(dvo.getPlanDvo().getDate());
        mMotivationAdapter.setData(dvo.getMotivationDvoList());
        mCravingsAdapter.setData(dvo.getCravingsDvoList());
        mAssistancesAdapter.setData(dvo.getAssistancesDvoList());
        mTriggersAdapter.setData(dvo.getTriggersDvoList());
    }

}
