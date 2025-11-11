package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfc extends zzfv {
    private static volatile Long zzzv;
    private static final Object zzzw;

    static {
        zzfc.zzzw = new Object();
    }

    public zzfc(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 44);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(zzfc.zzzv == null) {
            Object object0 = zzfc.zzzw;
            synchronized(object0) {
                if(zzfc.zzzv == null) {
                    zzfc.zzzv = (Long)this.zzaah.invoke(null);
                }
            }
        }
        synchronized(this.zzzx) {
            this.zzzx.zzbh(((long)zzfc.zzzv));
        }
    }
}

