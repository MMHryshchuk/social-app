package com.clearsoft.welivre.ui.events;

import android.net.Uri;

/**
 * Created by on 30.08.17.
 */

public class UriEvent {

    private Uri uri;

    public UriEvent() {
    }

    public UriEvent(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
