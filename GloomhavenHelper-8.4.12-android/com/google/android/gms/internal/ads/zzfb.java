package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfb extends zzfv {
    private long startTime;

    public zzfb(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, long v, int v1, int v2) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v1, 25);
        this.startTime = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        long v = (long)(((Long)this.zzaah.invoke(null)));
        synchronized(this.zzzx) {
            this.zzzx.zzbr(v);
            if(this.startTime != 0L) {
                this.zzzx.zzat(v - this.startTime);
                this.zzzx.zzaw(this.startTime);
            }
        }
    }
}

