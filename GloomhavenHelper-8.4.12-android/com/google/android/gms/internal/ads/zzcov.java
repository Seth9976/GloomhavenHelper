package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcov extends zzcox {
    private final zzbtl zzemy;
    private final zzbxk zzena;
    private final zzbgk zzgcx;
    private final zza zzgcy;

    public zzcov(zzbgk zzbgk0, zzbxk zzbxk0, zza zzbpt$zza0, zzbtl zzbtl0) {
        this.zzgcx = zzbgk0;
        this.zzena = zzbxk0;
        this.zzgcy = zzbpt$zza0;
        this.zzemy = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcox
    protected final zzdof zza(zzdeu zzdeu0, Bundle bundle0) {
        return this.zzgcx.zzacq().zza(this.zzgcy.zza(zzdeu0).zze(bundle0).zzahz()).zza(this.zzemy).zza(this.zzena).zzadh().zzadx().zzahq();
    }
}

