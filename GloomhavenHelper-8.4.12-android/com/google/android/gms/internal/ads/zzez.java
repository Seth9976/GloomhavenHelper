package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzez extends zzfv {
    public zzez(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 5);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        this.zzzx.zzam(-1L);
        this.zzzx.zzan(-1L);
        int[] arr_v = (int[])this.zzaah.invoke(null, this.zzuy.getContext());
        synchronized(this.zzzx) {
            this.zzzx.zzam(((long)arr_v[0]));
            this.zzzx.zzan(((long)arr_v[1]));
            if(arr_v[2] != 0x80000000) {
                this.zzzx.zzbm(((long)arr_v[2]));
            }
        }
    }
}

