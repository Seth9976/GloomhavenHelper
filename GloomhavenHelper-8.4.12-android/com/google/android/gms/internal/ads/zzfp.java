package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfp extends zzfv {
    private final zzer zzvp;
    private long zzzi;

    public zzfp(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1, zzer zzer0) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 53);
        this.zzvp = zzer0;
        if(zzer0 != null) {
            this.zzzi = zzer0.zzcq();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(this.zzvp != null) {
            this.zzzx.zzbl(((long)(((Long)this.zzaah.invoke(null, this.zzzi)))));
        }
    }
}

