package com.clearsoft.welivre.core.di.modules;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.mappers.FeedMapper;
import com.clearsoft.welivre.domain.mappers.FollowMapper;
import com.clearsoft.welivre.domain.mappers.PlanMapper;
import com.clearsoft.welivre.domain.repository.FeedRepository;
import com.clearsoft.welivre.domain.repository.FollowRepository;
import com.clearsoft.welivre.domain.repository.PlanRepository;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.repository.impl.FeedRepositoryImpl;
import com.clearsoft.welivre.domain.repository.impl.FollowRepositoryImpl;
import com.clearsoft.welivre.domain.repository.impl.PlanRepositoryImpl;
import com.clearsoft.welivre.domain.repository.impl.PreferenceRepositoryImpl;
import com.clearsoft.welivre.domain.repository.impl.SaveEntityRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        DataModule.class,
        MappersModule.class
})
public class RepositoriesModule {

    @Provides
    @Singleton
    PreferenceRepository providePreferenceRepository(App app){
        return new PreferenceRepositoryImpl(app);
    }
    @Provides
    @Singleton
    SaveEntityRepository provideSaveEntityRepo(
            DaoSession daoSession,
            FeedMapper feedMapper,
            PlanMapper planMapper,
            FollowMapper followMapper
    ){
        return new SaveEntityRepositoryImpl(
                daoSession,
                feedMapper,
                planMapper,
                followMapper
        );
    }

    @Provides
    @Singleton
    FeedRepository provideFeedRepo(DaoSession daoSession){
        return new FeedRepositoryImpl(daoSession);
    }

    @Provides
    @Singleton
    PlanRepository providePlanRepo(DaoSession daoSession){
        return new PlanRepositoryImpl(daoSession);
    }

    @Provides
    @Singleton
    FollowRepository provideFollowRepository(DaoSession daoSession){
        return new FollowRepositoryImpl(daoSession);
    }



}
