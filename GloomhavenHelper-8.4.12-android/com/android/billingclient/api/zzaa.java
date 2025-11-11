package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzp;
import java.util.concurrent.Callable;

final class zzaa implements Callable {
    final String zza;
    final PurchasesResponseListener zzb;
    final BillingClientImpl zzc;

    zzaa(BillingClientImpl billingClientImpl0, String s, PurchasesResponseListener purchasesResponseListener0) {
        this.zzc = billingClientImpl0;
        this.zza = s;
        this.zzb = purchasesResponseListener0;
        super();
    }

    @Override
    public final Object call() throws Exception {
        PurchasesResult purchase$PurchasesResult0 = BillingClientImpl.zzk(this.zzc, this.zza);
        if(purchase$PurchasesResult0.getPurchasesList() != null) {
            this.zzb.onQueryPurchasesResponse(purchase$PurchasesResult0.getBillingResult(), purchase$PurchasesResult0.getPurchasesList());
            return null;
        }
        this.zzb.onQueryPurchasesResponse(purchase$PurchasesResult0.getBillingResult(), zzp.zzg());
        return null;
    }
}

