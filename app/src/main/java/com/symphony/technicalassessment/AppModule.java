package com.symphony.technicalassessment;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
abstract class AppModule {

    @Provides
    @Singleton
    static Context provideContext(final App app) {
        return app.getApplicationContext();
    }
}
