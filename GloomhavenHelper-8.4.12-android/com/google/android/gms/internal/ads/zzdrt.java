package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdrt extends zzdpi {
    zzdrt(zzdrr zzdrr0, Class class0) {
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final void zzc(zzeah zzeah0) throws GeneralSecurityException {
        zzdrr.zza(((zzdsd)zzeah0).zzavs());
        zzdrr.zzea(((zzdsd)zzeah0).getKeySize());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final Object zzd(zzeah zzeah0) throws GeneralSecurityException {
        return (zzdsa)(((zzdyz)zzdsa.zzavt().zzec(0).zzt(zzdxn.zzt(zzdwq.zzey(((zzdsd)zzeah0).getKeySize()))).zzd(((zzdsd)zzeah0).zzavs()).zzbcx()));
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final zzeah zzq(zzdxn zzdxn0) throws zzdzh {
        return zzdsd.zzc(zzdxn0, zzdym.zzbcg());
    }
}

