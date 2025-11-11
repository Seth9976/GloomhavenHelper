package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdrs extends zzdpj {
    public zzdrs() {
        zzdpl[] arr_zzdpl = {new zzdrv(zzdpp.class)};
        super(zzdtx.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    private static void zza(zzdub zzdub0) throws GeneralSecurityException {
        if(zzdub0.zzavw() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        switch(zzdrx.zzhgc[zzdub0.zzaye().ordinal()]) {
            case 1: {
                if(zzdub0.zzavw() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            case 2: {
                if(zzdub0.zzavw() > 0x20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            case 3: {
                if(zzdub0.zzavw() > 0x40) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            default: {
                throw new GeneralSecurityException("unknown hash type");
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdru(this, zzdty.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdtx)zzeah0).getVersion(), 0);
        if(((zzdtx)zzeah0).zzavr().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzdrs.zza(((zzdtx)zzeah0).zzaxy());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdtx.zzq(zzdxn0, zzdym.zzbcg());
    }
}

