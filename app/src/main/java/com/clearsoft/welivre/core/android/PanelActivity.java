package com.clearsoft.welivre.core.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.ui.screens.home.HomeActivity;
import com.clearsoft.welivre.ui.screens.messenger.MessengerActivity;
import com.clearsoft.welivre.ui.screens.more.MoreActivity;
import com.clearsoft.welivre.ui.screens.my_training.MyTrainingActivity;
import com.clearsoft.welivre.ui.screens.sos.SosActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 29.06.17.
 */

public class PanelActivity extends BaseActivity {

     @BindView(R.id.home_panel_home)@Nullable
     ImageView vHome;
     @BindView(R.id.home_panel_training)@Nullable
     ImageView vTraining;
     @BindView(R.id.home_panel_sos)@Nullable
     ImageView vSos;
     @BindView(R.id.home_panel_messenger)@Nullable
     ImageView vMessenger;
     @BindView(R.id.home_panel_more)@Nullable
     ImageView vMore;
     @BindView(R.id.feed_text)@Nullable
     TextView vHomeText;
     @BindView(R.id.training_text)@Nullable
     TextView vTrainingText;
     @BindView(R.id.sos_text)@Nullable
     TextView vSosText;
     @BindView(R.id.msg_text)@Nullable
     TextView vMsgText;
     @BindView(R.id.more_text)@Nullable
     TextView vMoreText;
//    @BindView(R.id.bottom_bar_view)
   /* @Nullable
    BottomBar bottomBar;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setAllInActive();
        setActive();
//        setTabListener();
    }

   /* public void setTabListener(){
        assert bottomBar != null;
        if (this instanceof HomeActivity){
            bottomBar.selectTabWithId(R.id.tab_home);
        }else if (this instanceof  MessengerActivity){
            bottomBar.selectTabWithId(R.id.tab_messenger);

        } else if (this instanceof MoreActivity){
            bottomBar.selectTabWithId(R.id.tab_more);

        }
        bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.tab_home:
                    if (!(this instanceof HomeActivity))
                    HomeActivity.start(this);
                    return;
                case R.id.tab_training:
                    return;
                case R.id.tab_sos :
                    return;
                case R.id.tab_messenger:
                    if (!(this instanceof MessengerActivity))
                    MessengerActivity.start(this);

                    return;
                case R.id.tab_more:
                    if (!(this instanceof MoreActivity))
                    MoreActivity.start(this);
                    return;
                default:
                    return;
            }
        });

    }*/

    private void setAllInActive() {
        vHome.setImageResource(R.drawable.ic_home_tabic_incative);
        vTraining.setImageResource(R.drawable.ic_training_tabic_inactive);
        vMore.setImageResource(R.drawable.ic_more_tabic_inactive);
        vMessenger.setImageResource(R.drawable.ic_message_tabic_inactive);
        vSos.setImageResource(R.drawable.ic_sos_tabic_inactive);
        vHomeText.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        vTrainingText.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        vMoreText.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        vMsgText.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
        vSosText.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
    }

    private void setActive(){
        if (this instanceof HomeActivity){
            vHome.setImageResource(R.drawable.ic_home_tabic_active);
            vHomeText.setTextColor(getResources().getColor(R.color.colorBackgroundBlue));
        }else if (this instanceof MessengerActivity){
            vMessenger.setImageResource(R.drawable.ic_message_tabic_active);
            vMsgText.setTextColor(getResources().getColor(R.color.colorBackgroundBlue));
        }else if (this instanceof  MoreActivity){
            vMore.setImageResource(R.drawable.ic_more_tabic_active);
            vMoreText.setTextColor(getResources().getColor(R.color.colorBackgroundBlue));
        }else if (this instanceof MyTrainingActivity){
            vTraining.setImageResource(R.drawable.ic_training_tabic_active);
            vTrainingText.setTextColor(getResources().getColor(R.color.colorBackgroundBlue));
        }else if (this instanceof SosActivity){
            vSos.setImageResource(R.drawable.ic_sos_tabic_active);
            vSosText.setTextColor(getResources().getColor(R.color.colorBackgroundBlue));
        }else {

        }
    }


    @OnClick(R.id.panel_home)
    public void onHomeClick() {
        if (this instanceof  HomeActivity) return;
        HomeActivity.start(this);
    }

    @OnClick(R.id.panel_training)
    public void onTrainingClick() {
        if (this instanceof MyTrainingActivity) return;
        MyTrainingActivity.start(this);
    }

    @OnClick(R.id.panel_sos)
    public void onSosTabClick() {
        if (this instanceof SosActivity) return;
        SosActivity.start(this);
    }

    @OnClick(R.id.panel_messenger)
    public void onMessengerClick() {

    }

    @OnClick(R.id.panel_more)
    public void onMoreClick() {
        if (this instanceof MoreActivity)return;

        MoreActivity.start(this);
    }
}
