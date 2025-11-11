package com.android.billingclient.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public interface SkuDetailsResponseListener {
    void onSkuDetailsResponse(@NonNull BillingResult arg1, @Nullable List arg2);
}

