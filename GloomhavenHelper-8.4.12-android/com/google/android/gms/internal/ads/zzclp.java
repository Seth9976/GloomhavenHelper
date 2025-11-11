package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

public final class zzclp implements zzdil {
    private final zzclk zzgal;

    zzclp(zzclk zzclk0) {
        this.zzgal = zzclk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s, Throwable throwable0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue() && zzdig.zzgvh == zzdig0 && this.zzgal.zzanx() != 0L) {
            long v = zzq.zzlc().elapsedRealtime();
            long v1 = this.zzgal.zzanx();
            this.zzgal.zzer(v - v1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzb(zzdig zzdig0, String s) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue() && zzdig.zzgvh == zzdig0) {
            long v = zzq.zzlc().elapsedRealtime();
            this.zzgal.zzfe(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzc(zzdig zzdig0, String s) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue() && zzdig.zzgvh == zzdig0 && this.zzgal.zzanx() != 0L) {
            long v = zzq.zzlc().elapsedRealtime();
            long v1 = this.zzgal.zzanx();
            this.zzgal.zzer(v - v1);
        }
    }
}

