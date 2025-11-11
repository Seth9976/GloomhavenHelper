package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdrf extends zzdpj {
    public zzdrf() {
        zzdpl[] arr_zzdpl = {new zzdri(zzdpf.class)};
        super(zzdtp.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjf;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdtp)zzeah0).getVersion(), 0);
        zzdrm.zza(((zzdtp)zzeah0).zzaxe());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdtp.zzp(zzdxn0, zzdym.zzbcg());
    }
}

