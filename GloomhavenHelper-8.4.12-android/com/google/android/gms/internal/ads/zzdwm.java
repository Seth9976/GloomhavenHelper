package com.google.android.gms.internal.ads;

public final class zzdwm {
    private final byte[] data;

    private zzdwm(byte[] arr_b, int v, int v1) {
        this.data = new byte[v1];
        System.arraycopy(arr_b, 0, this.data, 0, v1);
    }

    public final byte[] getBytes() {
        byte[] arr_b = new byte[this.data.length];
        System.arraycopy(this.data, 0, arr_b, 0, this.data.length);
        return arr_b;
    }

    public static zzdwm zzr(byte[] arr_b) {
        return arr_b == null ? null : new zzdwm(arr_b, 0, arr_b.length);
    }
}

