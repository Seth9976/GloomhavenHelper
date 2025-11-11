package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqy extends zzdpj {
    zzdqy() {
        zzdpl[] arr_zzdpl = {new zzdqx(zzdoy.class)};
        super(zzduu.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjg;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdra(this, zzduv.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzduu)zzeah0).getVersion(), 0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzduu.zzu(zzdxn0, zzdym.zzbcg());
    }
}

