package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;

public final class zzcep implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfda;
    private final zzeew zzfgu;
    private final zzeew zzfgv;
    private final zzeew zzfnx;
    private final zzeew zzfss;
    private final zzeew zzfty;
    private final zzeew zzfup;
    private final zzeew zzfuq;

    private zzcep(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8) {
        this.zzfty = zzeew0;
        this.zzelc = zzeew1;
        this.zzfgu = zzeew2;
        this.zzfnx = zzeew3;
        this.zzfda = zzeew4;
        this.zzfup = zzeew5;
        this.zzfss = zzeew6;
        this.zzfgv = zzeew7;
        this.zzfuq = zzeew8;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzceb(((zzbee)this.zzfty.get()), ((Context)this.zzelc.get()), ((zzdeu)this.zzfgu.get()), ((zzdq)this.zzfnx.get()), ((zzazo)this.zzfda.get()), ((zza)this.zzfup.get()), ((zzsn)this.zzfss.get()), ((zzbsf)this.zzfgv.get()), ((zzbvt)this.zzfuq.get()));
    }

    public static zzcep zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8) {
        return new zzcep(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5, zzeew6, zzeew7, zzeew8);
    }
}

