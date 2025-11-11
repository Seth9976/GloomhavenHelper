package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfh extends zzfv {
    private long zzzz;

    public zzfh(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 12);
        this.zzzz = -1L;
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        this.zzzx.zzap(-1L);
        this.zzzx.zzap(((long)(((Long)this.zzaah.invoke(null, this.zzuy.getContext())))));
    }
}

