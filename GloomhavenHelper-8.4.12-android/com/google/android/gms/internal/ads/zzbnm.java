package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzbnm {
    private final Executor executor;
    private volatile boolean zzadk;
    private final ScheduledExecutorService zzfib;
    private final zzdof zzfic;

    public zzbnm(Executor executor0, ScheduledExecutorService scheduledExecutorService0, zzdof zzdof0) {
        this.zzadk = true;
        this.executor = executor0;
        this.zzfib = scheduledExecutorService0;
        this.zzfic = zzdof0;
    }

    public final boolean isLoading() {
        return this.zzadk;
    }

    private final void zza(List list0, zzdnu zzdnu0) {
        if(list0 != null && !list0.isEmpty()) {
            zzdof zzdof0 = zzdnt.zzaj(null);
            for(Object object0: list0) {
                zzbno zzbno0 = new zzbno(zzdnu0);
                zzdof0 = zzdnt.zzb(zzdnt.zzb(zzdof0, Throwable.class, zzbno0, this.executor), (zzbnf zzbnf0) -> {
                    if(zzbnf0 != null) {
                        zzdnu0.onSuccess(zzbnf0);
                    }
                    return zzdnt.zza(((zzdof)object0), ((long)(((Long)zzabs.zzcvv.get()))), TimeUnit.MILLISECONDS, this.zzfib);
                }, this.executor);
            }
            zzdnt.zza(zzdof0, new zzbns(this, zzdnu0), this.executor);
            return;
        }
        zzbnp zzbnp0 = new zzbnp(zzdnu0);
        this.executor.execute(zzbnp0);
    }

    // 检测为 Lambda 实现
    final zzdof zza(zzdnu zzdnu0, zzdof zzdof0, zzbnf zzbnf0) throws Exception [...]

    public final void zza(zzdnu zzdnu0) {
        zzbnt zzbnt0 = new zzbnt(this, zzdnu0);
        zzdnt.zza(this.zzfic, zzbnt0, this.executor);
    }

    private final void zzahk() {
        zzbnq zzbnq0 = () -> this.zzadk = false;
        zzazq.zzdxo.execute(zzbnq0);
    }

    // 检测为 Lambda 实现
    final void zzahl() [...]
}

