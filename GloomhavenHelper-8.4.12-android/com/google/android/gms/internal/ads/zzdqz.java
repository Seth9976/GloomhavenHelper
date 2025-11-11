package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqz extends zzdpj {
    zzdqz() {
        zzdpl[] arr_zzdpl = {new zzdrc(zzdoy.class)};
        super(zzdvb.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdrb(this, zzdve.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdvb)zzeah0).getVersion(), 0);
        if(((zzdvb)zzeah0).zzavr().size() != 0x20) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdvb.zzw(zzdxn0, zzdym.zzbcg());
    }
}

