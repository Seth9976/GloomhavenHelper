package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqj extends zzdpj {
    zzdqj() {
        zzdpl[] arr_zzdpl = {new zzdqm(zzdoy.class)};
        super(zzdsq.class, arr_zzdpl);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzb zzavc() {
        return zzb.zzhjd;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzdpi zzavf() {
        return new zzdql(this, zzdst.class);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final void zze(zzeah zzeah0) throws GeneralSecurityException {
        zzdwv.zzy(((zzdsq)zzeah0).getVersion(), 0);
        zzdwv.zzez(((zzdsq)zzeah0).zzavr().size());
        switch(((zzdsq)zzeah0).zzawp().zzawm()) {
            case 12: 
            case 16: {
                return;
            }
            default: {
                throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpj
    public final zzeah zzr(zzdxn zzdxn0) throws zzdzh {
        return zzdsq.zzh(zzdxn0, zzdym.zzbcg());
    }
}

