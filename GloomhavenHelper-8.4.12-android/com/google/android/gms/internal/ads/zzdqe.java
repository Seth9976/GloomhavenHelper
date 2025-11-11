package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqe extends zzdpj {
    zzdqe() {
        zzdpl[] arr_zzdpl = {new zzdqg(zzdoy.class)};
        super(zzdsh.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdqf(this, zzdsi.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdsh)zzeah0).getVersion(), 0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdsh.zzd(zzdxn0, zzdym.zzbcg());
    }
}

