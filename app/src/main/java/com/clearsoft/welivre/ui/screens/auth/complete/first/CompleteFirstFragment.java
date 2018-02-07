package com.clearsoft.welivre.ui.screens.auth.complete.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 28.06.17.
 */

public class CompleteFirstFragment extends ViewMvpFragment<CompleteFirstPresenter> implements CompleteFirstView {


    @BindView(R.id.complete_choice_1)
    RelativeLayout vCheckLay1;
    @BindView(R.id.complete_choice_2)
    RelativeLayout vCheckLay2;
    @BindView(R.id.complete_choice_3)
    RelativeLayout vCheckLay3;
    @BindView(R.id.complete_choice_4)
    RelativeLayout vCheckLay4;
    @BindView(R.id.complete_choice_5)
    RelativeLayout vCheckLay5;
    @BindView(R.id.complete_choice_6)
    RelativeLayout vCheckLay6;
    @BindView(R.id.complete_choice_1_check_box)
    ImageView vCheck1;
    @BindView(R.id.complete_choice_2_check_box)
    ImageView vCheck2;
    @BindView(R.id.complete_choice_3_check_box)
    ImageView vCheck3;
    @BindView(R.id.complete_choice_4_check_box)
    ImageView vCheck4;
    @BindView(R.id.complete_choice_5_check_box)
    ImageView vCheck5;
    @BindView(R.id.complete_choice_6_check_box)
    ImageView vCheck6;
    @BindView(R.id.complete_choice_1_bg)
    ImageView vBg1;
    @BindView(R.id.complete_choice_2_bg)
    ImageView vBg2;
    @BindView(R.id.complete_choice_3_bg)
    ImageView vBg3;
    @BindView(R.id.complete_choice_4_bg)
    ImageView vBg4;
    @BindView(R.id.complete_choice_5_bg)
    ImageView vBg5;
    @BindView(R.id.complete_choice_6_bg)
    ImageView vBg6;


    private final int CHECK_BOX_CHECK = R.drawable.situation_check_cell_ic;
    private final int CHECK_BOX_UNCHECK = R.drawable.situation_uncheck_ic;
    private int situation;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_first_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vCheck1.setImageResource(CHECK_BOX_CHECK);
        vBg1.setVisibility(View.VISIBLE);
        situation = 1;
        setupClickListeners();
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

    private void setupClickListeners(){
        vCheckLay1.setOnClickListener(v -> {
            setViewCheck(vCheck1,vBg1);
            situation = 1;

        });
        vCheckLay2.setOnClickListener(v ->{
            setViewCheck(vCheck2,vBg2);
            situation = 2;
        });
        vCheckLay3.setOnClickListener(v -> {
            setViewCheck(vCheck3,vBg3);
            situation = 3;
        });
        vCheckLay4.setOnClickListener(v -> {
            setViewCheck(vCheck4,vBg4);
            situation = 4;
        });
        vCheckLay5.setOnClickListener(v -> {
            setViewCheck(vCheck5,vBg5);
            situation = 5;
        });
        vCheckLay6.setOnClickListener(v -> {
            setViewCheck(vCheck6,vBg6);
            situation = 6;
        });
    }

    /* ----- View methods ----- */

    @Override
    public void setViewCheck(ImageView vCheck, ImageView vBg) {
        vCheck1.setImageResource(CHECK_BOX_UNCHECK);
        vBg1.setVisibility(View.GONE);
        vCheck2.setImageResource(CHECK_BOX_UNCHECK);
        vBg2.setVisibility(View.GONE);
        vCheck3.setImageResource(CHECK_BOX_UNCHECK);
        vBg3.setVisibility(View.GONE);
        vCheck4.setImageResource(CHECK_BOX_UNCHECK);
        vBg4.setVisibility(View.GONE);
        vCheck5.setImageResource(CHECK_BOX_UNCHECK);
        vBg5.setVisibility(View.GONE);
        vCheck6.setImageResource(CHECK_BOX_UNCHECK);
        vBg6.setVisibility(View.GONE);

        vCheck.setImageResource(CHECK_BOX_CHECK);
        vBg.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNextStep() {
    }

    @OnClick(R.id.complete_first_fragment_next)
    public void onNextClick(){
        getPresenter().onNextClick((CompleteRegisterActivity) getActivity(),situation);
    }


}
