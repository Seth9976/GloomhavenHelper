package com.android.billingclient.api;

import android.os.Bundle;
import java.util.concurrent.Callable;

public final class zzx implements Callable {
    public final BillingClientImpl zza;
    public final int zzb;
    public final SkuDetails zzc;
    public final String zzd;
    public final BillingFlowParams zze;
    public final Bundle zzf;

    public zzx(BillingClientImpl billingClientImpl0, int v, SkuDetails skuDetails0, String s, BillingFlowParams billingFlowParams0, Bundle bundle0) {
        this.zza = billingClientImpl0;
        this.zzb = v;
        this.zzc = skuDetails0;
        this.zzd = s;
        this.zze = billingFlowParams0;
        this.zzf = bundle0;
    }

    @Override
    public final Object call() {
        return this.zza.zze(this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }
}

