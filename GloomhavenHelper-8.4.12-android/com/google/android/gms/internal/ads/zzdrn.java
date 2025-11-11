package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzdrn implements zzdpf {
    private final zzdpr zzhfw;

    public zzdrn(zzdpr zzdpr0) {
        this.zzhfw = zzdpr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpf
    public final byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return zzdvp.zza(new byte[][]{this.zzhfw.zzavl().zzavk(), ((zzdpf)this.zzhfw.zzavl().zzavh()).zzc(arr_b, arr_b1)});
    }
}

