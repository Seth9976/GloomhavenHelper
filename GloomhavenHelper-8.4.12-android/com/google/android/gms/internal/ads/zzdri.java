package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

final class zzdri extends zzdpl {
    zzdri(Class class0) {
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpl
    public final Object zzak(Object object0) throws GeneralSecurityException {
        zzdtl zzdtl0 = ((zzdtp)object0).zzaxe();
        zzdtq zzdtq0 = zzdtl0.zzaxg();
        ECPublicKey eCPublicKey0 = zzdvy.zza(zzdrm.zza(zzdtq0.zzaxt()), ((zzdtp)object0).zzaxo().toByteArray(), ((zzdtp)object0).zzaxp().toByteArray());
        zzdro zzdro0 = new zzdro(zzdtl0.zzaxh().zzaxb());
        return new zzdvu(eCPublicKey0, zzdtq0.zzaxv().toByteArray(), zzdrm.zza(zzdtq0.zzaxu()), zzdrm.zza(zzdtl0.zzaxi()), zzdro0);
    }
}

