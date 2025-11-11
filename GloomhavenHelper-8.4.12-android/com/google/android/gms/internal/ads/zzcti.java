package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcti implements zzdng {
    private Executor executor;
    private zzcjk zzghl;

    public zzcti(Executor executor0, zzcjk zzcjk0) {
        this.executor = executor0;
        this.zzghl = zzcjk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) throws Exception {
        return zzdnt.zzb(this.zzghl.zzh(((zzaqx)object0)), new zzcth(((zzaqx)object0)), this.executor);
    }
}

