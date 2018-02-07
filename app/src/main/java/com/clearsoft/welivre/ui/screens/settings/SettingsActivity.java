package com.clearsoft.welivre.ui.screens.settings;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.Optional;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.core.bus.Bus;
import com.clearsoft.welivre.core.bus.EventBus;
import com.clearsoft.welivre.core.utils.PermissionHelper;
import com.clearsoft.welivre.ui.di.SettingsComponent;
import com.clearsoft.welivre.ui.events.AboutEvent;
import com.clearsoft.welivre.ui.events.ImageEvent;
import com.clearsoft.welivre.ui.events.UriEvent;
import com.clearsoft.welivre.ui.screens.popup.ProfileImagePopupActivity;
import com.clearsoft.welivre.ui.screens.profile.about.AboutFragment;
import com.clearsoft.welivre.ui.screens.settings.adapter.SettingsPagerAdapter;
import com.clearsoft.welivre.ui.screens.settings.privacy.PrivacyFragment;
import com.clearsoft.welivre.ui.screens.settings.privacy.PrivacyPresenter;
import com.clearsoft.welivre.ui.screens.settings.profile.SettingsProfileFragment;
import com.clearsoft.welivre.ui.screens.settings.profile.SettingsProfilePresenter;
import com.clearsoft.welivre.ui.screens.settings.questionaries.QuestionariesFragment;
import com.clearsoft.welivre.ui.screens.settings.questionaries.QuestionariesPresenter;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 25.07.17.
 */

public class SettingsActivity extends PanelActivity{

    public static final int RESULT_GALLERY_IMAGE = 848;
    public static final int RESULT_CAMERA_IMAGE = 849;
    public static int RESULT_CAMERA_PERMISSION = 1333;
    public static int RESULT_GALERY_PERMISSION = 2333;

    @BindView(R.id.activity_settings_txt_profile)
    TextView txtProfile;
    @BindView(R.id.activity_settings_txt_question)
    TextView txtQuestion;
    @BindView(R.id.activity_settings_txt_privacy)
    TextView txtPrivacy;

    @BindView(R.id.activity_settings_divider_profile)
    View dividerProfile;
    @BindView(R.id.activity_settings_divider_question)
    View dividerQuestion;
    @BindView(R.id.activity_settings_divider_privacy)
    View dividerPrivacy;

    @Inject
    SettingsProfilePresenter mProfilePresenter;
    @Inject
    QuestionariesPresenter mQuestionariesPresenter;
    @Inject
    PrivacyPresenter mPrivacyPresenter;
    @Inject
    Bus bus;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, SettingsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setupDagger();
        bus = App.getApp(this)
                .getAppComponent()
                .getBus();
        openProfile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProfileImagePopupActivity.REQUEST_IMAGE){
            if (resultCode == ProfileImagePopupActivity.RESULT_GALLERY){
                Optional<SettingsProfileFragment> fragment = findFragment(SettingsProfileFragment.class);
                if (fragment.isFragment() && mProfilePresenter.getView() != null) {
                    mProfilePresenter.checkGalleryPermission(this);
                }
            }else if (resultCode == ProfileImagePopupActivity.RESULT_CAMERA){
                Optional<SettingsProfileFragment> fragment = findFragment(SettingsProfileFragment.class);
                if (fragment.isFragment() && mProfilePresenter.getView() != null) {
                    mProfilePresenter.checkCameraPermission(this);
                }
            }
        }
        if (requestCode == RESULT_GALLERY_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            mProfilePresenter.cropImage(this,selectedImage);
        }
        if (requestCode == RESULT_CAMERA_IMAGE && resultCode == RESULT_OK ){
            mProfilePresenter.cropImage(this,null);
        }

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            Optional<SettingsProfileFragment> fragment = findFragment(SettingsProfileFragment.class);
            fragment.get().onActivityResult(requestCode, resultCode, data);
            bus.post(UCrop.getOutput(data));
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RESULT_CAMERA_PERMISSION){
            String writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
            String cameraPermission = android.Manifest.permission.CAMERA;
            if (PermissionHelper.isGrantedPermissions(this,writePermission,cameraPermission)){
                mProfilePresenter.onCameraChange(this);
            }
        }
        if (requestCode == RESULT_GALERY_PERMISSION){
            String galleryPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            if (PermissionHelper.isGrantedPermissions(this,galleryPermission)){
                mProfilePresenter.onGalleryChange(this);
            }

        }
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new SettingsComponent.Module())
                .inject(this);
    }

    private void setActive(TextView text, View divider){
        txtProfile.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        txtQuestion.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));
        txtPrivacy.setTextColor(ContextCompat.getColor(this,R.color.colorTextSecondary));

        dividerProfile.setVisibility(View.GONE);
        dividerQuestion.setVisibility(View.GONE);
        dividerPrivacy.setVisibility(View.GONE);

        divider.setVisibility(View.VISIBLE);
        text.setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryWhite));

    }

    private void openProfile(){
        setActive(txtProfile,dividerProfile);
        replaceFragment(mProfilePresenter,new SettingsProfileFragment());
    }

    private void openQuestion(){
        setActive(txtQuestion,dividerQuestion);
        replaceFragment(mQuestionariesPresenter,new QuestionariesFragment());
    }

    private void openPrivacy(){
        setActive(txtPrivacy,dividerPrivacy);
        replaceFragment(mPrivacyPresenter,new PrivacyFragment());
    }

    @OnClick(R.id.activity_settings_btn_profile)
    public void onProfileClick(){
        openProfile();
    }

    @OnClick(R.id.activity_settings_btn_question)
    public void onQusetionClick(){
        openQuestion();
    }

    @OnClick(R.id.activity_settings_btn_privacy)
    public void onPrivacyClick(){
        openPrivacy();
    }


    @EventBus
    @Subscribe
    public void onImageUpdateEvent(ImageEvent event) {
        Optional<SettingsProfileFragment> fragment = findFragment(SettingsProfileFragment.class);
        if (fragment.isFragment() && mProfilePresenter.getView() != null) {
            mProfilePresenter.update(event.getUri());
        }
    }


    @EventBus
    @Subscribe
    public void onUriUpdateEvent(UriEvent event) {
        Optional<SettingsProfileFragment> fragment = findFragment(SettingsProfileFragment.class);
        if (fragment.isFragment() && mProfilePresenter.getView() != null) {
            mProfilePresenter.updateUserImage(event.getUri());
        }
    }
}
