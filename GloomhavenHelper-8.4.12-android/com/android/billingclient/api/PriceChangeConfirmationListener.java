package com.android.billingclient.api;

import androidx.annotation.NonNull;

public interface PriceChangeConfirmationListener {
    void onPriceChangeConfirmationResult(@NonNull BillingResult arg1);
}

