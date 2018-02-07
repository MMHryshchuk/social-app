package com.clearsoft.welivre.ui.screens.sos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.screens.awards.AwardsActivity;
import com.clearsoft.welivre.ui.screens.favorite.FavoriteActivity;
import com.clearsoft.welivre.ui.screens.motivation.MotivationActivity;
import com.clearsoft.welivre.ui.screens.my_plan.MyPlanActivity;
import com.clearsoft.welivre.ui.screens.support.SupportActivity;
import com.clearsoft.welivre.ui.screens.video_help.VideoHelpActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 25.07.17.
 */

public class SosActivity extends PanelActivity {


    public static void start(Activity activity){
        Intent intent = new Intent(activity, SosActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        ButterKnife.bind(this);

    }



    @OnClick(R.id.activity_sos_favorites)
    public void openFavorites(){
        FavoriteActivity.start(this);
    }

    @OnClick(R.id.activity_sos_plan)
    public void openPlan(){
        MyPlanActivity.start(this);
    }

    @OnClick(R.id.activity_sos_awards)
    public void openAwards(){
        AwardsActivity.start(this);
    }

    @OnClick(R.id.activity_sos_motivations)
    public void openMotivations(){
        MotivationActivity.start(this);
    }

    @OnClick(R.id.activity_sos_video)
    public void openVideoHelp(){
        VideoHelpActivity.start(this);
    }

    @OnClick(R.id.activity_sos_support)
    public void openRequestSupport(){
        SupportActivity.start(this);
    }
}
