package com.clearsoft.welivre.ui.screens.settings.questionaries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.dvo.QuestionariesDvo;
import com.clearsoft.welivre.ui.screens.auth.complete.third.DatePickerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 25.07.17.
 */

public class QuestionariesFragment extends ViewMvpFragment<QuestionariesPresenter> implements QuestionariesView, DatePickerFragment.DateListener {


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

    @BindView(R.id.complete_second_ask_edit_first)
    EditText vSigarette;
    @BindView(R.id.complete_second_ask_edit_second)
    EditText vMoney;
    @BindView(R.id.complete_second_ask_edit_third)
    EditText vTime;

    private boolean isEnable = false;


    private final int CHECK_BOX_CHECK = R.drawable.situation_check_cell_ic;
    private final int CHECK_BOX_UNCHECK = R.drawable.situation_uncheck_ic;
    private int situation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questionnaries_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vCheck1.setImageResource(CHECK_BOX_CHECK);
        vBg1.setVisibility(View.VISIBLE);
        situation = 1;
        setupClickListeners();
        vGenderBtn.setOnClickListener(v -> {
            final PopupMenu menu = new PopupMenu(getActivity(), v);
            menu.getMenu().add(0, 0, 0, "Male");
            menu.getMenu().add(0, 1, 1, "Female");
            menu.setOnMenuItemClickListener(item -> {
                getPresenter().onGenderClick(item.getTitle() + "");
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

    private void setupClickListeners() {
        vCheckLay1.setOnClickListener(v -> {
            setViewCheck(vCheck1, vBg1);
            situation = 1;

        });
        vCheckLay2.setOnClickListener(v -> {
            setViewCheck(vCheck2, vBg2);
            situation = 2;
        });
        vCheckLay3.setOnClickListener(v -> {
            setViewCheck(vCheck3, vBg3);
            situation = 3;
        });
        vCheckLay4.setOnClickListener(v -> {
            setViewCheck(vCheck4, vBg4);
            situation = 4;
        });
        vCheckLay5.setOnClickListener(v -> {
            setViewCheck(vCheck5, vBg5);
            situation = 5;
        });
        vCheckLay6.setOnClickListener(v -> {
            setViewCheck(vCheck6, vBg6);
            situation = 6;
        });
    }

    @Override
    public void openCalendar() {
        DatePickerFragment dateFragment = new DatePickerFragment();
        dateFragment.setListener(this);
        dateFragment.show(getFragmentManager(), "datePicker");
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
    public void showData(QuestionariesDvo dvo) {
        if (StringUtils.isNullEmpty(dvo.getGender()) || dvo.getGender().equals("0")){
            vGender.setText("Male");
        }else {
            vGender.setText("Female");
        }
        vSigarette.setText(dvo.getCigaDailyNum());
        vMoney.setText(dvo.getCigaPackCost());
        vTime.setText(dvo.getCigaWakeup());
        vAgeSmoke.setText(dvo.getCigaStart());
        vDate.setText(dvo.getBirthday());
        switch (dvo.getSituation()){
            case 1 :
                setViewCheck(vCheck1, vBg1);
                break;
            case 2 :
                setViewCheck(vCheck2, vBg2);
                break;
            case 3 :
                setViewCheck(vCheck3, vBg3);
                break;
            case 4 :
                setViewCheck(vCheck4, vBg4);
                break;
            case 5 :
                setViewCheck(vCheck5, vBg5);
                break;
            case 6 :
                setViewCheck(vCheck6, vBg6);
                break;

        }

    }

    @Override
    public void showSucces() {
        Toast.makeText(getActivity(),"All changes save", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGender(String gender) {
        vGender.setText(gender);
    }

    @Override
    public void returnDate(String date) {
        vDate.setText(date);
    }

    @Override
    public void returnDateMilisecond(long milisecond) {

    }

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

    private boolean validate() {
        boolean isValid = true;
        String ciggarete = vSigarette.getText().toString();
        String money = vMoney.getText().toString();
        String time = vTime.getText().toString();
        String ageSmoke = vAgeSmoke.getText().toString();
        String year = vDate.getText().toString();
        if (StringUtils.isNullEmpty(ageSmoke)) {
            isValid = false;
        }
        if (StringUtils.isNullEmpty(year)) {
            isValid = false;
        }
        if (StringUtils.isNullEmpty(ciggarete)) {
            isValid = false;
        }
        if (StringUtils.isNullEmpty(money)) {
            isValid = false;
        }
        if (StringUtils.isNullEmpty(time)) {
            isValid = false;
        }
        return isValid;
    }


    @OnClick(R.id.complete_third_fragment_date_btn)
    public void onDateClick() {
        getPresenter().onCalendarClick();
    }

    @OnClick(R.id.questionaries_fragment_save)
    public void onSaveClick(){
        if (validate()){
            String ageSmoke = vAgeSmoke.getText().toString();
            String year = vDate.getText().toString();
            String gender = vGender.getText().toString();
            String cigarete = vSigarette.getText().toString();
            String time = vTime.getText().toString();
            String money = vMoney.getText().toString();
            getPresenter().saveChanges(situation,cigarete,money,time,ageSmoke,year,gender);
        }
    }
}
