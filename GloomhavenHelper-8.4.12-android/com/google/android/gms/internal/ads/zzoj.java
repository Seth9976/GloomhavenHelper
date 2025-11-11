package com.google.android.gms.internal.ads;

public final class zzoj {
    private byte[] data;
    private int zzbgp;
    private int zzbgq;
    private int zzbgr;

    public zzoj() {
    }

    public zzoj(byte[] arr_b) {
        this(arr_b, arr_b.length);
    }

    private zzoj(byte[] arr_b, int v) {
        this.data = arr_b;
        this.zzbgr = v;
    }

    public final int zzbd(int v) {
        boolean z = false;
        if(v == 0) {
            return 0;
        }
        int v1 = v;
        int v3 = 0;
        for(int v2 = 0; v2 < v / 8; ++v2) {
            int v4 = this.zzbgq;
            int v5 = v4 == 0 ? this.data[this.zzbgp] : (this.data[this.zzbgp + 1] & 0xFF) >>> 8 - v4 | (this.data[this.zzbgp] & 0xFF) << v4;
            v1 -= 8;
            v3 |= (0xFF & v5) << v1;
            ++this.zzbgp;
        }
        if(v1 > 0) {
            int v6 = this.zzbgq + v1;
            int v7 = (byte)(0xFF >> 8 - v1);
            if(v6 > 8) {
                int v8 = this.zzbgp;
                this.zzbgp = v8 + 1;
                v3 |= v7 & ((this.data[v8 + 1] & 0xFF) >> 16 - v6 | (this.data[v8] & 0xFF) << v6 - 8);
            }
            else {
                int v9 = this.zzbgp;
                int v10 = v7 & (this.data[v9] & 0xFF) >> 8 - v6 | v3;
                if(v6 == 8) {
                    this.zzbgp = v9 + 1;
                }
                v3 = v10;
            }
            this.zzbgq = v6 % 8;
        }
        if(this.zzbgp >= 0 && (this.zzbgq >= 0 && this.zzbgq < 8 && (this.zzbgp < this.zzbgr || this.zzbgp == this.zzbgr && this.zzbgq == 0))) {
            z = true;
        }
        zzob.checkState(z);
        return v3;
    }
}

