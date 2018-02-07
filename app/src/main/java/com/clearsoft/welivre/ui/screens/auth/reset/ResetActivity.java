package com.clearsoft.welivre.ui.screens.auth.reset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.utils.EmailValidator;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.di.ResetComponent;
import com.clearsoft.welivre.ui.screens.auth.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 28.06.17.
 */

public class ResetActivity extends BaseActivity implements ResetView {

    @BindView(R.id.activity_reset_email)
    EditText vEmail;

    @Inject
    ResetPresenter mResetPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, ResetActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mResetPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResetPresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new ResetComponent.Module())
                .inject(this);
    }

    private boolean validate() {
        boolean isValid = true;
        String email = vEmail.getText().toString();

        if (StringUtils.isNullEmpty(email)) {
            isValid = false;
        }

        if (!EmailValidator.isValid(email)) {
            isValid = false;
            vEmail.setError("e-mail not valid");
        }
        return isValid;
    }

    @Override
    public void openLogin() {
        LoginActivity.start(this);
        finish();
    }

    @OnClick(R.id.activity_reset_back_btn)
    public void onBackClick(){
        mResetPresenter.onBackClick();
    }

    @OnClick(R.id.activity_reset_reset_btn)
    public void onResetClick(){
        if (validate()){
            String email = vEmail.getText().toString();
            mResetPresenter.onResetClick(email);
        }
    }
}
