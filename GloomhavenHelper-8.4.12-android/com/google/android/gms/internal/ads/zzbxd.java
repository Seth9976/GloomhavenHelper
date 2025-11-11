package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbxd implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfda;
    private final zzeew zzffj;
    private final zzeew zzfgj;
    private final zzeew zzfhn;

    private zzbxd(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzelc = zzeew0;
        this.zzfgj = zzeew1;
        this.zzfhn = zzeew2;
        this.zzfda = zzeew3;
        this.zzffj = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbxa(((Context)this.zzelc.get()), ((zzbdv)this.zzfgj.get()), ((zzdei)this.zzfhn.get()), ((zzazo)this.zzfda.get()), ((int)(((Integer)this.zzffj.get()))));
    }

    public static zzbxd zzc(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        return new zzbxd(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4);
    }
}

