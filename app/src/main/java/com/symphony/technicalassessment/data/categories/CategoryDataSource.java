package com.symphony.technicalassessment.data.categories;

import com.symphony.technicalassessment.data.categories.model.Category;

import java.util.List;

import io.reactivex.Single;

/**
 * The data source which provide categories and
 * products which belongs to this categories
 */
public interface CategoryDataSource {
    Single<List<Category>> fetchCategoriesWithProducts();
}
