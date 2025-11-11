package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdrr extends zzdpj {
    zzdrr() {
        zzdpl[] arr_zzdpl = {new zzdrq(zzdpp.class)};
        super(zzdsa.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    private static void zza(zzdse zzdse0) throws GeneralSecurityException {
        if(zzdse0.zzavw() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if(zzdse0.zzavw() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdrt(this, zzdsd.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdsa)zzeah0).getVersion(), 0);
        zzdrr.zzea(((zzdsa)zzeah0).zzavr().size());
        zzdrr.zza(((zzdsa)zzeah0).zzavs());
    }

    private static void zzea(int v) throws GeneralSecurityException {
        if(v != 0x20) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 16 bytes");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdsa.zzb(zzdxn0, zzdym.zzbcg());
    }
}

