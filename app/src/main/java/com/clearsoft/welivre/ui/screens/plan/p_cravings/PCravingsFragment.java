package com.clearsoft.welivre.ui.screens.plan.p_cravings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 19.07.17.
 */

public class PCravingsFragment extends ViewMvpFragment<PCravingsPresenter> implements PCravingsView {


    @BindView(R.id.p_cravings_fragment_switch_1)
    Switch vSwitch1;
    @BindView(R.id.p_cravings_fragment_switch_2)
    Switch vSwitch2;
    @BindView(R.id.p_cravings_fragment_switch_3)
    Switch vSwitch3;
    @BindView(R.id.p_cravings_fragment_switch_4)
    Switch vSwitch4;
    @BindView(R.id.p_cravings_fragment_switch_5)
    Switch vSwitch5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_cravings_fragment,container,false);
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

    @OnClick(R.id.p_cravings_fragment_back_btn_frame)
    public void onBackClick(){
        getPresenter().onBackClick((PlanActivity) getActivity());
    }

    @OnClick(R.id.p_cravings_fragment_next_btn_frame)
    public void onNextClick(){
        if (!StringUtils.isNullEmpty(getCravingsIds()))
        getPresenter().onNextClick((PlanActivity) getActivity(),getCravingsIds());
    }

    private String getCravingsIds(){
        boolean first = true;
        String cravingsIDs = "";

            if (vSwitch1.isChecked()){
                    cravingsIDs += "1";
                first = false;

            }
            if (vSwitch2.isChecked()){
                if (first){
                    cravingsIDs += "2";
                    first = false;
                }else {
                    cravingsIDs += ",2";

                }
            }
            if (vSwitch3.isChecked()){
                if (first){
                    cravingsIDs += "3";
                    first = false;
                }else {
                    cravingsIDs += ",3";

                }
            }
            if (vSwitch4.isChecked()){
                if (first){
                    cravingsIDs += "4";
                    first = false;
                }else {
                    cravingsIDs += ",4";

                }
            }
            if (vSwitch5.isChecked()){
                if (first){
                    cravingsIDs += "5";
                    first = false;

                }else {
                    cravingsIDs += ",5";

                }
            }

            return cravingsIDs;

    }
}
