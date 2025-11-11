package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class zzdwc implements zzdoy {
    private final zzdwp zzhmj;
    private final zzdpp zzhmk;
    private final int zzhml;

    public zzdwc(zzdwp zzdwp0, zzdpp zzdpp0, int v) {
        this.zzhmj = zzdwp0;
        this.zzhmk = zzdpp0;
        this.zzhml = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdoy
    public final byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = this.zzhmj.zzn(arr_b);
        if(arr_b1 == null) {
            arr_b1 = new byte[0];
        }
        byte[] arr_b3 = zzdvp.zza(new byte[][]{arr_b1, arr_b2, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long)arr_b1.length) * 8L).array(), 8)});
        return zzdvp.zza(new byte[][]{arr_b2, this.zzhmk.zzl(arr_b3)});
    }
}

