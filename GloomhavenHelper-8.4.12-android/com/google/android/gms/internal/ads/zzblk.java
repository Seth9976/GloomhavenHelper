package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

public final class zzblk implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzeox;
    private final zzeew zzepn;
    private final zzeew zzezd;
    private final zzeew zzfec;
    private final zzeew zzfgi;
    private final zzeew zzfgj;
    private final zzeew zzfgk;
    private final zzeew zzfgl;
    private final zzeew zzfgm;

    public zzblk(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9) {
        this.zzepn = zzeew0;
        this.zzelc = zzeew1;
        this.zzfgi = zzeew2;
        this.zzfec = zzeew3;
        this.zzfgj = zzeew4;
        this.zzfgk = zzeew5;
        this.zzfgl = zzeew6;
        this.zzeox = zzeew7;
        this.zzezd = zzeew8;
        this.zzfgm = zzeew9;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzblk.zza(((zzbne)this.zzepn.get()), ((Context)this.zzelc.get()), ((zzdeh)this.zzfgi.get()), ((View)this.zzfec.get()), ((zzbdv)this.zzfgj.get()), ((zzbnc)this.zzfgk.get()), ((zzbzg)this.zzfgl.get()), ((zzbuz)this.zzeox.get()), zzeek.zzar(this.zzezd), ((Executor)this.zzfgm.get()));
    }

    public static zzbli zza(zzbne zzbne0, Context context0, zzdeh zzdeh0, View view0, zzbdv zzbdv0, zzbnc zzbnc0, zzbzg zzbzg0, zzbuz zzbuz0, zzeed zzeed0, Executor executor0) {
        return new zzbli(zzbne0, context0, zzdeh0, view0, zzbdv0, zzbnc0, zzbzg0, zzbuz0, zzeed0, executor0);
    }
}

