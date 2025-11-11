package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;

final class zzdrg extends zzdpi {
    private final zzdre zzhft;

    zzdrg(zzdre zzdre0, Class class0) {
        this.zzhft = zzdre0;
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final void zzc(zzeah zzeah0) throws GeneralSecurityException {
        zzdrm.zza(((zzdti)zzeah0).zzaxe());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final Object zzd(zzeah zzeah0) throws GeneralSecurityException {
        KeyPair keyPair0 = zzdvy.zza(zzdvy.zza(zzdrm.zza(((zzdti)zzeah0).zzaxe().zzaxg().zzaxt())));
        ECPublicKey eCPublicKey0 = (ECPublicKey)keyPair0.getPublic();
        ECPrivateKey eCPrivateKey0 = (ECPrivateKey)keyPair0.getPrivate();
        ECPoint eCPoint0 = eCPublicKey0.getW();
        zzdtp zzdtp0 = (zzdtp)(((zzdyz)zzdtp.zzaxq().zzek(0).zzc(((zzdti)zzeah0).zzaxe()).zzab(zzdxn.zzt(eCPoint0.getAffineX().toByteArray())).zzac(zzdxn.zzt(eCPoint0.getAffineY().toByteArray())).zzbcx()));
        return (zzdtm)(((zzdyz)zzdtm.zzaxm().zzej(0).zzb(zzdtp0).zzy(zzdxn.zzt(eCPrivateKey0.getS().toByteArray())).zzbcx()));
    }

    @Override  // com.google.android.gms.internal.ads.zzdpi
    public final zzeah zzq(zzdxn zzdxn0) throws zzdzh {
        return zzdti.zzn(zzdxn0, zzdym.zzbcg());
    }
}

