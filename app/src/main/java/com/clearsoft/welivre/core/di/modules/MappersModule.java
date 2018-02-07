package com.clearsoft.welivre.core.di.modules;

import com.clearsoft.welivre.domain.mappers.FeedMapper;
import com.clearsoft.welivre.domain.mappers.FollowMapper;
import com.clearsoft.welivre.domain.mappers.PlanMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vladimir on 09.06.16.
 */
@Module
public class MappersModule {

    @Provides
    @Singleton
    FeedMapper provideFeedMapper(){
        return new FeedMapper();
    }

    @Provides
    @Singleton
    PlanMapper providePlanMapper(){
        return new PlanMapper();
    }

    @Provides
    @Singleton
    FollowMapper provideFollowMapper(){
        return new FollowMapper();
    }
}
