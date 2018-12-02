package com.symphony.technicalassessment.screen.main.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.symphony.technicalassessment.R;
import com.symphony.technicalassessment.data.categories.model.Category;
import com.symphony.technicalassessment.data.product.model.Product;
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends SimpleSectionedAdapter<CategoriesAdapter.ProductViewHolder> {

    private final List<Category> mCategories;
    private final OnProductItemClickListener mOnProductItemClickListener;

    public CategoriesAdapter(@NonNull final List<Category> categories,
                             @NonNull final OnProductItemClickListener onProductItemClickListener) {
        mCategories = categories;
        mOnProductItemClickListener = onProductItemClickListener;
    }

    @Override
    protected String getSectionHeaderTitle(int section) {
        return mCategories.get(section).getName();
    }

    @Override
    protected int getSectionCount() {
        return mCategories.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return mCategories.get(section).getProducts().size();
    }

    @Override
    protected @LayoutRes int getLayoutResource() {
        return R.layout.item_product_header;
    }

    @Override
    protected @IdRes int getTitleTextID() {
        return R.id.product_title;
    }

    @Override
    protected ProductViewHolder onCreateItemViewHolder(@NonNull final ViewGroup parent,
                                                       final int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false));
    }

    @Override
    protected void onBindItemViewHolder(@NonNull final ProductViewHolder holder, final int section,
                                        final int position) {
        final Category category = mCategories.get(section);
        final Product product = category.getProducts().get(position);
        holder.bind(product, mOnProductItemClickListener);
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_image)
        ImageView mProductImageView;
        @BindView(R.id.product_name)
        TextView mNameTextView;
        @BindView(R.id.product_root)
        View mRoot;

        private final RequestOptions requestOptions = new RequestOptions();

        @SuppressLint("CheckResult")
        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            requestOptions.error(R.drawable.ic_error_placeholder);
        }

        void bind(@NonNull final Product product,
                  @NonNull final OnProductItemClickListener onProductItemClickListener) {
            Glide.with(mProductImageView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(product.getImageFullUrl())
                    .into(mProductImageView);
            mNameTextView.setText(product.getName());
            mRoot.setOnClickListener(v -> onProductItemClickListener.onProductItemClick(product, mNameTextView, mProductImageView));
        }
    }
}
