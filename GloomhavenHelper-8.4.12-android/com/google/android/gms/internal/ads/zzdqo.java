package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqo extends zzdpj {
    zzdqo() {
        zzdpl[] arr_zzdpl = {new zzdqn(zzdoy.class)};
        super(zzdsx.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdqq(this, zzdsy.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdsx)zzeah0).getVersion(), 0);
        zzdwv.zzez(((zzdsx)zzeah0).zzavr().size());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdsx.zzj(zzdxn0, zzdym.zzbcg());
    }
}

