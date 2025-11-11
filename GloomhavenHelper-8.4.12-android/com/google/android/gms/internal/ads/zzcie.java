package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final class zzcie implements zzdng {
    static final zzdng zzblf;

    static {
        zzcie.zzblf = new zzcie();
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        return zzdnt.immediateFailedFuture(((ExecutionException)object0).getCause());
    }
}

