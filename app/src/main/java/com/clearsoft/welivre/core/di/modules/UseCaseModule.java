package com.clearsoft.welivre.core.di.modules;


import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.android.UploadRetrofit;
import com.clearsoft.welivre.core.bus.Bus;
import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;
import com.clearsoft.welivre.domain.api.ImageApi;
import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.mappers.ErrorMapper;
import com.clearsoft.welivre.domain.mappers.FeedMapper;
import com.clearsoft.welivre.domain.mappers.FollowMapper;
import com.clearsoft.welivre.domain.mappers.PlanMapper;
import com.clearsoft.welivre.domain.repository.FeedRepository;
import com.clearsoft.welivre.domain.repository.FollowRepository;
import com.clearsoft.welivre.domain.repository.PlanRepository;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.use_cases.AuthUseCase;
import com.clearsoft.welivre.domain.use_cases.ContentUseCase;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.domain.use_cases.PlanUseCase;
import com.clearsoft.welivre.domain.use_cases.UploadUseCase;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.domain.use_cases.impl.AuthUseCaseImpl;
import com.clearsoft.welivre.domain.use_cases.impl.ContentUseCaseImpl;
import com.clearsoft.welivre.domain.use_cases.impl.FeedUseCaseImpl;
import com.clearsoft.welivre.domain.use_cases.impl.PlanUseCaseImpl;
import com.clearsoft.welivre.domain.use_cases.impl.UploadUseCaseImpl;
import com.clearsoft.welivre.domain.use_cases.impl.UserUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


/**
 * Created by vladimir on 02.06.16.
 */
@Module(includes = {
        RepositoriesModule.class,
        ThreadExecutorsModule.class,
        ApiModule.class
})
public class UseCaseModule {

    @Provides
    @Singleton
    AuthUseCase provideAuthUseCase(
            DaoSession daoSession,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            PreferenceRepository preferenceRepository,
            Retrofit retrofit,
            SaveEntityRepository saveEntityRepository,
            AuthRetrofit authRetrofit,
            ErrorMapper errorMapper
    ) {
        return new AuthUseCaseImpl(
                daoSession,
                threadExecutor,
                postExecutionThread,
                retrofit,
                authRetrofit,
                preferenceRepository,
                saveEntityRepository,
                errorMapper
        );
    }

    @Provides
    @Singleton
    FeedUseCase providePostUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            PreferenceRepository preferenceRepository,
            Retrofit retrofit,
            SaveEntityRepository saveEntityRepository,
            AuthRetrofit authRetrofit,
            FeedRepository feedRepository,
            ErrorMapper errorMapper,
            FeedMapper feedMapper
    ) {
        return new FeedUseCaseImpl(
                threadExecutor,
                postExecutionThread,
                retrofit,
                authRetrofit,
                preferenceRepository,
                saveEntityRepository,
                feedRepository,
                errorMapper,
                feedMapper
        );
    }

    @Provides
    @Singleton
    PlanUseCase provideMoreUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            PlanRepository planRepository,
            PreferenceRepository preferenceRepository,
            Retrofit retrofit,
            SaveEntityRepository saveEntityRepository,
            AuthRetrofit authRetrofit,
            ErrorMapper errorMapper,
            PlanMapper planMapper
    ) {
        return new PlanUseCaseImpl(
                threadExecutor,
                postExecutionThread,
                retrofit,
                authRetrofit,
                planRepository,
                preferenceRepository,
                saveEntityRepository,
                errorMapper,
                planMapper
        );
    }

    @Provides
    @Singleton
    UserUseCase provideUserUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            PreferenceRepository preferenceRepository,
            Retrofit retrofit,
            FollowRepository followRepository,
            SaveEntityRepository saveEntityRepository,
            AuthRetrofit authRetrofit,
            ErrorMapper errorMapper,
            FollowMapper followMapper
    ) {
        return new UserUseCaseImpl(
                threadExecutor,
                postExecutionThread,
                retrofit,
                authRetrofit,
                preferenceRepository,
                followRepository,
                saveEntityRepository,
                errorMapper,
                followMapper
        );
    }

    @Provides
    @Singleton
    UploadUseCase provideUploadUseCase(
            ImageApi imageApi,
            Bus bus,
            DaoSession daoSession,
            UploadRetrofit uploadRetrofit,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            SaveEntityRepository saveEntityRepository,
            PreferenceRepository preferenceRepository
    ) {
        return new UploadUseCaseImpl(
                imageApi,
                bus,
                daoSession,
                uploadRetrofit,
                threadExecutor,
                postExecutionThread,
                saveEntityRepository,
                preferenceRepository
        );
    }

    @Provides
    @Singleton
    ContentUseCase provideContentUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            PreferenceRepository preferenceRepository,
            Retrofit retrofit,
            SaveEntityRepository saveEntityRepository,
            AuthRetrofit authRetrofit,
            ErrorMapper errorMapper
    ) {
        return new ContentUseCaseImpl(
                threadExecutor,
                postExecutionThread,
                retrofit,
                authRetrofit,
                preferenceRepository,
                saveEntityRepository,
                errorMapper
        );
    }
}
