package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzcki implements Callable {
    private final zzdof zzfjy;
    private final zzdof zzfsq;

    zzcki(zzdof zzdof0, zzdof zzdof1) {
        this.zzfsq = zzdof0;
        this.zzfjy = zzdof1;
    }

    @Override
    public final Object call() {
        return new zzckv(((zzcky)this.zzfsq.get()), ((zzcks)this.zzfjy.get()).zzfzi, ((zzcks)this.zzfjy.get()).zzfzh);
    }
}

