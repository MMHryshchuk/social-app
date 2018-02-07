package com.clearsoft.welivre.domain.use_cases.impl;

import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;
import com.clearsoft.welivre.domain.api.ContentApi;
import com.clearsoft.welivre.domain.api.FeedApi;
import com.clearsoft.welivre.domain.mappers.ErrorMapper;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.use_cases.ContentUseCase;

import retrofit2.Retrofit;

/**
 * Created by on 29.08.17.
 */

public class ContentUseCaseImpl implements ContentUseCase {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private ContentApi contentApi;
    private PreferenceRepository preferenceRepository;
    private SaveEntityRepository saveEntityRepository;
    private ErrorMapper errorMapper;

    public ContentUseCaseImpl(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            Retrofit retrofit,
            AuthRetrofit authRetrofit,
            PreferenceRepository preferenceRepository,
            SaveEntityRepository saveEntityRepository,
            ErrorMapper errorMapper
    ) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.contentApi = authRetrofit.create(ContentApi.class);
        this.preferenceRepository = preferenceRepository;
        this.saveEntityRepository = saveEntityRepository;
        this.errorMapper = errorMapper;
    }


}
