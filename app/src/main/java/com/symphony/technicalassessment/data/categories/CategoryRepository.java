package com.symphony.technicalassessment.data.categories;

import com.symphony.technicalassessment.data.categories.model.Category;
import com.symphony.technicalassessment.network.ConnectionHelper;
import com.symphony.technicalassessment.network.NetworkConnectionException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class CategoryRepository implements CategoryDataSource {

    private final ConnectionHelper mConnectionHelper;
    private final CategoryApi mCategoryApi;

    @Inject
    public CategoryRepository(@NonNull final ConnectionHelper connectionHelper,
                              @NonNull final CategoryApi categoryApi) {
        mConnectionHelper = connectionHelper;
        mCategoryApi = categoryApi;
    }

    @Override
    public Single<List<Category>> fetchCategoriesWithProducts() {
        return mCategoryApi.fetchCategoriesWithProducts()
                .doOnSubscribe(subscription -> {
                    if (!mConnectionHelper.isConnected()) throw new NetworkConnectionException();
                });
    }

}
