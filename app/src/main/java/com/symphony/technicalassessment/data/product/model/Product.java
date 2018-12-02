package com.symphony.technicalassessment.data.product.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.symphony.technicalassessment.data.ImmutableStyle;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

@ImmutableStyle
@Value.Immutable
@Gson.TypeAdapters
public interface Product extends WithProduct, Parcelable {

    static ImmutableProduct.IdBuildStage newBuilder() {
        return ImmutableProduct.newBuilder();
    }

    Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return newBuilder()
                    .withId(source.readInt())
                    .withCategoryId(source.readInt())
                    .withName(source.readString())
                    .withImageUrl(source.readString())
                    .withImageFullUrl(source.readString())
                    .withSalePrice(source.readParcelable(SalePrice.class.getClassLoader()))
                    .withDescription(source.readString())
                    .build();
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[0];
        }
    };

    @Override
    default int describeContents() {
        return 0;
    }

    @Override
    default void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeInt(getCategoryId());
        dest.writeString(getName());
        dest.writeString(getImageUrl());
        dest.writeString(getImageFullUrl());
        dest.writeParcelable(getSalePrice(), flags);
        dest.writeString(getDescription());
    }

    /**
     * Get the id of product
     *
     * @return the id of current product
     */
    @SerializedName("id")
    int getId();

    /**
     * Get the id of parent category
     *
     * @return the id of category
     */
    @SerializedName("categoryId")
    int getCategoryId();

    /**
     * Get name of product
     *
     * @return the name of product
     */
    @NonNull
    @SerializedName("name")
    String getName();

    /**
     * Get the end of url of image by which we can fetch this image
     *
     * @return
     */
    @NonNull
    @SerializedName("url")
    String getImageUrl();

    /**
     * Get the full product image url which consist from
     * base url and {@link #getImageUrl()}
     *
     * @return the product image full url
     */
    @NonNull
    String getImageFullUrl();

    /**
     * Get description of product
     *
     * @return the description of product
     */
    @Nullable
    @SerializedName("description")
    @Value.Default
    default String getDescription() {
        return "";
    }

    /**
     * Get the information about price and currency of product
     *
     * @return the price for sale
     */
    @NonNull
    @SerializedName("salePrice")
    SalePrice getSalePrice();

}
