package com.symphony.technicalassessment.data;

import org.immutables.value.Value;

@Value.Style(
        init = "with*",
        typeAbstract = "Abs*",
        get = {"get*", "is*", "has*"},
        visibility = Value.Style.ImplementationVisibility.PACKAGE,
        builderVisibility = Value.Style.BuilderVisibility.PACKAGE,
        overshadowImplementation = true,
        stagedBuilder = true,
        strictBuilder = true,
        builder = "newBuilder"
)
public @interface ImmutableStyle {
}
