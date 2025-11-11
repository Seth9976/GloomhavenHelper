package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqu extends zzdpj {
    zzdqu() {
        zzdpl[] arr_zzdpl = {new zzdqt(zzdoy.class)};
        super(zzduq.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjg;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdqw(this, zzdur.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzduq)zzeah0).getVersion(), 0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzduq.zzs(zzdxn0, zzdym.zzbcg());
    }
}

