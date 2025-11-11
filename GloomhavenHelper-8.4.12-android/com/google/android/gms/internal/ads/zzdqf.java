package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdqf extends zzdpi {
    private final zzdqe zzhfi;

    zzdqf(zzdqe zzdqe0, Class class0) {
        this.zzhfi = zzdqe0;
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final void zzc(zzeah zzeah0) throws GeneralSecurityException {
        new zzdqi().zzavf().zzc(((zzdsi)zzeah0).zzawd());
        new zzdrs().zzavf().zzc(((zzdsi)zzeah0).zzawe());
        zzdwv.zzez(((zzdsi)zzeah0).zzawd().getKeySize());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final Object zzd(zzeah zzeah0) throws GeneralSecurityException {
        zzdsl zzdsl0 = (zzdsl)new zzdqi().zzavf().zzd(((zzdsi)zzeah0).zzawd());
        zzdtx zzdtx0 = (zzdtx)new zzdrs().zzavf().zzd(((zzdsi)zzeah0).zzawe());
        return (zzdsh)(((zzdyz)zzdsh.zzawb().zzb(zzdsl0).zzb(zzdtx0).zzed(0).zzbcx()));
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final zzeah zzq(zzdxn zzdxn0) throws zzdzh {
        return zzdsi.zze(zzdxn0, zzdym.zzbcg());
    }
}

