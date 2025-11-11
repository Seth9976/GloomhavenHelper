package com.google.android.gms.internal.ads;

public final class zzol {
    private byte[] data;
    private int zzbgp;
    private int zzbgq;
    private int zzbgr;

    public zzol(byte[] arr_b, int v, int v1) {
        this.data = arr_b;
        this.zzbgp = v;
        this.zzbgr = v1;
        this.zzbgq = 0;
        this.zziw();
    }

    public final int zzbd(int v) {
        if(v == 0) {
            return 0;
        }
        int v2 = v / 8;
        int v3 = 0;
        for(int v1 = 0; v1 < v2; ++v1) {
            int v4 = this.zzbf(this.zzbgp + 1) ? this.zzbgp + 2 : this.zzbgp + 1;
            int v5 = this.zzbgq;
            int v6 = v5 == 0 ? this.data[this.zzbgp] : (this.data[v4] & 0xFF) >>> 8 - v5 | (this.data[this.zzbgp] & 0xFF) << v5;
            v -= 8;
            v3 |= (0xFF & v6) << v;
            this.zzbgp = v4;
        }
        if(v > 0) {
            int v7 = this.zzbgq + v;
            int v8 = (byte)(0xFF >> 8 - v);
            int v9 = this.zzbf(this.zzbgp + 1) ? this.zzbgp + 2 : this.zzbgp + 1;
            if(v7 > 8) {
                byte[] arr_b = this.data;
                int v10 = (arr_b[this.zzbgp] & 0xFF) << v7 - 8;
                this.zzbgp = v9;
                v3 |= v8 & ((0xFF & arr_b[v9]) >> 16 - v7 | v10);
            }
            else {
                int v11 = v8 & (0xFF & this.data[this.zzbgp]) >> 8 - v7 | v3;
                if(v7 == 8) {
                    this.zzbgp = v9;
                }
                v3 = v11;
            }
            this.zzbgq = v7 % 8;
        }
        this.zziw();
        return v3;
    }

    public final void zzbe(int v) {
        int v1 = this.zzbgp;
        this.zzbgp = v / 8 + v1;
        this.zzbgq += v % 8;
        int v2 = this.zzbgq;
        if(v2 > 7) {
            ++this.zzbgp;
            this.zzbgq = v2 - 8;
        }
        for(int v3 = v1 + 1; v3 <= this.zzbgp; ++v3) {
            if(this.zzbf(v3)) {
                ++this.zzbgp;
                v3 += 2;
            }
        }
        this.zziw();
    }

    private final boolean zzbf(int v) {
        return 2 <= v && v < this.zzbgr && (this.data[v] == 3 && this.data[v - 2] == 0 && this.data[v - 1] == 0);
    }

    public final boolean zzis() {
        return this.zzbd(1) == 1;
    }

    public final int zzit() {
        return this.zziv();
    }

    public final int zziu() {
        int v = this.zziv();
        return v % 2 == 0 ? -((v + 1) / 2) : (v + 1) / 2;
    }

    private final int zziv() {
        int v = 0;
        int v1;
        for(v1 = 0; !this.zzis(); ++v1) {
        }
        if(v1 > 0) {
            v = this.zzbd(v1);
        }
        return (1 << v1) - 1 + v;
    }

    private final void zziw() {
        zzob.checkState(this.zzbgp >= 0 && (this.zzbgq >= 0 && this.zzbgq < 8 && (this.zzbgp < this.zzbgr || this.zzbgp == this.zzbgr && this.zzbgq == 0)));
    }
}

