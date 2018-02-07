package com.clearsoft.welivre.core.android;

import android.app.Application;
import android.content.Context;
import android.net.Uri;


import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.di.components.AppComponent;
import com.clearsoft.welivre.core.di.components.DaggerAppComponent;
import com.clearsoft.welivre.core.di.modules.DataModule;
import com.clearsoft.welivre.core.di.modules.MappersModule;
import com.clearsoft.welivre.core.di.modules.ApiModule;
import com.clearsoft.welivre.core.di.modules.AppModule;
import com.clearsoft.welivre.core.di.modules.ThreadExecutorsModule;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.fabric.sdk.android.Fabric;

import net.danlew.android.joda.JodaTimeAndroid;


/**

 */
public class App extends Application {

    private AppComponent appComponent;

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//             This process is dedicated to LeakCanary for heap analysis.
//             You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        Fabric.with(this, new Crashlytics(), new Answers());
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        FacebookSdk.setIsDebugEnabled(true);
//        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        JodaTimeAndroid.init(this);
        setupAppComponent();
        setupPicasso();
        setupChadSdk();
    }

    private void setupChadSdk() {
       /* // This is used for the app custom toast and activity transition
        ChatSDKUiHelper.initDefault();

// Init the network manager
        BNetworkManager.init(this);

// Create a new adapter
        BChatcatNetworkAdapter adapter = new BChatcatNetworkAdapter(this);

// Set the adapter
        BNetworkManager.sharedManager().setNetworkAdapter(adapter);*/
    }

    private void setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(getString(R.string.baseUrl), getString(R.string.uploadUrl)))
                .threadExecutorsModule(new ThreadExecutorsModule())
                .dataModule(new DataModule())
                .mappersModule(new MappersModule())
                .build();
    }

    private void setupPicasso() {
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        Picasso picasso = builder.build();
//        picasso.setIndicatorsEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }


}
