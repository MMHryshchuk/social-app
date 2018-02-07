package com.clearsoft.welivre.ui.screens.plan.p_motivation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.dvo.MotivationDvo;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;
import com.clearsoft.welivre.ui.screens.plan.p_motivation.adapter.PMotivationAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 19.07.17.
 */

public class PMotivationFragment extends ViewMvpFragment<PMotivationPresenter> implements PMotivationView {

    @BindView(R.id.p_motivation_fragment_recycler)
    RecyclerView vRecycler;

    private PMotivationAdapter mMotivationAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.p_motivation_fragment,container,false);
        ButterKnife.bind(this,view);
        initRecycler();
        return view;
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
        mMotivationAdapter = new PMotivationAdapter(getActivity(),true);
        vRecycler.setNestedScrollingEnabled(false);
        vRecycler.setLayoutManager(new GridLayoutManager(getActivity(),3));
        vRecycler.setAdapter(mMotivationAdapter);
        vRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public void showData(List<MotivationDvo> data) {
        mMotivationAdapter.setData(data);
    }

    @OnClick(R.id.p_motivation_fragment_back_btn_frame)
    public void onBackClick(){
        getPresenter().onBackClick((PlanActivity) getActivity());
    }

    @OnClick(R.id.p_motivation_fragment_next_btn_frame)
    public void onNextClick(){
        if (!StringUtils.isNullEmpty(mMotivationAdapter.getMotivationIds()))
        getPresenter().onNextClick((PlanActivity) getActivity(), mMotivationAdapter.getMotivationIds());
    }


}
