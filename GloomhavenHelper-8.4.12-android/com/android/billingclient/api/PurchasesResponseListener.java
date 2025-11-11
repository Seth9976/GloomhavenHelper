package com.android.billingclient.api;

import androidx.annotation.NonNull;
import java.util.List;

@zze
public interface PurchasesResponseListener {
    @zze
    void onQueryPurchasesResponse(@NonNull BillingResult arg1, @NonNull List arg2);
}

