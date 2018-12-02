package com.symphony.technicalassessment.screen.main;

import com.symphony.technicalassessment.screen.ActivityScope;

import dagger.Binds;
import dagger.Module;
import io.reactivex.annotations.NonNull;

@Module
public abstract class MainActivityModule {

    @Binds
    @ActivityScope
    public abstract  MainContract.View bindActivity(@NonNull final MainActivity mainActivity);

    @Binds
    @ActivityScope
    public abstract MainContract.Presenter bindMainPresenter(@NonNull final MainPresenter mainPresenter);
}
