package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbpe implements zzeej {
    private final zzeew zzelw;
    private final zzeew zzerc;
    private final zzeew zzffi;
    private final zzeew zzfgu;
    private final zzeew zzfjj;

    private zzbpe(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzfjj = zzeew0;
        this.zzfgu = zzeew1;
        this.zzffi = zzeew2;
        this.zzerc = zzeew3;
        this.zzelw = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbpf(((Context)this.zzfjj.get()), ((zzdeu)this.zzfgu.get()), ((zzazo)this.zzffi.get()), ((zzawh)this.zzerc.get()), ((zzcho)this.zzelw.get()));
    }

    public static zzbpe zzb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        return new zzbpe(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4);
    }
}

