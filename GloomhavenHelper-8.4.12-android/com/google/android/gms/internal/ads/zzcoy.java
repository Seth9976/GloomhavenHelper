package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcoy extends zzcox {
    private final zzbtl zzemy;
    private final zzcrh zzeyi;
    private final zzbgk zzgcx;
    private final zza zzgcy;

    public zzcoy(zzbgk zzbgk0, zza zzbpt$zza0, zzcrh zzcrh0, zzbtl zzbtl0) {
        this.zzgcx = zzbgk0;
        this.zzgcy = zzbpt$zza0;
        this.zzeyi = zzcrh0;
        this.zzemy = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcox
    protected final zzdof zza(zzdeu zzdeu0, Bundle bundle0) {
        return this.zzgcx.zzacp().zzd(this.zzgcy.zza(zzdeu0).zze(bundle0).zzahz()).zzd(this.zzemy).zzb(this.zzeyi).zzaev().zzadx().zzahq();
    }
}

