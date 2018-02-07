package com.clearsoft.welivre.ui.screens.auth.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.ui.di.LoginComponent;
import com.clearsoft.welivre.ui.screens.auth.register.RegisterActivity;
import com.clearsoft.welivre.ui.screens.auth.reset.ResetActivity;
import com.clearsoft.welivre.ui.screens.exit.ExitActivity;
import com.clearsoft.welivre.ui.screens.home.HomeActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
//import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 27.06.17.
 */

public class LoginActivity extends BaseActivity implements LoginView, GoogleApiClient.OnConnectionFailedListener {

    protected static final int RC_SIGN_IN = 1999;
    protected static final int FB_SIGN_IN = 1998;

    protected static final int LOGIN_TYPE_FACEBOOK = 1991;
    protected static final int LOGIN_TYPE_GOOGLE = 1981;

    @BindView(R.id.activity_login_progress_frame)
    FrameLayout vProgress;
    @BindView(R.id.activity_login_email)
    EditText vEmail;
    @BindView(R.id.activity_login_password)
    EditText vPassword;


    @Inject
    LoginPresenter mLoginPresenter;

    private String TAG = "LoginActivity";
    private String userName;
    private String userAvatar;

    // FireBase Auth
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;
//    private CallbackManager mCallbackManager;
    private PreferenceRepository preferenceRepository;

    private int loginType = 0;


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupDagger();
        preferenceRepository = App.getApp(this)
                .getAppComponent()
                .getPreferenceRepository();
        if (!StringUtils.isNullEmpty(preferenceRepository.getAccessToken())) {
            openHome();
        }
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.e(TAG, "onAuthStateChanged:signed_in:" + user.getUid());


            } else {
                // User is signed out
                Log.e(TAG, "onAuthStateChanged:signed_out");
                hideProgress();
            }
            // ...
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mLoginPresenter.attachView(this);
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLoginPresenter.detachView();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean validate() {
        boolean isValid = true;
        String email = vEmail.getText().toString();
        String password = vPassword.getText().toString();
        if (StringUtils.isNullEmpty(email)) {
            isValid = false;
        }
        if (StringUtils.isNullEmpty(password)) {
            isValid = false;
        }
        if (!EmailValidator.isValid(email)) {
            isValid = false;
            vEmail.setError("e-mail not valid");
        }
        return isValid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ExitActivity.REQUEST_EXIT) {
            if (resultCode == ExitActivity.RESULT_POSITIVE) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
            return;
        }

        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = result.getSignInAccount();
//                firebaseAuthWithGoogle(account);
//            } else {
//                Log.d("GooGleSingIn", result.getStatus().toString() + " \n" + result.getStatus().getStatusMessage());
//                // Google Sign In failed, update UI appropriately
//                // [START_EXCLUDE]
//                Toast.makeText(this, "Activity Result fail", Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
        } else {
//            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgress();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = task.getResult().getUser();

//                        mLoginPresenter.saveToPref(user.getToken(true).toString(),user.getUid(),user.getDisplayName(),user.getEmail(),user.getPhotoUrl().getPath());

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                    // [START_EXCLUDE]
                    hideProgress();
                    // [END_EXCLUDE]
                });
    }

    private void onFbAuth() {
//        mCallbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().setLoginBehavior(LoginBehavior.WEB_ONLY);
//
//        LoginManager.getInstance().registerCallback(mCallbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        System.out.println("Success");
//
//                        showProgress();
//
//                        Bundle parameters = new Bundle();
//                        parameters.putString("fields", "id, first_name, last_name ,email, gender, cover, picture.type(large)");
//                        final AccessToken loginToken = loginResult.getAccessToken();
//
//                        GraphRequest gr = GraphRequest.newMeRequest(loginToken, (json, response) -> {
//                            if (response.getError() != null) {
//                                hideProgress();
//                            } else {
//                                System.out.println("Success");
//
//                                Bundle bFacebookData = getFacebookData(json);
//                                userName = bFacebookData.getString("first_name") + " " + bFacebookData.getString("last_name");
//                                userAvatar = bFacebookData.getString("profile_pic");
//                                Log.e("From Facebook :", userName);
//                                handleFacebookAccessToken(loginToken);
//                            }
//                        });
//                        gr.setParameters(parameters);
//                        gr.executeAsync();
//                    }
//
//                    @Override
//                    public void onCancel() {
//                    }
//
//                    @Override
//                    public void onError(FacebookException error) {
//                    }
//                });
//
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));

    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            Log.e("Facebook Info", bundle.toString());
            return bundle;

        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.toString());
        }

        return null;
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = task.getResult().getUser();
                        Log.d(TAG, "user : " + user.getDisplayName());
//                        mLoginPresenter.checkIfRegister(user.getDisplayName(),user.getEmail());
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                    hideProgress();
                    // ...
                });
    }


    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new LoginComponent.Module())
                .inject(this);
    }



    /* ----------- View methods -----------*/

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openHome() {
        HomeActivity.start(this);
        finish();
    }

    @Override
    public void openReset() {
        ResetActivity.start(this);
    }

    @Override
    public void openRegister() {
        RegisterActivity.start(this);
        finish();
    }

    @Override
    public void loginWithGoogle() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);

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
    public void onBackPressed() {
        ExitActivity.start(this);
    }

    @OnClick(R.id.activity_login_create_btn)
    public void onCreateClick() {
        mLoginPresenter.onCreateClick();
    }

    @OnClick(R.id.activity_login_forgot_btn)
    public void onForgotClick() {
        mLoginPresenter.onForgotClick();
    }

    @OnClick(R.id.activity_login_login_btn)
    public void onLoginClick() {
        if (validate()) {
            String email = vEmail.getText().toString();
            String password = vPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mLoginPresenter.onLoginClick(email, password);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    @OnClick(R.id.activity_login_facebook_btn)
    public void onFacebookClick() {
//        onFbAuth();
    }

    @OnClick(R.id.activity_login_google_btn)
    public void onGoogleClick() {
//        mLoginPresenter.onGoogleClick();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
