package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdqq extends zzdpi {
    private final zzdqo zzhfl;

    zzdqq(zzdqo zzdqo0, Class class0) {
        this.zzhfl = zzdqo0;
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final void zzc(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzez(((zzdsy)zzeah0).getKeySize());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final Object zzd(zzeah zzeah0) throws GeneralSecurityException {
        return (zzdsx)(((zzdyz)zzdsx.zzawv().zzw(zzdxn.zzt(zzdwq.zzey(((zzdsy)zzeah0).getKeySize()))).zzeg(0).zzbcx()));
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final zzeah zzq(zzdxn zzdxn0) throws zzdzh {
        return zzdsy.zzk(zzdxn0, zzdym.zzbcg());
    }
}

