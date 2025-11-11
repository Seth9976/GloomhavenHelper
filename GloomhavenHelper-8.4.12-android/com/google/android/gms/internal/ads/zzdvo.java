package com.google.android.gms.internal.ads;

import java.security.InvalidKeyException;

final class zzdvo extends zzdvr {
    zzdvo(byte[] arr_b, int v) throws InvalidKeyException {
        super(arr_b, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdvr
    final int[] zzb(int[] arr_v, int v) {
        if(arr_v.length != 3) {
            throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", ((int)(arr_v.length << 5))));
        }
        int[] arr_v1 = new int[16];
        zzdvr.zza(arr_v1, this.zzhlp);
        arr_v1[12] = v;
        System.arraycopy(arr_v, 0, arr_v1, 13, arr_v.length);
        return arr_v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdvr
    final int zzbab() {
        return 12;
    }
}

