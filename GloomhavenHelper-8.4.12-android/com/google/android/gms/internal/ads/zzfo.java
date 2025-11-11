package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfo extends zzfv {
    private final boolean zzaad;

    public zzfo(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 61);
        this.zzaad = zzei0.zzcj();
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        long v = (long)(((Long)this.zzaah.invoke(null, this.zzuy.getContext(), Boolean.valueOf(this.zzaad))));
        synchronized(this.zzzx) {
            this.zzzx.zzbo(v);
        }
    }
}

