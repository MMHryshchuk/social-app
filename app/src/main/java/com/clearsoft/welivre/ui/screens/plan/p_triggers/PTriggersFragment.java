package com.clearsoft.welivre.ui.screens.plan.p_triggers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.screens.plan.PlanActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 19.07.17.
 */

public class PTriggersFragment extends ViewMvpFragment<PTriggersPresenter> implements PTriggersView {

    @BindView(R.id.trigger_emotional_view)
    LinearLayout vEmotionalView;
    @BindView(R.id.trigger_habits_view)
    LinearLayout vHabitsView;
    @BindView(R.id.trigger_social_view)
    LinearLayout vSocialView;
    @BindView(R.id.p_triggers_fragment_emotional_txt)
    TextView vEmotional;
    @BindView(R.id.p_triggers_fragment_habits_txt)
    TextView vHabits;
    @BindView(R.id.p_triggers_fragment_social_txt)
    TextView vSocial;
    @BindView(R.id.p_triggers_fragment_emotional_divider)
    View vEmotionalDivider;
    @BindView(R.id.p_triggers_fragment_habits_divider)
    View vHabitsDivider;
    @BindView(R.id.p_triggers_fragment_social_divider)
    View vSocialDivider;
    @BindView(R.id.trigger_emotional_btn_1)
    LinearLayout vTriggerLsy_btn1;
    @BindView(R.id.trigger_emotional_btn_2)
    LinearLayout vTriggerLsy_btn2;
    @BindView(R.id.trigger_emotional_btn_3)
    LinearLayout vTriggerLsy_btn3;
    @BindView(R.id.trigger_emotional_btn_4)
    LinearLayout vTriggerLsy_btn4;
    @BindView(R.id.trigger_emotional_btn_5)
    LinearLayout vTriggerLsy_btn5;
    @BindView(R.id.trigger_emotional_btn_6)
    LinearLayout vTriggerLsy_btn6;
    @BindView(R.id.trigger_emotional_btn_7)
    LinearLayout vTriggerLsy_btn7;
    @BindView(R.id.trigger_emotional_btn_8)
    LinearLayout vTriggerLsy_btn8;
    @BindView(R.id.trigger_emotional_btn_9)
    LinearLayout vTriggerLsy_btn9;
    @BindView(R.id.trigger_emotional_btn_10)
    LinearLayout vTriggerLsy_btn10;
    @BindView(R.id.trigger_emotional_btn_11)
    LinearLayout vTriggerLsy_btn11;
    @BindView(R.id.trigger_emotional_btn_12)
    LinearLayout vTriggerLsy_btn12;
    @BindView(R.id.trigger_emotional_btn_13)
    LinearLayout vTriggerLsy_btn13;
    @BindView(R.id.trigger_emotional_btn_14)
    LinearLayout vTriggerLsy_btn14;

    @BindView(R.id.trigger_emotional_image_1)
    ImageView vTriggerImg1;
    @BindView(R.id.trigger_emotional_image_2)
    ImageView vTriggerImg2;
    @BindView(R.id.trigger_emotional_image_3)
    ImageView vTriggerImg3;
    @BindView(R.id.trigger_emotional_image_4)
    ImageView vTriggerImg4;
    @BindView(R.id.trigger_emotional_image_5)
    ImageView vTriggerImg5;
    @BindView(R.id.trigger_emotional_image_6)
    ImageView vTriggerImg6;
    @BindView(R.id.trigger_emotional_image_7)
    ImageView vTriggerImg7;
    @BindView(R.id.trigger_emotional_image_8)
    ImageView vTriggerImg8;
    @BindView(R.id.trigger_emotional_image_9)
    ImageView vTriggerImg9;
    @BindView(R.id.trigger_emotional_image_10)
    ImageView vTriggerImg10;
    @BindView(R.id.trigger_emotional_image_11)
    ImageView vTriggerImg11;
    @BindView(R.id.trigger_emotional_image_12)
    ImageView vTriggerImg12;
    @BindView(R.id.trigger_emotional_image_13)
    ImageView vTriggerImg13;
    @BindView(R.id.trigger_emotional_image_14)
    ImageView vTriggerImg14;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_triggers_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTabOnClickListener();
        setTabActive(vEmotionalView, vEmotional, vEmotionalDivider);
        setItemClickListener();

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


    private void setTabOnClickListener() {
        vEmotional.setOnClickListener(v -> {
            setTabActive(vEmotionalView, vEmotional, vEmotionalDivider);
        });
        vHabits.setOnClickListener(v -> {
            setTabActive(vHabitsView, vHabits, vHabitsDivider);
        });
        vSocial.setOnClickListener(v -> {
            setTabActive(vSocialView, vSocial, vSocialDivider);
        });
    }

    private void setItemClickListener() {
        vTriggerLsy_btn1.setOnClickListener(v -> {
            vTriggerImg1.setSelected(!vTriggerImg1.isSelected());
        });
        vTriggerLsy_btn2.setOnClickListener(v -> {
            vTriggerImg2.setSelected(!vTriggerImg2.isSelected());
        });
        vTriggerLsy_btn3.setOnClickListener(v -> {
            vTriggerImg3.setSelected(!vTriggerImg3.isSelected());


        });
        vTriggerLsy_btn4.setOnClickListener(v -> {
            vTriggerImg4.setSelected(!vTriggerImg4.isSelected());

        });
        vTriggerLsy_btn5.setOnClickListener(v -> {
            vTriggerImg5.setSelected(!vTriggerImg5.isSelected());

        });
        vTriggerLsy_btn6.setOnClickListener(v -> {
            vTriggerImg6.setSelected(!vTriggerImg6.isSelected());

        });
        vTriggerLsy_btn7.setOnClickListener(v -> {
            vTriggerImg7.setSelected(!vTriggerImg7.isSelected());

        });
        vTriggerLsy_btn8.setOnClickListener(v -> {
            vTriggerImg8.setSelected(!vTriggerImg8.isSelected());

        });
        vTriggerLsy_btn9.setOnClickListener(v -> {
            vTriggerImg9.setSelected(!vTriggerImg9.isSelected());

        });
        vTriggerLsy_btn10.setOnClickListener(v -> {
            vTriggerImg10.setSelected(!vTriggerImg10.isSelected());

        });
        vTriggerLsy_btn11.setOnClickListener(v -> {
            vTriggerImg11.setSelected(!vTriggerImg11.isSelected());

        });
        vTriggerLsy_btn12.setOnClickListener(v -> {
            vTriggerImg12.setSelected(!vTriggerImg12.isSelected());

        });
        vTriggerLsy_btn13.setOnClickListener(v -> {
            vTriggerImg13.setSelected(!vTriggerImg13.isSelected());

        });
        vTriggerLsy_btn14.setOnClickListener(v -> {
            vTriggerImg14.setSelected(!vTriggerImg14.isSelected());

        });



    }


    @OnClick(R.id.p_triggers_fragment_back_btn_frame)
    public void onBackClick() {
        getPresenter().onBackClick((PlanActivity) getActivity());
    }

    @OnClick(R.id.p_triggers_fragment_next_btn_frame)
    public void onNextClick() {
        if (!StringUtils.isNullEmpty(getTriggersIds()))
        getPresenter().onNextClick((PlanActivity) getActivity(), getTriggersIds());
    }


    @Override
    public void setTabActive(LinearLayout triggerView, TextView textView, View divider) {
        vEmotionalView.setVisibility(View.GONE);
        vHabitsView.setVisibility(View.GONE);
        vSocialView.setVisibility(View.GONE);

        vEmotionalDivider.setVisibility(View.GONE);
        vHabitsDivider.setVisibility(View.GONE);
        vSocialDivider.setVisibility(View.GONE);

        vEmotional.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextSecondary));
        vHabits.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextSecondary));
        vSocial.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextSecondary));

        triggerView.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryBlack));

    }


    private String getTriggersIds() {
        boolean first = true;
        String triggersId = "";

        if (vTriggerImg1.isSelected()) {
            triggersId += "1";
            first = false;

        }
        if (vTriggerImg2.isSelected()) {
            if (first) {
                triggersId += "2";
                first = false;
            } else {
                triggersId += ",2";

            }
        }
        if (vTriggerImg3.isSelected()) {
            if (first) {
                triggersId += "3";
                first = false;
            } else {
                triggersId += ",3";

            }
        }
        if (vTriggerImg4.isSelected()) {
            if (first) {
                triggersId += "4";
                first = false;
            } else {
                triggersId += ",4";

            }
        }
        if (vTriggerImg5.isSelected()) {
            if (first) {
                triggersId += "5";
                first = false;

            } else {
                triggersId += ",5";

            }
        }
        if (vTriggerImg6.isSelected()) {
            if (first) {
                triggersId += "6";
                first = false;

            } else {
                triggersId += ",6";

            }
        }

        if (vTriggerImg7.isSelected()) {
            if (first) {
                triggersId += "7";
                first = false;

            } else {
                triggersId += ",7";

            }
        }

        if (vTriggerImg8.isSelected()) {
            if (first) {
                triggersId += "8";
                first = false;

            } else {
                triggersId += ",8";

            }
        }

        if (vTriggerImg9.isSelected()) {
            if (first) {
                triggersId += "9";
                first = false;

            } else {
                triggersId += ",9";

            }
        }

        if (vTriggerImg10.isSelected()) {
            if (first) {
                triggersId += "10";
                first = false;

            } else {
                triggersId += ",10";

            }
        }

        if (vTriggerImg11.isSelected()) {
            if (first) {
                triggersId += "11";
                first = false;

            } else {
                triggersId += ",11";

            }
        }

        if (vTriggerImg12.isSelected()) {
            if (first) {
                triggersId += "12";
                first = false;

            } else {
                triggersId += ",12";

            }
        }

        if (vTriggerImg13.isSelected()) {
            if (first) {
                triggersId += "13";
                first = false;

            } else {
                triggersId += ",13";

            }
        }

        if (vTriggerImg14.isSelected()) {
            if (first) {
                triggersId += "14";
                first = false;

            } else {
                triggersId += ",14";

            }
        }

        return triggersId;
    }
}
