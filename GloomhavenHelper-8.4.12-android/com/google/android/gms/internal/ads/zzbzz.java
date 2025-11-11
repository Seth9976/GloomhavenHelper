package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbzz implements zzeej {
    private final zzeew zzenm;
    private final zzeew zzepx;
    private final zzeew zzepz;
    private final zzeew zzerc;
    private final zzeew zzfev;
    private final zzeew zzfgm;
    private final zzeew zzfgu;
    private final zzeew zzfnw;

    private zzbzz(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7) {
        this.zzerc = zzeew0;
        this.zzfgu = zzeew1;
        this.zzenm = zzeew2;
        this.zzfnw = zzeew3;
        this.zzepx = zzeew4;
        this.zzfgm = zzeew5;
        this.zzfev = zzeew6;
        this.zzepz = zzeew7;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbzv(((zzawh)this.zzerc.get()), ((zzdeu)this.zzfgu.get()), ((zzbzd)this.zzenm.get()), ((zzbyz)this.zzfnw.get()), ((zzcad)this.zzepx.get()), ((Executor)this.zzfgm.get()), ((Executor)this.zzfev.get()), ((zzbyu)this.zzepz.get()));
    }

    public static zzbzz zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7) {
        return new zzbzz(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5, zzeew6, zzeew7);
    }
}

