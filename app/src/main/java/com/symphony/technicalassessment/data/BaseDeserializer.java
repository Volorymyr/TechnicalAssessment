package com.symphony.technicalassessment.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseDeserializer<T> implements JsonDeserializer<T> {

    protected static String parseAsStringOrDefault(@Nullable final JsonElement jsonElement,
                                                   @NonNull final String defaultValue) {
        if (isJsonNull(jsonElement)) return defaultValue;
        return jsonElement.getAsString();
    }

    protected static int parseAsIntOrDefault(@Nullable final JsonElement jsonElement,
                                             final int defaultValue) {
        if (isJsonNull(jsonElement)) return defaultValue;
        return jsonElement.getAsInt();
    }

    protected static long parseAsLongOrDefault(@Nullable JsonElement jsonElement,
                                               int defaultValue) {
        if (isJsonNull(jsonElement)) return defaultValue;
        return jsonElement.getAsLong();
    }

    protected static float parseAsFloatWithDefault(@Nullable final JsonElement jsonElement,
                                                   final float defaultValue) {
        if (isJsonNull(jsonElement)) return defaultValue;
        return jsonElement.getAsFloat();
    }

    protected static boolean parseAsBooleanOrDefault(@Nullable final JsonElement jsonElement,
                                                     final boolean defaultValue) {
        if (isJsonNull(jsonElement)) return defaultValue;
        return jsonElement.getAsBoolean();
    }

    @Nullable
    protected static <T> T parseAsObjectOrNull(@NonNull final JsonDeserializationContext context,
                                               @Nullable final JsonElement jsonElement,
                                               @NonNull final Class<T> clazz) {
        if (isJsonNull(jsonElement)) return null;
        return context.deserialize(jsonElement, clazz);
    }

    protected static <T> T parseAsObjectOrDefault(@NonNull final JsonDeserializationContext context,
                                                  @Nullable final JsonElement jsonElement,
                                                  @NonNull final Class<T> clazz,
                                                  @NonNull final T defaultValue) {
        if (isJsonNull(jsonElement)) return defaultValue;
        return context.deserialize(jsonElement, clazz);
    }

    protected static <T> List<T> parseAsListOrDefault(@NonNull final JsonDeserializationContext context,
                                                      @Nullable final JsonElement jsonElement,
                                                      @NonNull final Class<T> clazz) {
        if (isJsonNull(jsonElement)) return Collections.emptyList();
        final List<T> result = new ArrayList<>();
        final JsonArray array = jsonElement.getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonElement resultElement = array.get(i);
            if (isJsonNull(resultElement)) continue;
            T object = context.deserialize(resultElement, clazz);
            result.add(object);
        }
        return result;
    }

    protected static boolean isJsonNull(@Nullable final JsonElement jsonElement) {
        return jsonElement == null || jsonElement.isJsonNull();
    }
}
