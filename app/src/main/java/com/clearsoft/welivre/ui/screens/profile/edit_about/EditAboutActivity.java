package com.clearsoft.welivre.ui.screens.profile.edit_about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.di.EditAboutComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 11.08.17.
 */

public class EditAboutActivity extends BaseActivity implements EditAboutView {

    @BindView(R.id.activity_about_edit_user_name)
    TextView vUserName;
    @BindView(R.id.activity_about_edit_user_img)
    ImageView vUserImg;
    @BindView(R.id.activity_about_edit_txt)
    EditText vAboutTxt;
    @BindView(R.id.activity_about_edit_progress)
    FrameLayout vProgressFrame;

    @Inject
    EditAboutPresenter mEditAboutPresenter;
    @Inject
    PreferenceRepository preferenceRepository;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, EditAboutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_edit);
        ButterKnife.bind(this);
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
        setupDagger();
        vUserName.setText(preferenceRepository.getUserName());
        if (!StringUtils.isNullEmpty(preferenceRepository.getUserImage()))
        ImageLoader.load(vUserImg,preferenceRepository.getUserImage(),true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEditAboutPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEditAboutPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new EditAboutComponent.Module())
                .inject(this);
    }

    @Override
    public void closeEdit() {
        finish();
    }

    @Override
    public void showProgress() {
        vProgressFrame.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgressFrame.setVisibility(View.GONE);
    }


    @OnClick(R.id.activity_about_edit_save_btn)
    public void onSaveClick(){
        String about = vAboutTxt.getText().toString();
        if (!StringUtils.isNullEmpty(about))
        mEditAboutPresenter.saveAbout(about);
    }

    @OnClick(R.id.activity_about_edit_close_btn)
    public void clsoe(){
        finish();
    }



}
