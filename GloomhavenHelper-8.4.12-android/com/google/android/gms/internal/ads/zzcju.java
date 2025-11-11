package com.google.android.gms.internal.ads;

import android.os.Binder;
import java.util.concurrent.ExecutionException;

public final class zzcju {
    private final zzdoe zzfrv;
    private final zzeed zzfxn;
    private final zzcjp zzfyv;

    public zzcju(zzdoe zzdoe0, zzcjp zzcjp0, zzeed zzeed0) {
        this.zzfrv = zzdoe0;
        this.zzfyv = zzcjp0;
        this.zzfxn = zzeed0;
    }

    private final zzdof zza(zzaqx zzaqx0, zzckf zzckf0, zzckf zzckf1, zzdng zzdng0) {
        zzdno zzdno0 = zzdno.zzg((zzawo.zzen(zzaqx0.packageName) ? zzdnt.immediateFailedFuture(new zzcjv(0)) : zzdnt.zzb(zzckf0.zzo(zzaqx0), ExecutionException.class, zzcjx.zzblf, this.zzfrv))).zzb(zzdng0, this.zzfrv);
        zzcjw zzcjw0 = (zzcjv zzcjv0) -> zzdnt.zzb(zzckf1.zzo(zzaqx0), zzdng0, this.zzfrv);
        return (zzdno)zzdnt.zzb(zzdno0, zzcjv.class, zzcjw0, this.zzfrv);
    }

    // 检测为 Lambda 实现
    final zzdof zza(zzckf zzckf0, zzaqx zzaqx0, zzdng zzdng0, zzcjv zzcjv0) throws Exception [...]

    public final zzdof zzj(zzaqx zzaqx0) {
        zzcjz zzcjz0 = new zzcjz(zzaqx0);
        this.zzfyv.getClass();
        return this.zza(zzaqx0, zzcjy.zza(this.zzfyv), (zzaqx zzaqx0) -> ((zzckh)this.zzfxn.get()).zzc(zzaqx0, Binder.getCallingUid()), zzcjz0);
    }

    // 去混淆评级： 低(20)
    public final zzdof zzk(zzaqx zzaqx0) {
        return zzep.zzav(zzaqx0.zzdnd) ? zzdnt.immediateFailedFuture(new zzcke("Pool key missing from removeUrl call.", 1)) : this.zza(zzaqx0, (zzaqx zzaqx0) -> this.zzfyv.zzgh(zzaqx0.zzdnd), (zzaqx zzaqx0) -> ((zzckh)this.zzfxn.get()).zzgi(zzaqx0.zzdnd), zzcka.zzblf);
    }

    // 检测为 Lambda 实现
    final zzdof zzl(zzaqx zzaqx0) [...]

    // 检测为 Lambda 实现
    final zzdof zzm(zzaqx zzaqx0) [...]

    // 检测为 Lambda 实现
    final zzdof zzn(zzaqx zzaqx0) [...]
}

