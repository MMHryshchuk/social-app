package com.clearsoft.welivre.core.di.modules;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.domain.entities.DaoMaster;
import com.clearsoft.welivre.domain.entities.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vladimir on 29.05.16.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    public DaoSession provideSession(App app){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, "clearsoft-welivre",null);
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        return master.newSession();
    }
}
