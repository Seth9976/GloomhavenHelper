package com.android.billingclient.api;

import java.util.concurrent.Callable;

public final class zzm implements Callable {
    public final BillingClientImpl zza;
    public final SkuDetails zzb;
    public final String zzc;

    public zzm(BillingClientImpl billingClientImpl0, SkuDetails skuDetails0, String s) {
        this.zza = billingClientImpl0;
        this.zzb = skuDetails0;
        this.zzc = s;
    }

    @Override
    public final Object call() {
        return this.zza.zzf(this.zzb, this.zzc);
    }
}

