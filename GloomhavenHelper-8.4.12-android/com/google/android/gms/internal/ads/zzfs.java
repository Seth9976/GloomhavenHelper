package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfs extends zzfv {
    private static volatile Long zzaae;
    private static final Object zzzw;

    static {
        zzfs.zzzw = new Object();
    }

    public zzfs(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 33);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(zzfs.zzaae == null) {
            Object object0 = zzfs.zzzw;
            synchronized(object0) {
                if(zzfs.zzaae == null) {
                    zzfs.zzaae = (Long)this.zzaah.invoke(null);
                }
            }
        }
        synchronized(this.zzzx) {
            this.zzzx.zzaz(((long)zzfs.zzaae));
        }
    }
}

