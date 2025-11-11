package com.android.billingclient.api;

import java.util.List;
import java.util.concurrent.Callable;

public final class zzp implements Callable {
    public final BillingClientImpl zza;
    public final String zzb;
    public final List zzc;
    public final SkuDetailsResponseListener zzd;

    public zzp(BillingClientImpl billingClientImpl0, String s, List list0, String s1, SkuDetailsResponseListener skuDetailsResponseListener0) {
        this.zza = billingClientImpl0;
        this.zzb = s;
        this.zzc = list0;
        this.zzd = skuDetailsResponseListener0;
    }

    @Override
    public final Object call() {
        this.zza.zzq(this.zzb, this.zzc, null, this.zzd);
        return null;
    }
}

