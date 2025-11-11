package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqp extends zzdpj {
    zzdqp() {
        zzdpl[] arr_zzdpl = {new zzdqs(zzdoy.class)};
        super(zzdtb.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdqr(this, zzdtc.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdtb)zzeah0).getVersion(), 0);
        if(((zzdtb)zzeah0).zzavr().size() != 0x20) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdtb.zzl(zzdxn0, zzdym.zzbcg());
    }
}

