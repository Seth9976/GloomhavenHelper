package com.google.android.gms.internal.ads;

import android.os.Binder;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public final class zzcic {
    private final zzdoe zzfrv;
    private final zzdoe zzfxl;
    private final zzcjg zzfxm;
    private final zzeed zzfxn;

    public zzcic(zzdoe zzdoe0, zzdoe zzdoe1, zzcjg zzcjg0, zzeed zzeed0) {
        this.zzfxl = zzdoe0;
        this.zzfrv = zzdoe1;
        this.zzfxm = zzcjg0;
        this.zzfxn = zzeed0;
    }

    // 检测为 Lambda 实现
    final zzdof zza(zzaqx zzaqx0, int v, zzcjv zzcjv0) throws Exception [...]

    public final zzdof zze(zzaqx zzaqx0) {
        zzdof zzdof0;
        if(zzawo.zzen(zzaqx0.packageName)) {
            zzdof0 = zzdnt.immediateFailedFuture(new zzcjv(0));
        }
        else {
            zzcif zzcif0 = () -> ((InputStream)this.zzfxm.zzg(zzaqx0).get(((long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcpu)))))), TimeUnit.SECONDS));
            zzdof0 = zzdnt.zzb(this.zzfxl.zzd(zzcif0), ExecutionException.class, zzcie.zzblf, this.zzfrv);
        }
        zzcih zzcih0 = (zzcjv zzcjv0) -> ((zzckh)this.zzfxn.get()).zzb(zzaqx0, Binder.getCallingUid());
        return zzdnt.zzb(zzdof0, zzcjv.class, zzcih0, this.zzfrv);
    }

    // 检测为 Lambda 实现
    final InputStream zzf(zzaqx zzaqx0) throws Exception [...]
}

