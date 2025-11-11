package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzcnf implements zzcly {
    private final ScheduledExecutorService zzfib;
    private final zzbqk zzflh;
    private final zzdoe zzfrv;
    private final zzbmc zzgby;
    private final zzcml zzgbz;

    public zzcnf(zzbmc zzbmc0, zzcml zzcml0, zzbqk zzbqk0, ScheduledExecutorService scheduledExecutorService0, zzdoe zzdoe0) {
        this.zzgby = zzbmc0;
        this.zzgbz = zzcml0;
        this.zzflh = zzbqk0;
        this.zzfib = scheduledExecutorService0;
        this.zzfrv = zzdoe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdeq0.zzgql.zzfir.zzaqx() != null && this.zzgbz.zza(zzdeq0, zzdei0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        zzcne zzcne0 = () -> {
            zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
            zzbmq zzbmq0 = new zzbmq(zzdeq0.zzgql.zzfir.zzaqx(), () -> zzdnt.zza(zzdnt.zza(this.zzgbz.zzb(zzdeq0, zzdei0), ((long)zzdei0.zzgpz), TimeUnit.SECONDS, this.zzfib), new zzcng(this), this.zzfrv));
            return this.zzgby.zza(zzbnv0, zzbmq0).zzaeu();
        };
        return this.zzfrv.zzd(zzcne0);
    }

    // 检测为 Lambda 实现
    final zzblg zzc(zzdeq zzdeq0, zzdei zzdei0) throws Exception [...]

    // 检测为 Lambda 实现
    final void zzd(zzdeq zzdeq0, zzdei zzdei0) [...]
}

