package com.symphony.technicalassessment.data;

import com.symphony.technicalassessment.data.category.CategoryDataSource;
import com.symphony.technicalassessment.data.category.CategoryRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @Binds
    @Singleton
    public abstract CategoryDataSource provideProductDataSource(CategoryRepository productRepository);

}
