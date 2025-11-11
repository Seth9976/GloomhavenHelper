package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfq extends zzfv {
    public zzfq(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 51);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        synchronized(this.zzzx) {
            zzej zzej0 = new zzej(((String)this.zzaah.invoke(null)));
            this.zzzx.zzbj(((long)zzej0.zzye));
            this.zzzx.zzbk(((long)zzej0.zzyf));
        }
    }
}

