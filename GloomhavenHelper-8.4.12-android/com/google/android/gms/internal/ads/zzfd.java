package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

public final class zzfd implements Callable {
    private final zzei zzuy;
    private final zzb zzzx;

    public zzfd(zzei zzei0, zzb zzbs$zza$zzb0) {
        this.zzuy = zzei0;
        this.zzzx = zzbs$zza$zzb0;
    }

    @Override
    public final Object call() throws Exception {
        return this.zzcu();
    }

    private final Void zzcu() throws Exception {
        if(this.zzuy.zzcl() != null) {
            this.zzuy.zzcl().get();
        }
        zza zzbs$zza0 = this.zzuy.zzck();
        if(zzbs$zza0 != null) {
            try {
                synchronized(this.zzzx) {
                    byte[] arr_b = zzbs$zza0.toByteArray();
                    zzdym zzdym0 = zzdym.zzbch();
                    this.zzzx.zza(arr_b, 0, arr_b.length, zzdym0);
                    return null;
                }
            }
            catch(zzdzh unused_ex) {
            }
        }
        return null;
    }
}

