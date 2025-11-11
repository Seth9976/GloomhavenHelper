package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfk extends zzfv {
    private static volatile String zzaaa;
    private static final Object zzzw;

    static {
        zzfk.zzzw = new Object();
    }

    public zzfk(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 1);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        this.zzzx.zzaf("E");
        if(zzfk.zzaaa == null) {
            synchronized(zzfk.zzzw) {
                if(zzfk.zzaaa == null) {
                    zzfk.zzaaa = (String)this.zzaah.invoke(null);
                }
            }
        }
        synchronized(this.zzzx) {
            this.zzzx.zzaf(zzfk.zzaaa);
        }
    }
}

