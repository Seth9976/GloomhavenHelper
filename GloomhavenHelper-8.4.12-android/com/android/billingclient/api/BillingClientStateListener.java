package com.android.billingclient.api;

import androidx.annotation.NonNull;

public interface BillingClientStateListener {
    void onBillingServiceDisconnected();

    void onBillingSetupFinished(@NonNull BillingResult arg1);
}

