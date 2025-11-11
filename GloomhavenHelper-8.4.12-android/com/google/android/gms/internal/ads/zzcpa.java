package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcpa extends zzcox {
    private final zzbtl zzemy;
    private final zzbgk zzgcx;
    private final zza zzgcy;

    public zzcpa(zzbgk zzbgk0, zza zzbpt$zza0, zzbtl zzbtl0) {
        this.zzgcx = zzbgk0;
        this.zzgcy = zzbpt$zza0;
        this.zzemy = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcox
    protected final zzdof zza(zzdeu zzdeu0, Bundle bundle0) {
        return this.zzgcx.zzacr().zze(this.zzgcy.zza(zzdeu0).zze(bundle0).zzahz()).zze(this.zzemy).zzafa().zzadx().zzahq();
    }
}

