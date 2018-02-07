package com.clearsoft.welivre.core.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.clearsoft.welivre.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class ImageLoader {

    //TODO : replace 0
    public static void load(final ImageView imageView, String path, boolean user) {
        if (user) {
            loadImage(get(imageView.getContext()), path, R.drawable.placeholder_user, imageView);
        } else {
            loadImage(get(imageView.getContext()), path, R.drawable.placeholder_default, imageView);
        }
    }

    public static void load(final ImageView imageView, Uri path) {
        loadImage(get(imageView.getContext()), path, 0, imageView);
    }


    public static Picasso get(Context context) {
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(false);
        return picasso;
    }

    private static void loadImage(final Picasso picasso, final String path, final int plaseholder, final ImageView imageView) {
        if (StringUtils.isNullEmpty(path)) {
            loadRes(imageView, plaseholder);
            return;
        }


        picasso.load(path)
                .fit()
                .noFade()
                .centerCrop()
                .placeholder(plaseholder)
                .error(plaseholder)

                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        picasso.load(path)
                                .fit()
                                .centerCrop()
                                .placeholder(plaseholder)
                                .error(plaseholder)
                                .into(imageView);
                    }
                });
    }

    private static void loadImage(final Picasso picasso, final Uri path, final int plaseholder, final ImageView imageView) {
        if (path == null) {
            loadRes(imageView, plaseholder);
            return;
        }


        picasso.load(path)
                .fit()
                .noFade()
                .centerInside()
//               .placeholder(plaseholder)
//                .error(plaseholder)

                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        picasso.load(path)
                                .fit()
                                .centerInside()
//                                .placeholder(plaseholder)
//                                .error(plaseholder)
                                .into(imageView);
                    }
                });
    }


    public static void loadRes(ImageView imageView, int res) {
        get(imageView.getContext()).load(res).into(imageView);
    }


}
