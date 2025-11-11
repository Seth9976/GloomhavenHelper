package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

final class zzcjb implements zzdng {
    static final zzdng zzblf;

    static {
        zzcjb.zzblf = new zzcjb();
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        TimeoutException timeoutException0 = (TimeoutException)object0;
        return zzdnt.immediateFailedFuture(new zzcke("Timed out waiting for ad response.", 2));
    }
}

