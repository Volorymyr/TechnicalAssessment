package com.symphony.technicalassessment.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class NetworkConnectionHelper implements ConnectionHelper {

    private final ConnectivityManager mManager;

    @Inject
    NetworkConnectionHelper(@NonNull final Context context) {
        mManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean isConnected() {
        NetworkInfo networkInfo = mManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
