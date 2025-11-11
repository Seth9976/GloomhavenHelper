package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbmx implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfda;
    private final zzeew zzfgj;
    private final zzeew zzfhn;

    public zzbmx(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzelc = zzeew0;
        this.zzfgj = zzeew1;
        this.zzfhn = zzeew2;
        this.zzfda = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbmu(((Context)this.zzelc.get()), ((zzbdv)this.zzfgj.get()), ((zzdei)this.zzfhn.get()), ((zzazo)this.zzfda.get()));
    }
}

