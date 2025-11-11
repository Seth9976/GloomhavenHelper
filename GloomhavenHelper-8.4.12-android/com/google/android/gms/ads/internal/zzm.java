package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzdq;
import com.google.android.gms.internal.ads.zzdr;
import java.util.concurrent.Callable;

final class zzm implements Callable {
    private final zzl zzblt;

    zzm(zzl zzl0) {
        this.zzblt = zzl0;
        super();
    }

    @Override
    public final Object call() throws Exception {
        return new zzdq(zzdr.zza(this.zzblt.zzblu.zzbmj, this.zzblt.zzur, false));
    }
}

