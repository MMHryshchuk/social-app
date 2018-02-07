package com.clearsoft.welivre.domain.api;

import com.clearsoft.welivre.domain.api.request.LoginRequest;
import com.clearsoft.welivre.domain.api.request.RegisterRequest;
import com.clearsoft.welivre.domain.api.request.ResetRequest;
import com.clearsoft.welivre.domain.api.request.UserSituationRequest;
import com.clearsoft.welivre.domain.api.response.AuthDtoResponse;
import com.clearsoft.welivre.domain.api.response.FeedBackResponse;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by on 03.07.17.
 */

public interface AuthApi {

    @POST("mobile/usermanager/login")
    Observable<AuthDtoResponse> login (@Body LoginRequest request);


    @POST("mobile/usermanager/signUp")
    Observable<AuthDtoResponse> register(@Body RegisterRequest request);

    @POST("mobile/usermanager/forgotPassword")
    Observable<FeedBackResponse> reset(@Body ResetRequest request);


}
