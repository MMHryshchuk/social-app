package com.clearsoft.welivre.ui.screens.settings.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.Optional;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.utils.EmailValidator;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.dvo.SettingsProfileDvo;
import com.clearsoft.welivre.ui.screens.popup.ProfileImagePopupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yalantis.ucrop.UCrop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.facebook.widget.ProfilePictureView.TAG;

/**
 * Created by on 25.07.17.
 */

public class SettingsProfileFragment extends ViewMvpFragment<SettingsProfilePresenter> implements SettingsProfileView {

    @BindView(R.id.settings_profile_fragment_image)
    ImageView vUserImage;
    @BindView(R.id.settings_profile_fragment_name_ed)
    EditText vNameEd;
    @BindView(R.id.settings_profile_fragment_email_ed)
    EditText vEmailEd;
    @BindView(R.id.settings_profile_fragment_password_ed)
    EditText vPasswordEd;
    @BindView(R.id.settings_profile_fragment_about_ed)
    EditText vAboutEd;
    @BindView(R.id.settings_profile_fragment_progress_frame)
    FrameLayout vProgressFrame;

    private PreferenceRepository preferenceRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_profile_fragment,container,false);
        ButterKnife.bind(this,view);
        preferenceRepository = App.getApp(getActivity())
                .getAppComponent()
                .getPreferenceRepository();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getPresenter().getView() == null)
        getPresenter().attachView(this);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            getPresenter().updateUserImage(UCrop.getOutput(data));
        }
    }

    private boolean validate() {
        boolean isValid = true;
        String email = vEmailEd.getText().toString();
        String name = vNameEd.getText().toString();
        String password = vPasswordEd.getText().toString();
        String about = vAboutEd.getText().toString();
        if (StringUtils.isNullEmpty(email)) {
            isValid = false;
        }
        if (StringUtils.isNullEmpty(name)){
            isValid = false;
        }
        if (StringUtils.isNullEmpty(password)){
            isValid = false;
        }
        if (StringUtils.isNullEmpty(about)){
            isValid = false;
        }
        if (!EmailValidator.isValid(email)){
            isValid = false;
            vEmailEd.setError("e-mail not valid");
        }
        return isValid;
    }

    @Override
    public void setData(SettingsProfileDvo dvo) {
        vNameEd.setText(dvo.getName());
        vEmailEd.setText(dvo.getEmail());
        vAboutEd.setText(dvo.getAbout());
        if (!StringUtils.isNullEmpty(dvo.getImage())){
            ImageLoader.load(vUserImage,dvo.getImage(),true);
        }
    }

    @Override
    public void updateImg(Uri path) {
        vUserImage.setImageURI(path);
    }

    @Override
    public void showProgress() {
        vProgressFrame.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgressFrame.setVisibility(View.GONE);
    }

    @Override
    public void updatePassword(String password) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential(preferenceRepository.getUserEmail(), preferenceRepository.getUserPassword());

// Prompt the user to re-provide their sign-in credentials
        if (user != null) {
            user.reauthenticate(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
//                                        Log.d(TAG, "Password updated");
                                    } else {
//                                        Log.d(TAG, "Error password not updated");
                                    }
                                }
                            });
                        } else {
//                            Log.d(TAG, "Error auth failed");
                        }
                    });
        }
    }

    @OnClick(R.id.settings_profile_fragment_image)
    public void onSetImage(){
        ProfileImagePopupActivity.startForResult(getActivity());
    }

    @OnClick(R.id.settings_profile_fragment_save_btn)
    public void onSaveClick(){
        if (validate()){
            String email = vEmailEd.getText().toString();
            String name = vNameEd.getText().toString();
            String password = vPasswordEd.getText().toString();
            String about = vAboutEd.getText().toString();
            getPresenter().saveUserInfo(name,email,password,about);
        }
    }
}
