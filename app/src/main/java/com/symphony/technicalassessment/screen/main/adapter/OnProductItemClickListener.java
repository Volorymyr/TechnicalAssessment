package com.symphony.technicalassessment.screen.main.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.symphony.technicalassessment.data.product.model.Product;

@FunctionalInterface
public interface OnProductItemClickListener {

    /**
     * @param product the product which belongs to specific item
     * @param sharedViews used for shared element transition
     */
    void onProductItemClick(@NonNull final Product product, @NonNull final View... sharedViews);
}
