package com.clearsoft.welivre.ui.screens.plan.p_personal_motivation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 19.07.17.
 */

public class PPersonalMotivationFragment extends ViewMvpFragment<PPersonalMotivationPresenter> implements PPersonalMotivationView {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_personal_motivation_fragment,container,false);
        ButterKnife.bind(this,view);
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


    @OnClick(R.id.p_personal_motivation_fragment_back_btn_frame)
    public void onBackClick(){
        getPresenter().onBackClick((PlanActivity) getActivity());
    }

    @OnClick(R.id.p_personal_motivation_fragment_next_btn_frame)
    public void onNextClick(){
        getPresenter().onNextClick((PlanActivity) getActivity());
    }
}
