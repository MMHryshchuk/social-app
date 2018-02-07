package com.clearsoft.welivre.domain.use_cases.impl;

import android.os.Build;
import android.util.Log;

import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.api.AuthApi;
import com.clearsoft.welivre.domain.api.dto.UserDto;
import com.clearsoft.welivre.domain.api.request.LoginRequest;
import com.clearsoft.welivre.domain.api.request.RegisterRequest;
import com.clearsoft.welivre.domain.api.request.ResetRequest;
import com.clearsoft.welivre.domain.api.request.UserSituationRequest;
import com.clearsoft.welivre.domain.api.response.AuthDtoResponse;
import com.clearsoft.welivre.domain.entities.DaoMaster;
import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.mappers.ErrorMapper;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by on 04.07.17.
 */

public class AuthUseCaseImpl implements AuthUseCase {

    private DaoSession daoSession;
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private AuthApi authApi;
    private PreferenceRepository preferenceRepository;
    private SaveEntityRepository saveEntityRepository;
    private ErrorMapper errorMapper;

    public AuthUseCaseImpl(
            DaoSession daoSession,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            Retrofit retrofit,
            AuthRetrofit authRetrofit,
            PreferenceRepository preferenceRepository,
            SaveEntityRepository saveEntityRepository,
            ErrorMapper errorMapper
    ) {
        this.daoSession = daoSession;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.authApi = retrofit.create(AuthApi.class);
        this.preferenceRepository = preferenceRepository;
        this.saveEntityRepository = saveEntityRepository;
        this.errorMapper = errorMapper;
    }

    @Override
    public Observable<String> login(String email, String password) {
        return authApi.login(new LoginRequest(email, password))
                .doOnNext(o -> {
                    if (o.getResult() != null) {
                        saveUserInfoToPref(o.getResult());
                        preferenceRepository.saveUserPlanId(Integer.parseInt(o.getResult().getUserPlan()));
                        preferenceRepository.saveUserPassword(password);
                    }
                })
                .map(authDtoResponse -> {
                    if (authDtoResponse.getResult() == null) {
                        return authDtoResponse.getMessage();
                    }
                    return "";
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

    }

    @Override
    public Observable<String> register(String email, String name, String password, String lang,String token) {
        return authApi.register(new RegisterRequest(email, name, password, "1", Build.MODEL, token, lang))
                .doOnNext(o -> {
                    if (o.getResult() != null) {
                        saveUserInfoToPref(o.getResult());
                        preferenceRepository.saveUserPassword(password);

                    }

                })
                .map(authDtoResponse -> {
                    if (authDtoResponse.getResult() == null ) {
                        return authDtoResponse.getMessage();
                    }
                    return "";
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> reset(String email, String lang) {
        return authApi.reset(new ResetRequest(email, lang))
                .doOnNext(feedBackResponse -> {

                })
                .map(feedBackResponse -> {
                    return new Object();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }


    @Override
    public Observable<Object> logout() {
        return Observable.create(e -> {
            preferenceRepository.clear();
            DaoMaster.dropAllTables(daoSession.getDatabase(), true);
            DaoMaster.createAllTables(daoSession.getDatabase(), true);
            e.onNext(true);
            e.onComplete();
        })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    private void saveUserInfoToPref(UserDto dto){
        preferenceRepository.saveAccessToken(dto.getUserToken());
        preferenceRepository.saveUserId(dto.getUserId());
        preferenceRepository.saveUserName(dto.getUserName());
        preferenceRepository.saveUserEmail(dto.getUserEmail());
        if (!StringUtils.isNullEmpty(dto.getUserAvatat()))
            preferenceRepository.saveUserImage("http://welivreapp.com/welivre_ci/upload/profile/"+dto.getUserAvatat());
        preferenceRepository.saveUserLanguage(dto.getUserLanguage());
        preferenceRepository.saveUserAbout(dto.getUserAbout());
        preferenceRepository.saveUserDevice(dto.getUserDevice());
        preferenceRepository.saveUserFollows(dto.getUserFollows());
        preferenceRepository.saveUserFollowed(dto.getUserFollowed());
        preferenceRepository.saveUserPosts(dto.getUserPosts());
        preferenceRepository.saveUserSituation(dto.getUserSituation());
        preferenceRepository.saveUserCigaDayliNum(dto.getUserCigaDailyNum());
        preferenceRepository.saveUserCigaPackCost(dto.getUserCigaPackCost());
        preferenceRepository.saveUserCigaWakeupMinutes(dto.getUserCigaWakeupMinutes());
        preferenceRepository.saveUserCigaStartAge(dto.getUserCigaStartAge());
        preferenceRepository.saveUserBirthday(dto.getUserBirthday());
        preferenceRepository.saveUserGender(dto.getUserGender());
    }


}
