package com.google.android.gms.internal.ads;

final class zzcaw implements zzdku {
    private final double zzfrr;
    private final boolean zzfrs;
    private final zzcax zzfrt;

    zzcaw(zzcax zzcax0, double f, boolean z) {
        this.zzfrt = zzcax0;
        this.zzfrr = f;
        this.zzfrs = z;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    public final Object apply(Object object0) {
        return this.zzfrt.zza(((zzo)object0).data, this.zzfrr, this.zzfrs);
    }
}

