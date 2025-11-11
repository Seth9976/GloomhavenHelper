package com.android.billingclient.api;

import android.os.Bundle;
import java.util.concurrent.Callable;

public final class zzo implements Callable {
    public final BillingClientImpl zza;
    public final String zzb;
    public final Bundle zzc;

    public zzo(BillingClientImpl billingClientImpl0, String s, Bundle bundle0) {
        this.zza = billingClientImpl0;
        this.zzb = s;
        this.zzc = bundle0;
    }

    @Override
    public final Object call() {
        return this.zza.zzg(this.zzb, this.zzc);
    }
}

