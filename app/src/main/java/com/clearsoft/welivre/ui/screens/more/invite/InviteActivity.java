package com.clearsoft.welivre.ui.screens.more.invite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.PanelActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 25.07.17.
 */

public class InviteActivity extends PanelActivity {

    public static void start(Activity activity){
        Intent intent = new Intent(activity,InviteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.activity_invite_whatsup)
    public void onWhatsUp(){
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Share via WhatsApp");
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,"WhatsApp is not installed on this device",Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.activity_invite_facebook)
    public void onFacebook(){
        try {
            Intent intent1 = new Intent();
            intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
            intent1.setAction("android.intent.action.SEND");
            intent1.setType("text/plain");
            intent1.putExtra("android.intent.extra.TEXT", "Share Via Facebook");
            startActivity(intent1);
        } catch (Exception e) {
            // If we failed (not native FB app installed), try share through SEND
//            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
//            startActivity(intent);
            Toast.makeText(this,"Facebook is not installed on this device",Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.activity_invite_twitter)
    public void onTwitter(){
        try
        {
            // Check if the Twitter app is installed on the phone.
            getPackageManager().getPackageInfo("com.twitter.android", 0);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setClassName("com.twitter.android", "com.twitter.android.composer.ComposerActivity");
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Your text");
            startActivity(intent);

        }
        catch (Exception e)
        {
            Toast.makeText(this,"Twitter is not installed on this device",Toast.LENGTH_LONG).show();

        }
    }

    @OnClick(R.id.activity_invite_more)
    public void onMoreInv(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Share via more");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,"Hello Friends"));
        onBackPressed();
    }

    @OnClick(R.id.toolbar_more_back_btn)
    public void close(){
        finish();
    }

}
