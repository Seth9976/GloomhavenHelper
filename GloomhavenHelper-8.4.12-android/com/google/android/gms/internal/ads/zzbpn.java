package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbpn implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfhn;
    private final zzeew zzfjq;

    private zzbpn(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzelc = zzeew0;
        this.zzfhn = zzeew1;
        this.zzfjq = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbpk(((Context)this.zzelc.get()), ((zzdei)this.zzfhn.get()), ((zzapm)this.zzfjq.get()));
    }

    public static zzbpn zzh(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzbpn(zzeew0, zzeew1, zzeew2);
    }
}

