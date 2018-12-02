package com.symphony.technicalassessment.data;

import com.google.gson.GsonBuilder;
import com.symphony.technicalassessment.data.category.CategoryApi;
import com.symphony.technicalassessment.data.category.model.GsonAdaptersCategory;
import com.symphony.technicalassessment.data.product.json.ProductDeserializer;
import com.symphony.technicalassessment.data.product.model.GsonAdaptersSalePrice;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ApiModule {

    @Provides
    @Singleton
    @Named("BaseUrl")
    static String provideBaseUrl() {
        return "http://mobcategories.s3-website-eu-west-1.amazonaws.com/";
    }

    @Provides
    @Singleton
    static CategoryApi provideProductApi(@NonNull @Named("BaseUrl") final String baseUrl,
                                         @NonNull final OkHttpClient okHttpClient,
                                         @NonNull final GsonBuilder gsonBuilder) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CategoryApi.class);
    }

    @Provides
    @Singleton
    static GsonBuilder provideProductGsonBuilder(@NonNull final ProductDeserializer productDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new GsonAdaptersCategory())
                .registerTypeAdapterFactory(new GsonAdaptersSalePrice())
                .registerTypeAdapter(ProductDeserializer.TYPE, productDeserializer);
    }

    @Provides
    @Singleton
    static ProductDeserializer provideProductDeserializaer(@NonNull @Named("BaseUrl") final String baseUrl ) {
        return new ProductDeserializer(baseUrl);
    }
}
