package com.clearsoft.welivre.ui.screens.auth.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.utils.EmailValidator;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.di.RegisterComponent;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;
import com.clearsoft.welivre.ui.screens.auth.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 28.06.17.
 */

public class RegisterActivity extends BaseActivity implements RegisterView{

    @BindView(R.id.activity_register_email)
    EditText vEmail;
    @BindView(R.id.activity_register_name)
    EditText vName;
    @BindView(R.id.activity_register_password)
    EditText vPassword;
    @BindView(R.id.activity_register_progress_frame)
    FrameLayout vProgressFrame;

    @Inject
    RegisterPresenter mRegisterPresenter;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setupDagger();
        mAuth = FirebaseAuth.getInstance();

    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new RegisterComponent.Module())
                .inject(this);
    }

    private boolean validate() {
        boolean isValid = true;
        String email = vEmail.getText().toString();
        String name = vName.getText().toString();
        String password = vPassword.getText().toString();
        if (StringUtils.isNullEmpty(email)) {
            isValid = false;
        }
        if (StringUtils.isNullEmpty(name)){
            isValid = false;
        }
        if (StringUtils.isNullEmpty(password)){
            isValid = false;
        }
        if (!EmailValidator.isValid(email)){
            isValid = false;
            vEmail.setError("e-mail not valid");
        }
        return isValid;
    }



    @Override
    protected void onStart() {
        super.onStart();
        mRegisterPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRegisterPresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        LoginActivity.start(this);
    }

    /* ----- View methods ----- */
    @Override
    public void openLogin() {
        LoginActivity.start(this);
    }

    @Override
    public void openCompleteRegister() {
        CompleteRegisterActivity.start(this);
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        vProgressFrame.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgressFrame.setVisibility(View.GONE);
    }


    @OnClick(R.id.activity_register_register_btn)
    public void onRegisterClick(){
        if (validate()){
            String email = vEmail.getText().toString();
            String name = vName.getText().toString();
            String password = vPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            mRegisterPresenter.onCreateClick(email,name,password,task.getResult().getUser().getUid());
                        }else {
                            Toast.makeText(RegisterActivity.this,task.getException()+ " Register failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @OnClick(R.id.activity_register_have_acc)
    public void onHaveAccClick(){
        mRegisterPresenter.onHaveAccClick();
    }
}
