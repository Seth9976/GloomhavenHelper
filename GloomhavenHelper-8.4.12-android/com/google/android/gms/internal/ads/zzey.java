package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

public final class zzey extends zzfv {
    private final Context zzzr;
    private static zzfy zzzt;

    static {
        zzey.zzzt = new zzfy();
    }

    public zzey(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1, Context context0) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 29);
        this.zzzr = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        this.zzzx.zzai("E");
        AtomicReference atomicReference0 = zzey.zzzt.zzaw("com.esotericsoftware.gloomhavenhelper");
        if(atomicReference0.get() == null) {
            synchronized(atomicReference0) {
                if(atomicReference0.get() == null) {
                    atomicReference0.set(((String)this.zzaah.invoke(null, this.zzzr)));
                }
            }
        }
        String s = (String)atomicReference0.get();
        synchronized(this.zzzx) {
            this.zzzx.zzai(zzci.zza(s.getBytes(), true));
        }
    }
}

