package com.clearsoft.welivre.core.di.modules;

import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.android.UploadRetrofit;
import com.clearsoft.welivre.domain.api.ImageApi;
import com.clearsoft.welivre.domain.mappers.ErrorMapper;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {
    private String baseUrl;
    private String uploadUrl;


    public ApiModule(String baseUrl, String uploadUrl) {
        this.baseUrl = baseUrl;
        this.uploadUrl = uploadUrl;
    }

    @Provides
    @Singleton
    @Named("SimpleOkHttp")
    OkHttpClient provideSimpleOkHttpClient(){
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)
                .addInterceptor(interceptor)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    @Named("AuthOkHttp")
    OkHttpClient provideAuthOkHttpClient(PreferenceRepository preferenceRepository){
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(chain -> {
                    Request request = chain.request();
//                    String token = preferenceRepository.getAccessToken();
                    String token = "";
//                    if (StringUtils.isNullEmpty(token)){
//                        throw new IOException("401 Not auth");
//                    }

                    request = request.newBuilder()
                                .addHeader("Authorization", token)
                            .build();

                    return chain.proceed(request);
                })
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        Gson gson = new Gson();
        return gson;
    }

    @Singleton
    @Provides
    ImageApi provideImageApi(UploadRetrofit uploadRetrofit){
        return new ImageApi(uploadRetrofit);
    }

    @Provides
    @Singleton
    ErrorMapper provideErrorMapper(Gson gson){
        return new ErrorMapper(gson);
    }


    @Singleton
    @Provides
    Retrofit provideSimpleRetrofit(@Named("SimpleOkHttp") OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    @Singleton
    @Provides
    AuthRetrofit provideAuthRetrofit(@Named("AuthOkHttp") OkHttpClient okHttpClient, Gson gson){
        return new AuthRetrofit( new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build());

    }


    @Singleton
    @Provides
    UploadRetrofit provideImgRetrofit(@Named("AuthOkHttp") OkHttpClient okHttpClient, Gson gson){
        return new UploadRetrofit( new Retrofit.Builder()
                .baseUrl(uploadUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build());

    }
}
