package com.google.android.gms.common;

import java.util.Arrays;

final class zzf extends zze {
    private final byte[] zzu;

    zzf(byte[] arr_b) {
        super(Arrays.copyOfRange(arr_b, 0, 25));
        this.zzu = arr_b;
    }

    @Override  // com.google.android.gms.common.zze
    final byte[] getBytes() {
        return this.zzu;
    }
}

