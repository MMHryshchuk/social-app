package com.clearsoft.welivre.ui.screens.auth.complete.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.dvo.CompleteRegDvo;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;
import com.clearsoft.welivre.ui.screens.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.Body;

/**
 * Created by on 28.06.17.
 */

public class CompleteThirdFragment extends ViewMvpFragment<CompleteThirdPresenter> implements CompleteThirdView, DatePickerFragment.DateListener {

    @BindView(R.id.complete_third_age_edit_text)
    EditText vAgeSmoke;
    @BindView(R.id.complete_third_date_text)
    TextView vDate;
    @BindView(R.id.complete_third_sex_text)
    TextView vGender;
    @BindView(R.id.complete_third_gender_btn)
    RelativeLayout vGenderBtn;
    @BindView(R.id.complete_register_third_progress_frame)
    FrameLayout vProgress;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_third_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vGenderBtn.setOnClickListener(v -> {
            final PopupMenu menu = new PopupMenu(getActivity(), v);
                menu.getMenu().add(0, 0, 0, "Male" );
                menu.getMenu().add(0, 1, 1, "Female" );
            menu.setOnMenuItemClickListener(item -> {
                getPresenter().onGenderClick(item.getTitle()+"");
                return true;
            });
            menu.show();
        });
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

    private boolean validate(){
        boolean isValid = true;
        String ageSmoke = vAgeSmoke.getText().toString();
        String year = vDate.getText().toString();
        if (StringUtils.isNullEmpty(ageSmoke)){
            isValid = false;
        }
        if (StringUtils.isNullEmpty(year)){
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void openHome() {
        HomeActivity.start(getActivity());
        getActivity().finish();
    }

    @Override
    public void openCalendar() {
        DatePickerFragment dateFragment = new DatePickerFragment();
        dateFragment.setListener(this);
        dateFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void showGender(String gender) {
        vGender.setText(gender);
    }

    @Override
    public void showProgress() {
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void returnDate(String date) {
        vDate.setText(date);
    }

    @Override
    public void returnDateMilisecond(long milisecond) {
        
    }

    @OnClick(R.id.complete_third_fragment_date_btn)
    public void onDateClick() {
        getPresenter().onCalendarClick();
    }

    @OnClick(R.id.complete_third_fragment_next)
    public void onNextClick() {
        if (validate()){
            String ageSmoke = vAgeSmoke.getText().toString();
            String year = vDate.getText().toString();
            String gender = vGender.getText().toString();
            getPresenter().onNextClick((CompleteRegisterActivity) getActivity(),ageSmoke,year,gender);
        }
    }

}
