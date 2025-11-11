package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqi extends zzdpj {
    zzdqi() {
        zzdpl[] arr_zzdpl = {new zzdqh(zzdwp.class)};
        super(zzdsl.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    static void zza(zzdqi zzdqi0, zzdsp zzdsp0) throws GeneralSecurityException {
        zzdqi.zza(zzdsp0);
    }

    private static void zza(zzdsp zzdsp0) throws GeneralSecurityException {
        if(zzdsp0.zzawm() < 12 || zzdsp0.zzawm() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdqk(this, zzdsm.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdsl)zzeah0).getVersion(), 0);
        zzdwv.zzez(((zzdsl)zzeah0).zzavr().size());
        zzdqi.zza(((zzdsl)zzeah0).zzawg());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdsl.zzf(zzdxn0, zzdym.zzbcg());
    }
}

