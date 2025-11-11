package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzcph implements zzdng {
    private final Executor executor;
    private final zzdis zzfdr;
    private final ScheduledExecutorService zzffm;
    private final zzdif zzfis;
    private final zzbqk zzflh;
    private final zzbng zzgdh;
    private final zzcpc zzgdi;

    public zzcph(zzdif zzdif0, zzcpc zzcpc0, zzbqk zzbqk0, zzdis zzdis0, zzbng zzbng0, Executor executor0, ScheduledExecutorService scheduledExecutorService0) {
        this.zzfis = zzdif0;
        this.zzgdi = zzcpc0;
        this.zzflh = zzbqk0;
        this.zzfdr = zzdis0;
        this.zzgdh = zzbng0;
        this.executor = executor0;
        this.zzffm = scheduledExecutorService0;
    }

    // 检测为 Lambda 实现
    final zzdof zza(zzdei zzdei0, zzcly zzcly0, zzdeq zzdeq0, Throwable throwable0) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) throws Exception {
        zzdof zzdof0 = this.zzfis.zzu(zzdig.zzgvp).zze(zzdnt.immediateFailedFuture(new zzcpe("No ad configs", 3))).zzata();
        zzbjr zzbjr0 = new zzbjr(((zzdeq)object0), this.zzfdr);
        this.zzflh.zza(zzbjr0, this.executor);
        int v = 0;
        for(Object object1: ((zzdeq)object0).zzgqm.zzgqi) {
            zzdei zzdei0 = (zzdei)object1;
            for(Object object2: zzdei0.zzgpk) {
                String s = (String)object2;
                zzcly zzcly0 = this.zzgdh.zzd(zzdei0.zzfmh, s);
                if(zzcly0 != null && zzcly0.zza(((zzdeq)object0), zzdei0)) {
                    zzdhx zzdhx0 = this.zzfis.zza(zzdig.zzgvq, zzdof0).zzgq("render-config-" + v + "-" + s);
                    zzcpg zzcpg0 = (Throwable throwable0) -> {
                        zzdof zzdof0 = zzdnt.zza(zzcly0.zzb(((zzdeq)object0), zzdei0), ((long)zzdei0.zzgpz), TimeUnit.MILLISECONDS, this.zzffm);
                        return this.zzgdi.zza(zzdei0, zzdof0);
                    };
                    zzdof0 = zzdhx0.zza(Throwable.class, zzcpg0).zzata();
                    break;
                }
                if(false) {
                    break;
                }
            }
            ++v;
        }
        return zzdof0;
    }
}

