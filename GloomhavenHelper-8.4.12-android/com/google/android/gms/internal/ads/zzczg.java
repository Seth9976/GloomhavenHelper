package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzczg implements zzcye {
    private final Executor executor;
    private final zzavr zzbmv;
    private final ScheduledExecutorService zzfib;
    private final zzavn zzglb;
    private final int zzgll;
    private final Context zzur;

    public zzczg(zzavn zzavn0, int v, Context context0, zzavr zzavr0, ScheduledExecutorService scheduledExecutorService0, Executor executor0) {
        this.zzglb = zzavn0;
        this.zzgll = v;
        this.zzur = context0;
        this.zzbmv = zzavr0;
        this.zzfib = scheduledExecutorService0;
        this.executor = executor0;
    }

    final zzczd zza(Exception exception0) {
        this.zzbmv.zza(exception0, "ATTESTATION_TOKEN_FETCH");
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcqo)).booleanValue()) {
            return zzdnt.zzaj(null);
        }
        zzdno zzdno0 = zzdno.zzg(zzdnt.zza(() -> this.zzglb.zza(this.zzur, this.zzgll), this.executor)).zza(zzczi.zzdpv, this.executor).zza(((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcjx)))), TimeUnit.MILLISECONDS, this.zzfib);
        zzczh zzczh0 = new zzczh(this);
        return zzdno0.zza(Exception.class, zzczh0, zzdoh.zzauv());
    }

    // 检测为 Lambda 实现
    final zzdof zzapu() throws Exception [...]
}

