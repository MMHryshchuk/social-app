package com.clearsoft.welivre.core.di.modules;


import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ThreadExecutorsModule {

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecuter(){
        return new ThreadExecutor.DefaultWorker();
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread (){
        return new PostExecutionThread.DefaultWorker();
    }



}
