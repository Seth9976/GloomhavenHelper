package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;

public final class zzcow extends zzcox {
    private final zzbtl zzemy;
    private final zzbxk zzena;
    private final zzcrh zzeyi;
    private final ViewGroup zzfgb;
    private final zzbsf zzfho;
    private final zzbgk zzgcx;
    private final zza zzgcy;

    public zzcow(zzbgk zzbgk0, zza zzbpt$zza0, zzcrh zzcrh0, zzbtl zzbtl0, zzbxk zzbxk0, zzbsf zzbsf0, @Nullable ViewGroup viewGroup0) {
        this.zzgcx = zzbgk0;
        this.zzgcy = zzbpt$zza0;
        this.zzeyi = zzcrh0;
        this.zzemy = zzbtl0;
        this.zzena = zzbxk0;
        this.zzfho = zzbsf0;
        this.zzfgb = viewGroup0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcox
    protected final zzdof zza(zzdeu zzdeu0, Bundle bundle0) {
        return this.zzgcx.zzacm().zzc(this.zzgcy.zza(zzdeu0).zze(bundle0).zzahz()).zzc(this.zzemy).zza(this.zzeyi).zzb(this.zzena).zza(new zzbmy(this.zzfho)).zzb(new zzblf(this.zzfgb)).zzael().zzadx().zzahq();
    }
}

