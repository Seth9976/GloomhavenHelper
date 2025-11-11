package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfg extends zzfv {
    private static final Object zzzw;
    private static volatile Long zzzy;

    static {
        zzfg.zzzw = new Object();
    }

    public zzfg(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 22);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(zzfg.zzzy == null) {
            Object object0 = zzfg.zzzw;
            synchronized(object0) {
                if(zzfg.zzzy == null) {
                    zzfg.zzzy = (Long)this.zzaah.invoke(null);
                }
            }
        }
        synchronized(this.zzzx) {
            this.zzzx.zzav(((long)zzfg.zzzy));
        }
    }
}

