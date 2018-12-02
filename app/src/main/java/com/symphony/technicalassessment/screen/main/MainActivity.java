package com.symphony.technicalassessment.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.symphony.technicalassessment.R;
import com.symphony.technicalassessment.data.category.model.Category;
import com.symphony.technicalassessment.network.NetworkConnectionException;
import com.symphony.technicalassessment.screen.BaseActivity;
import com.symphony.technicalassessment.screen.detail.DetailsActivity;
import com.symphony.technicalassessment.screen.main.adapter.CategoriesAdapter;
import com.symphony.technicalassessment.screen.main.adapter.OnProductItemClickListener;
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.main_categories)
    RecyclerView mCategoriesRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.main_error)
    TextView mErrorTextView;

    @Inject
    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        //fetch data from the back-end
        if (mCategoriesRecyclerView.getAdapter() == null) {
            mPresenter.fetchCategories();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void showCategories(@NonNull final List<Category> categoriesWithProducts) {
        if (mCategoriesRecyclerView.getAdapter() == null) {
            //Initialize recycler view with layout manager and data
            final CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesWithProducts, mOnProductItemClickListener);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            SectionedSpanSizeLookup lookup = new SectionedSpanSizeLookup(categoriesAdapter, layoutManager);
            layoutManager.setSpanSizeLookup(lookup);
            mCategoriesRecyclerView.setLayoutManager(layoutManager);
            mCategoriesRecyclerView.setAdapter(categoriesAdapter);
        }
    }

    private final OnProductItemClickListener mOnProductItemClickListener
            = (product, sharedViews) -> DetailsActivity.start(MainActivity.this, product, sharedViews);

    @Override
    public void showCategoriesError(@NonNull final Throwable error) {
        //If adapter already has some data, do nothing
        if (mCategoriesRecyclerView.getAdapter() == null) {
            //Hide data and show errors
            mCategoriesRecyclerView.setVisibility(GONE);
            mProgressBar.setVisibility(GONE);
            mErrorTextView.setVisibility(VISIBLE);
            //Show error text
            if (error instanceof NetworkConnectionException) {
                mErrorTextView.setText(getText(R.string.main_error_network_connection));
            } else {
                mErrorTextView.setText(getText(R.string.main_error));
            }
        }
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(VISIBLE);
        mCategoriesRecyclerView.setVisibility(GONE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(GONE);
        mCategoriesRecyclerView.setVisibility(VISIBLE);
    }
}
