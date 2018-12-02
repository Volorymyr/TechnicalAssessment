package com.symphony.technicalassessment;

import com.symphony.technicalassessment.screen.ActivityScope;
import com.symphony.technicalassessment.screen.detail.DetailsActivity;
import com.symphony.technicalassessment.screen.main.MainActivity;
import com.symphony.technicalassessment.screen.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector
    abstract DetailsActivity detailsActivityInjector();
}
