package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public final class zzcss implements zzcso {
    private final zzbgk zzgcx;
    private final Context zzgfp;
    @GuardedBy("this")
    private final zzdew zzgfq;
    private final zzcsm zzggs;
    @Nullable
    @GuardedBy("this")
    private zzbnm zzggt;

    public zzcss(zzbgk zzbgk0, Context context0, zzcsm zzcsm0, zzdew zzdew0) {
        this.zzgcx = zzbgk0;
        this.zzgfp = context0;
        this.zzggs = zzcsm0;
        this.zzgfq = zzdew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcso
    public final boolean isLoading() {
        return this.zzggt != null && this.zzggt.isLoading();
    }

    @Override  // com.google.android.gms.internal.ads.zzcso
    public final boolean zza(zzuh zzuh0, String s, zzcsr zzcsr0, zzcsq zzcsq0) throws RemoteException {
        if(s == null) {
            zzawf.zzey("Ad unit ID should not be null for NativeAdLoader.");
            this.zzgcx.zzacf().execute(() -> this.zzggs.zzaot().onAdFailedToLoad(1));
            return false;
        }
        zzdfc.zze(this.zzgfp, zzuh0.zzccp);
        zzdeu zzdeu0 = this.zzgfq.zzg(zzuh0).zzdl((zzcsr0 instanceof zzcst ? ((zzcst)zzcsr0).zzggu : 1)).zzarb();
        zzbxr zzbxr0 = this.zzgcx.zzacq().zza(new zza().zzcc(this.zzgfp).zza(zzdeu0).zzahz());
        com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza0 = new com.google.android.gms.internal.ads.zzbtl.zza().zza(this.zzggs.zzaos(), this.zzgcx.zzacf());
        Executor executor0 = this.zzgcx.zzacf();
        com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza1 = zzbtl$zza0.zza(this.zzggs.zzaot(), executor0).zza(this.zzggs.zzaou(), this.zzgcx.zzacf()).zza(this.zzggs.zzaov(), this.zzgcx.zzacf()).zza(this.zzggs.zzaor(), this.zzgcx.zzacf());
        Executor executor1 = this.zzgcx.zzacf();
        zzbxo zzbxo0 = zzbxr0.zza(zzbtl$zza1.zza(zzdeu0.zzgqw, executor1).zzais()).zza(this.zzggs.zzaoq()).zzadh();
        zzbxo0.zzadz().zzdm(1);
        this.zzggt = new zzbnm(this.zzgcx.zzach(), this.zzgcx.zzacg(), zzbxo0.zzadx().zzahq());
        this.zzggt.zza(new zzcsu(this, zzcsq0, zzbxo0));
        return true;
    }

    // 检测为 Lambda 实现
    final void zzaox() [...]
}

