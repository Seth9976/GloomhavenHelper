package com.android.billingclient.api;

import java.util.concurrent.Callable;

final class zzab implements Callable {
    final String zza;
    final PurchaseHistoryResponseListener zzb;
    final BillingClientImpl zzc;

    zzab(BillingClientImpl billingClientImpl0, String s, PurchaseHistoryResponseListener purchaseHistoryResponseListener0) {
        this.zzc = billingClientImpl0;
        this.zza = s;
        this.zzb = purchaseHistoryResponseListener0;
        super();
    }

    @Override
    public final Object call() throws Exception {
        zzag zzag0 = BillingClientImpl.zzi(this.zzc, this.zza);
        this.zzb.onPurchaseHistoryResponse(zzag0.zza(), zzag0.zzb());
        return null;
    }
}

