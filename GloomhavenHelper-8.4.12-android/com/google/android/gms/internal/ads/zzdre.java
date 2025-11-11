package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdre extends zzdpv {
    zzdre() {
        zzdpl[] arr_zzdpl = {new zzdrd(zzdpc.class)};
        super(zzdtm.class, zzdtp.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhje;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdrg(this, zzdti.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        if(((zzdtm)zzeah0).zzavr().isEmpty()) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        zzdwv.zzy(((zzdtm)zzeah0).getVersion(), 0);
        zzdrm.zza(((zzdtm)zzeah0).zzaxl().zzaxe());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdtm.zzo(zzdxn0, zzdym.zzbcg());
    }
}

