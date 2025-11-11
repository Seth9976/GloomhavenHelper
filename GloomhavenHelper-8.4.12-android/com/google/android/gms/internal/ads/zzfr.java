package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfr extends zzfv {
    public zzfr(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 0x30);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        this.zzzx.zzf(zzcd.zzld);
        boolean z = ((Boolean)this.zzaah.invoke(null, this.zzuy.getContext())).booleanValue();
        synchronized(this.zzzx) {
            if(z) {
                this.zzzx.zzf(zzcd.zzlc);
            }
            else {
                this.zzzx.zzf(zzcd.zzlb);
            }
        }
    }
}

