package com.symphony.technicalassessment.data.product.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.symphony.technicalassessment.data.ImmutableStyle;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import io.reactivex.annotations.NonNull;

@ImmutableStyle
@Value.Immutable
@Gson.TypeAdapters
public interface SalePrice extends Parcelable {

    static ImmutableSalePrice.AmountBuildStage newBuilder() {
        return ImmutableSalePrice.newBuilder();
    }

    Creator<SalePrice> CREATOR = new Creator<SalePrice>() {
        @Override
        public SalePrice createFromParcel(Parcel source) {
            return newBuilder()
                    .withAmount(source.readFloat())
                    .withCurrency(source.readString())
                    .build();
        }

        @Override
        public SalePrice[] newArray(int size) {
            return new SalePrice[0];
        }
    };

    @Override
    default void writeToParcel(@NonNull final Parcel dest, final int flags) {
        dest.writeFloat(getAmount());
        dest.writeString(getCurrency());
    }

    @Override
    default int describeContents() {
        return 0;
    }

    /**
     * Get amount of {@link Product}
     *
     * @return the amount
     */
    @SerializedName("amount")
    float getAmount();

    /**
     * Get currency with using for sale {@link Product}
     *
     * @return the currency (EUR, UAH, etc)
     */
    @NonNull
    @SerializedName("currency")
    String getCurrency();

    /**
     * Get amount plus currency
     *
     * @return the price of {@link Product}
     */
    @NonNull
    @Value.Default
    default String getPrice() {
        return String.valueOf(getAmount()) + " " + getCurrency();
    }
}
