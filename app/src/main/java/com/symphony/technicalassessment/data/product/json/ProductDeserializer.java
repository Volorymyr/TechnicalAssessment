package com.symphony.technicalassessment.data.product.json;

import android.support.annotation.NonNull;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.symphony.technicalassessment.data.BaseDeserializer;
import com.symphony.technicalassessment.data.product.model.Product;
import com.symphony.technicalassessment.data.product.model.SalePrice;

import java.lang.reflect.Type;

public final class ProductDeserializer extends BaseDeserializer<Product> {

    public static final Type TYPE = new TypeToken<Product>() {}.getType();

    private final String mBaseUrl;

    public ProductDeserializer(@NonNull final String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    public Product deserialize(@NonNull final JsonElement jsonElement,
                               @NonNull final Type typeOfT,
                               @NonNull final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject json = jsonElement.getAsJsonObject();
        final String imageUrl = parseAsStringOrDefault(json.get("url"), "");
        final String imageFullUrl = imageUrl.isEmpty() ? "" : mBaseUrl + imageUrl.replace("/", "");
        return Product.newBuilder()
                .withId(parseAsIntOrDefault(json.get("id"),0))
                .withCategoryId(parseAsIntOrDefault(json.get("categoryId"), 0))
                .withName(parseAsStringOrDefault(json.get("name"), ""))
                .withImageUrl(imageUrl)
                .withImageFullUrl(imageFullUrl)
                .withSalePrice(parseAsObjectOrNull(context, json.get("salePrice"), SalePrice.class))
                .build();
    }
}
