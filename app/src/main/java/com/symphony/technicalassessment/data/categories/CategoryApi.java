package com.symphony.technicalassessment.data.categories;

import com.symphony.technicalassessment.data.categories.model.Category;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CategoryApi {

    @GET("./")
    Single<List<Category>> fetchCategoriesWithProducts();

}
