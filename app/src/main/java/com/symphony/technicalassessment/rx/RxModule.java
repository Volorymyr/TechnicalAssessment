package com.symphony.technicalassessment.rx;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import io.reactivex.annotations.NonNull;

@Module
public abstract class RxModule {

    @Binds
    @Singleton
    public abstract BaseRxTransformerProvider provideRxTransformerProvider(@NonNull final RxTransformerProvider rxTransformerProvider);
}
