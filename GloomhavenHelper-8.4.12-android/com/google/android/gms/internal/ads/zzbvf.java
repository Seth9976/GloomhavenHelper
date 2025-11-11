package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbvf extends zzbtk implements zzagj {
    public zzbvf(Set set0) {
        super(set0);
    }

    @Override  // com.google.android.gms.internal.ads.zzagj
    public final void zza(zzasq zzasq0) {
        this.zza(new zzbvh(zzasq0));
    }

    @Override  // com.google.android.gms.internal.ads.zzagj
    public final void zzrx() {
        synchronized(this) {
            this.zza(zzbve.zzfkj);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzagj
    public final void zzry() {
        this.zza(zzbvg.zzfkj);
    }
}

