package com.android.billingclient.api;

import androidx.annotation.NonNull;

public interface ConsumeResponseListener {
    void onConsumeResponse(@NonNull BillingResult arg1, @NonNull String arg2);
}

