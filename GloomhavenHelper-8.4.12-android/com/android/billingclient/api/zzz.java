package com.android.billingclient.api;

import java.util.concurrent.Callable;

final class zzz implements Callable {
    final String zza;
    final BillingClientImpl zzb;

    zzz(BillingClientImpl billingClientImpl0, String s) {
        this.zzb = billingClientImpl0;
        this.zza = s;
        super();
    }

    @Override
    public final Object call() throws Exception {
        return BillingClientImpl.zzk(this.zzb, this.zza);
    }
}

