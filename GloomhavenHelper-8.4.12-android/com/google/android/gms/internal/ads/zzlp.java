package com.google.android.gms.internal.ads;

public final class zzlp implements zzmq {
    private final zzmq[] zzbaa;

    public zzlp(zzmq[] arr_zzmq) {
        this.zzbaa = arr_zzmq;
    }

    @Override  // com.google.android.gms.internal.ads.zzmq
    public final boolean zzee(long v) {
        boolean z = false;
        do {
            long v1 = this.zzhk();
            if(v1 == 0x8000000000000000L) {
                break;
            }
            zzmq[] arr_zzmq = this.zzbaa;
            boolean z1 = false;
            for(int v2 = 0; v2 < arr_zzmq.length; ++v2) {
                zzmq zzmq0 = arr_zzmq[v2];
                if(zzmq0.zzhk() == v1) {
                    z1 |= zzmq0.zzee(v);
                }
            }
            z |= z1;
        }
        while(z1);
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzmq
    public final long zzhk() {
        zzmq[] arr_zzmq = this.zzbaa;
        long v1 = 0x7FFFFFFFFFFFFFFFL;
        for(int v = 0; v < arr_zzmq.length; ++v) {
            long v2 = arr_zzmq[v].zzhk();
            if(v2 != 0x8000000000000000L) {
                v1 = Math.min(v1, v2);
            }
        }
        return v1 == 0x7FFFFFFFFFFFFFFFL ? 0x8000000000000000L : v1;
    }
}

