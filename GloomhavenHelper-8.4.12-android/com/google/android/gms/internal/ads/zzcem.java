package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzc;
import java.util.concurrent.Executor;

public final class zzcem implements zzeej {
    private final zzeew zzenr;
    private final zzeew zzeoc;
    private final zzeew zzeof;
    private final zzeew zzepf;
    private final zzeew zzeps;
    private final zzeew zzewg;
    private final zzeew zzewn;
    private final zzeew zzeyg;
    private final zzeew zzfev;
    private final zzeew zzfjh;
    private final zzeew zzfnx;
    private final zzeew zzfum;
    private final zzeew zzfun;

    private zzcem(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9, zzeew zzeew10, zzeew zzeew11, zzeew zzeew12) {
        this.zzeof = zzeew0;
        this.zzeoc = zzeew1;
        this.zzewn = zzeew2;
        this.zzepf = zzeew3;
        this.zzewg = zzeew4;
        this.zzfev = zzeew5;
        this.zzeps = zzeew6;
        this.zzenr = zzeew7;
        this.zzfum = zzeew8;
        this.zzfun = zzeew9;
        this.zzfjh = zzeew10;
        this.zzfnx = zzeew11;
        this.zzeyg = zzeew12;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzced(((zzbqg)this.zzeof.get()), ((zzbrc)this.zzeoc.get()), ((zzbrq)this.zzewn.get()), ((zzbrv)this.zzepf.get()), ((zzbsy)this.zzewg.get()), ((Executor)this.zzfev.get()), ((zzbuu)this.zzeps.get()), ((zzbkb)this.zzenr.get()), ((zzc)this.zzfum.get()), ((zzbqw)this.zzfun.get()), ((zzaub)this.zzfjh.get()), ((zzdq)this.zzfnx.get()), ((zzbsq)this.zzeyg.get()));
    }

    public static zzcem zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9, zzeew zzeew10, zzeew zzeew11, zzeew zzeew12) {
        return new zzcem(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5, zzeew6, zzeew7, zzeew8, zzeew9, zzeew10, zzeew11, zzeew12);
    }
}

