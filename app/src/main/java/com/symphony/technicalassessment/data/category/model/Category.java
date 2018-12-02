package com.symphony.technicalassessment.data.category.model;

import com.google.gson.annotations.SerializedName;
import com.symphony.technicalassessment.data.ImmutableStyle;
import com.symphony.technicalassessment.data.product.model.Product;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

@ImmutableStyle
@Value.Immutable
@Gson.TypeAdapters
public interface Category {

    /**
     * Get the id of category
     *
     * @return the id of current category
     */
    @SerializedName("id")
    int getId();

    /**
     * Get the name of category
     *
     * @return the name of current category
     */
    @NonNull
    @SerializedName("name")
    String getName();

    /**
     * Get the description of category
     * can be null
     *
     * @return the description of current category
     */
    @Nullable
    @SerializedName("description")
    String getDescription();

    /**
     * Get list of products which belongs to this category
     *
     * @return the list of products
     */
    @NonNull
    @SerializedName("products")
    List<Product> getProducts();
}
