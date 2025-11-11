package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcvg implements zzcye {
    private final Clock zzbmz;
    private final AtomicReference zzgix;
    private final zzcye zzgiy;
    private final long zzgiz;

    public zzcvg(zzcye zzcye0, long v, Clock clock0) {
        this.zzgix = new AtomicReference();
        this.zzbmz = clock0;
        this.zzgiy = zzcye0;
        this.zzgiz = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcvf zzcvf0 = (zzcvf)this.zzgix.get();
        if(zzcvf0 == null || zzcvf0.hasExpired()) {
            zzcvf0 = new zzcvf(this.zzgiy.zzapb(), this.zzgiz, this.zzbmz);
            this.zzgix.set(zzcvf0);
        }
        return zzcvf0.zzgiv;
    }
}

