package com.symphony.technicalassessment.rx;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

public interface BaseRxTransformerProvider {

    <T> SingleTransformer<T, T> single();
    <T> FlowableTransformer<T, T> flowable();
    <T> ObservableTransformer<T, T> observable();

}
