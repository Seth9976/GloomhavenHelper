package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzccr implements zzeej {
    private final zzeew zzeqa;
    private final zzeew zzfjj;
    private final zzeew zzfnw;
    private final zzeew zzfte;

    private zzccr(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfjj = zzeew0;
        this.zzfnw = zzeew1;
        this.zzeqa = zzeew2;
        this.zzfte = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzccp(((Context)this.zzfjj.get()), ((zzbyz)this.zzfnw.get()), ((zzbzv)this.zzeqa.get()), ((zzbyo)this.zzfte.get()));
    }

    public static zzccr zzb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzccr(zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

