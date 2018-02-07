package com.clearsoft.welivre.ui.screens.plan.p_date;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.ui.screens.auth.complete.third.DatePickerFragment;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 19.07.17.
 */

public class PDateFragment extends ViewMvpFragment<PDatePresenter> implements PDateView , DatePickerFragment.DateListener{

    @BindView(R.id.p_date_fragment_edit_date)
    TextView vDate;
    private long dateMilisec;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_date_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    @Override
    public void returnDate(String date) {
        vDate.setText(date);
    }

    @Override
    public void returnDateMilisecond(long milisecond) {
        dateMilisec = milisecond;
    }


    @Override
    public void openCalendar() {
        DatePickerFragment dateFragment = new DatePickerFragment();
        dateFragment.setListener(this);
        dateFragment.show(getFragmentManager(), "datePicker");
    }


    @OnClick(R.id.p_date_fragment_next_btn_frame)
    public void onDateNextClick(){
        getPresenter().onNextClick((PlanActivity) getActivity(), vDate.getText().toString(),dateMilisec);
    }

    @OnClick(R.id.p_date_fragment_edit_date)
    public void onDateClick(){
        getPresenter().onDateTxtClick();
    }


}
