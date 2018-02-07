package com.clearsoft.welivre.core.android;

import retrofit2.Retrofit;

/**
 * Created by vladimir on 08.06.16.
 */
public class AuthRetrofit {
    private Retrofit retrofit;

    public AuthRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T create(Class<T> api){
        return retrofit.create(api);
    }
}
