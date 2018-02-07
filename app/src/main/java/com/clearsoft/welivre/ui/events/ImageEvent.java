package com.clearsoft.welivre.ui.events;

import android.net.Uri;

/**
 * Created by on 15.08.17.
 */

public class ImageEvent {

    private Uri uri;

    public ImageEvent(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }
}
