package com.android.billingclient.api;

public final class zzt implements Runnable {
    public final PurchaseHistoryResponseListener zza;

    public zzt(PurchaseHistoryResponseListener purchaseHistoryResponseListener0) {
        this.zza = purchaseHistoryResponseListener0;
    }

    @Override
    public final void run() {
        this.zza.onPurchaseHistoryResponse(zzak.zzr, null);
    }
}

