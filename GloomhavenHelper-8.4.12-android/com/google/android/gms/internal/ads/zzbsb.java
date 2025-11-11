package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzbsb extends zzbtk implements zzbqu, zzbrw {
    private final zzdei zzfdq;
    private AtomicBoolean zzfko;

    public zzbsb(Set set0, zzdei zzdei0) {
        super(set0);
        this.zzfko = new AtomicBoolean();
        this.zzfdq = zzdei0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        if(this.zzfdq.zzfmh == 2 || this.zzfdq.zzfmh == 4 || this.zzfdq.zzfmh == 5) {
            this.zzaib();
        }
    }

    final void zza(zzbsg zzbsg0) throws Exception {
        zzbsg0.zzb(this.zzfdq.zzgqe);
    }

    @Override  // com.google.android.gms.internal.ads.zzbrw
    public final void zzaia() {
        if(this.zzfdq.zzfmh == 1) {
            this.zzaib();
        }
    }

    private final void zzaib() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcsi)).booleanValue() && this.zzfko.compareAndSet(false, true) && this.zzfdq.zzgqe != null && this.zzfdq.zzgqe.type == 3) {
            this.zza(new zzbse(this));
        }
    }
}

