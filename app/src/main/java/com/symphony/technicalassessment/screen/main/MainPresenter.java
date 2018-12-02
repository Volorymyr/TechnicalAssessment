package com.symphony.technicalassessment.screen.main;

import com.symphony.technicalassessment.data.category.CategoryDataSource;
import com.symphony.technicalassessment.data.category.model.Category;
import com.symphony.technicalassessment.rx.BaseRxTransformerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;
    private final CategoryDataSource mCategoryDataSource;
    private final BaseRxTransformerProvider mRxProvider;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    MainPresenter(@NonNull final MainContract.View mainView,
                  @NonNull final CategoryDataSource categoryDataSource,
                  @NonNull final BaseRxTransformerProvider rxTransformerProvider) {
        mMainView = mainView;
        mCategoryDataSource = categoryDataSource;
        mRxProvider = rxTransformerProvider;
    }

    @Override
    public void onStart() {
        //Ignore
    }

    @Override
    public void fetchCategories() {
        Disposable disposable = mCategoryDataSource.fetchCategories()
                .compose(mRxProvider.single())
                .doOnSubscribe(disposable1 -> mMainView.showProgress())
                .subscribeWith(new DisposableSingleObserver<List<Category>>() {
                    @Override
                    public void onSuccess(@NonNull final List<Category> categories) {
                        mMainView.showCategories(categories);
                        mMainView.hideProgress();
                    }

                    @Override
                    public void onError(@NonNull final Throwable error) {
                        mMainView.showCategoriesError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }
}
