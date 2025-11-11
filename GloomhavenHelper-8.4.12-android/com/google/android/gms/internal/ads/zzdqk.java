package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdqk extends zzdpi {
    private final zzdqi zzhfj;

    zzdqk(zzdqi zzdqi0, Class class0) {
        this.zzhfj = zzdqi0;
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final void zzc(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzez(((zzdsm)zzeah0).getKeySize());
        zzdsp zzdsp0 = ((zzdsm)zzeah0).zzawg();
        zzdqi.zza(this.zzhfj, zzdsp0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final Object zzd(zzeah zzeah0) throws GeneralSecurityException {
        return (zzdsl)(((zzdyz)zzdsl.zzawh().zzc(((zzdsm)zzeah0).zzawg()).zzu(zzdxn.zzt(zzdwq.zzey(((zzdsm)zzeah0).getKeySize()))).zzee(0).zzbcx()));
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final zzeah zzq(zzdxn zzdxn0) throws zzdzh {
        return zzdsm.zzg(zzdxn0, zzdym.zzbcg());
    }
}

