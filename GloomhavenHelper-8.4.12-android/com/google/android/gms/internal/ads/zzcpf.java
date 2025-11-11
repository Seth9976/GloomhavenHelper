package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;

final class zzcpf implements zzdnu {
    private final String zzgdc;
    private final long zzgdd;
    private final zzcpc zzgde;

    zzcpf(zzcpc zzcpc0, String s, long v) {
        this.zzgde = zzcpc0;
        this.zzgdc = s;
        this.zzgdd = v;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        long v = this.zzgde.zzbmz.elapsedRealtime();
        this.zzgde.zza(this.zzgdc, 0, v - this.zzgdd);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        long v = this.zzgde.zzbmz.elapsedRealtime();
        int v1 = 3;
        if(throwable0 instanceof TimeoutException) {
            v1 = 2;
        }
        else if(!(throwable0 instanceof zzcos)) {
            if(throwable0 instanceof CancellationException) {
                v1 = 4;
            }
            else if(!(throwable0 instanceof zzcid)) {
                v1 = 6;
            }
            else if(((zzcid)throwable0).getErrorCode() == 3) {
                v1 = 1;
            }
            else {
                v1 = 6;
            }
        }
        this.zzgde.zza(this.zzgdc, v1, v - this.zzgdd);
    }
}

