package com.clearsoft.welivre.core.android;

import retrofit2.Retrofit;

/**
 * Created by on 28.07.17.
 */

public class UploadRetrofit {

    private Retrofit retrofit;

    public UploadRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T create(Class<T> api){
        return retrofit.create(api);
    }
}
