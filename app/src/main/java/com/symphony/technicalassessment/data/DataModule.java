package com.symphony.technicalassessment.data;

import com.symphony.technicalassessment.data.categories.CategoryDataSource;
import com.symphony.technicalassessment.data.categories.CategoryRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @Binds
    @Singleton
    public abstract CategoryDataSource provideProductDataSource(CategoryRepository productRepository);

}
