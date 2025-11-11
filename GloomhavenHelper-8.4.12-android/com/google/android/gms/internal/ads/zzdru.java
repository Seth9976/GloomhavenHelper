package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdru extends zzdpi {
    zzdru(zzdrs zzdrs0, Class class0) {
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final void zzc(zzeah zzeah0) throws GeneralSecurityException {
        if(((zzdty)zzeah0).getKeySize() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzdrs.zza(((zzdty)zzeah0).zzaxy());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final Object zzd(zzeah zzeah0) throws GeneralSecurityException {
        return (zzdtx)(((zzdyz)zzdtx.zzaxz().zzen(0).zzd(((zzdty)zzeah0).zzaxy()).zzad(zzdxn.zzt(zzdwq.zzey(((zzdty)zzeah0).getKeySize()))).zzbcx()));
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final zzeah zzq(zzdxn zzdxn0) throws zzdzh {
        return zzdty.zzr(zzdxn0, zzdym.zzbcg());
    }
}

