package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzdqv implements zzdoy {
    private static final byte[] zzhfn;
    private final zzdui zzhfo;
    private final zzdoy zzhfp;

    static {
        zzdqv.zzhfn = new byte[0];
    }

    public zzdqv(zzdui zzdui0, zzdoy zzdoy0) {
        this.zzhfo = zzdui0;
        this.zzhfp = zzdoy0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdoy
    public final byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = zzdpu.zzb(this.zzhfo).toByteArray();
        byte[] arr_b3 = this.zzhfp.zzc(arr_b2, zzdqv.zzhfn);
        byte[] arr_b4 = ((zzdoy)zzdpu.zza("", arr_b2, zzdoy.class)).zzc(arr_b, arr_b1);
        return ByteBuffer.allocate(arr_b3.length + 4 + arr_b4.length).putInt(arr_b3.length).put(arr_b3).put(arr_b4).array();
    }
}

