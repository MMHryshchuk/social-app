package com.clearsoft.welivre.core.di.components;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by vladimir on 27.07.16.
 */
@Subcomponent(modules = {ServicesComponent.ServicesModule.class})
@Singleton
public interface ServicesComponent {


    void inject();

    @Module
    class ServicesModule {
    }
}
