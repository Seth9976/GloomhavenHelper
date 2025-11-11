package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;

final class zzdrd extends zzdpl {
    zzdrd(Class class0) {
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpl
    public final Object zzak(Object object0) throws GeneralSecurityException {
        zzdtl zzdtl0 = ((zzdtm)object0).zzaxl().zzaxe();
        zzdtq zzdtq0 = zzdtl0.zzaxg();
        zzdwa zzdwa0 = zzdrm.zza(zzdtq0.zzaxt());
        byte[] arr_b = ((zzdtm)object0).zzavr().toByteArray();
        ECParameterSpec eCParameterSpec0 = zzdvy.zza(zzdwa0);
        ECPrivateKeySpec eCPrivateKeySpec0 = new ECPrivateKeySpec(new BigInteger(1, arr_b), eCParameterSpec0);
        PrivateKey privateKey0 = ((KeyFactory)zzdwf.zzhmx.zzhg("EC")).generatePrivate(eCPrivateKeySpec0);
        zzdro zzdro0 = new zzdro(zzdtl0.zzaxh().zzaxb());
        return new zzdvv(((ECPrivateKey)privateKey0), zzdtq0.zzaxv().toByteArray(), zzdrm.zza(zzdtq0.zzaxu()), zzdrm.zza(zzdtl0.zzaxi()), zzdro0);
    }
}

