package com.android.billingclient.api;

import java.util.concurrent.Callable;

public final class zzn implements Callable {
    public final BillingClientImpl zza;
    public final String zzb;

    public zzn(BillingClientImpl billingClientImpl0, String s) {
        this.zza = billingClientImpl0;
        this.zzb = s;
    }

    @Override
    public final Object call() {
        return this.zza.zzn(this.zzb);
    }
}

