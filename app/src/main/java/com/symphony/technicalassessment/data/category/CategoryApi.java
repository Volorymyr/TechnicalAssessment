package com.symphony.technicalassessment.data.category;

import com.symphony.technicalassessment.data.category.model.Category;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CategoryApi {

    @GET("./")
    Single<List<Category>> fetchCategoriesWithProducts();

}
