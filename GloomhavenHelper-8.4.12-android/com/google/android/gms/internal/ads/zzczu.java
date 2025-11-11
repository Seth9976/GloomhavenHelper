package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzczu implements Callable {
    private final zzdof zzfjy;
    private final zzdof zzfsq;

    zzczu(zzdof zzdof0, zzdof zzdof1) {
        this.zzfsq = zzdof0;
        this.zzfjy = zzdof1;
    }

    @Override
    public final Object call() {
        return new zzczs(((String)this.zzfsq.get()), ((String)this.zzfjy.get()));
    }
}

