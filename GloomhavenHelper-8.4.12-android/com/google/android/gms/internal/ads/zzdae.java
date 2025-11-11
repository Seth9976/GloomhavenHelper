package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzdae implements zzcye {
    private ScheduledExecutorService zzfib;
    private zzapj zzgma;
    private Context zzur;

    public zzdae(zzapj zzapj0, ScheduledExecutorService scheduledExecutorService0, Context context0) {
        this.zzgma = zzapj0;
        this.zzfib = scheduledExecutorService0;
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return zzdnt.zzb(zzdnt.zza(this.zzgma.zzs(this.zzur), ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcnr)))), TimeUnit.MILLISECONDS, this.zzfib), zzdad.zzdpv, zzazq.zzdxk);
    }
}

