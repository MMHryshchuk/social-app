package com.clearsoft.welivre.domain.use_cases.impl;

import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.android.UploadRetrofit;
import com.clearsoft.welivre.core.bus.Bus;
import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;
import com.clearsoft.welivre.domain.api.ImageApi;
import com.clearsoft.welivre.domain.api.request.ImageUploadRequest;
import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.use_cases.UploadUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by on 28.07.17.
 */

public class UploadUseCaseImpl implements UploadUseCase {


    private ImageApi imageApi;
    private Bus bus;
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private PreferenceRepository preferenceRepository;

    public UploadUseCaseImpl(
            ImageApi imageApi,
            Bus bus,
            DaoSession daoSession,
            UploadRetrofit uploadRetrofit,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            SaveEntityRepository saveEntityRepository,
            PreferenceRepository preferenceRepository
    ) {

        this.imageApi = imageApi;
        this.preferenceRepository = preferenceRepository;
        this.bus = bus;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    @Override
    public Observable<String> uploadPostImage(String path) {
        return imageApi.uploadImage(path)
                .doOnNext(o -> {

                })
                .map(o -> {
                    return new String();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

    }

    @Override
    public Observable<String> uploadAvatarImage(String path) {
        return null;
    }
}
