package com.symphony.technicalassessment.screen.main;

import com.symphony.technicalassessment.data.categories.model.Category;
import com.symphony.technicalassessment.screen.BasePresenter;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * The contract between MainView and MainPresenter
 */
public interface MainContract {

    interface View {
        void showCategories(@NonNull final List<Category> categories);
        void showCategoriesError(@NonNull final Throwable error);
        void showProgress();
        void hideProgress();
    }

    interface Presenter extends BasePresenter {
        void fetchCategories();
    }
}
