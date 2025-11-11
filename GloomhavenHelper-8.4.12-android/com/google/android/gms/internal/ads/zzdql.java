package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdql extends zzdpi {
    private final zzdqj zzhfk;

    zzdql(zzdqj zzdqj0, Class class0) {
        this.zzhfk = zzdqj0;
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final void zzc(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzez(((zzdst)zzeah0).getKeySize());
        switch(((zzdst)zzeah0).zzawp().zzawm()) {
            case 12: 
            case 16: {
                return;
            }
            default: {
                throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final Object zzd(zzeah zzeah0) throws GeneralSecurityException {
        return (zzdsq)(((zzdyz)zzdsq.zzawq().zzv(zzdxn.zzt(zzdwq.zzey(((zzdst)zzeah0).getKeySize()))).zzb(((zzdst)zzeah0).zzawp()).zzef(0).zzbcx()));
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final zzeah zzq(zzdxn zzdxn0) throws zzdzh {
        return zzdst.zzi(zzdxn0, zzdym.zzbcg());
    }
}

