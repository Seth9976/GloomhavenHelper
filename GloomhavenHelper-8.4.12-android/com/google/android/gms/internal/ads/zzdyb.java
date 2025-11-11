package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

final class zzdyb extends zzdxz {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzhor;
    private int zzhos;
    private int zzhot;
    private int zzhou;
    private int zzhov;

    private zzdyb(byte[] arr_b, int v, int v1, boolean z) {
        super(null);
        this.zzhov = 0x7FFFFFFF;
        this.buffer = arr_b;
        this.limit = v1 + v;
        this.pos = v;
        this.zzhot = this.pos;
        this.zzhor = z;
    }

    zzdyb(byte[] arr_b, int v, int v1, boolean z, zzdyc zzdyc0) {
        this(arr_b, v, v1, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(this.zzbbw());
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(this.zzbbv());
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final String readString() throws IOException {
        int v = this.zzbbt();
        if(v > 0) {
            int v1 = this.pos;
            if(v <= this.limit - v1) {
                String s = new String(this.buffer, v1, v, zzdzc.UTF_8);
                this.pos += v;
                return s;
            }
        }
        if(v != 0) {
            throw v >= 0 ? zzdzh.zzbdi() : zzdzh.zzbdj();
        }
        return "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbb() throws IOException {
        if(this.zzbbr()) {
            this.zzhou = 0;
            return 0;
        }
        this.zzhou = this.zzbbt();
        int v = this.zzhou;
        if(v >>> 3 == 0) {
            throw zzdzh.zzbdl();
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final long zzbbc() throws IOException {
        return this.zzbbu();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final long zzbbd() throws IOException {
        return this.zzbbu();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbe() throws IOException {
        return this.zzbbt();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final long zzbbf() throws IOException {
        return this.zzbbw();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbg() throws IOException {
        return this.zzbbv();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final boolean zzbbh() throws IOException {
        return this.zzbbu() != 0L;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final String zzbbi() throws IOException {
        int v = this.zzbbt();
        if(v > 0) {
            int v1 = this.pos;
            if(v <= this.limit - v1) {
                String s = zzece.zzo(this.buffer, v1, v);
                this.pos += v;
                return s;
            }
        }
        if(v != 0) {
            throw v > 0 ? zzdzh.zzbdi() : zzdzh.zzbdj();
        }
        return "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final zzdxn zzbbj() throws IOException {
        int v = this.zzbbt();
        if(v > 0) {
            int v1 = this.pos;
            if(v <= this.limit - v1) {
                zzdxn zzdxn0 = zzdxn.zzh(this.buffer, v1, v);
                this.pos += v;
                return zzdxn0;
            }
        }
        if(v == 0) {
            return zzdxn.zzhoe;
        }
        if(v > 0) {
            int v2 = this.pos;
            if(v <= this.limit - v2) {
                this.pos = v + v2;
                return zzdxn.zzu(Arrays.copyOfRange(this.buffer, v2, this.pos));
            }
        }
        throw v > 0 ? zzdzh.zzbdi() : zzdzh.zzbdj();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbk() throws IOException {
        return this.zzbbt();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbl() throws IOException {
        return this.zzbbt();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbm() throws IOException {
        return this.zzbbv();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final long zzbbn() throws IOException {
        return this.zzbbw();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbo() throws IOException {
        return zzdyb.zzfl(this.zzbbt());
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final long zzbbp() throws IOException {
        return zzdyb.zzfg(this.zzbbu());
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    final long zzbbq() throws IOException {
        long v = 0L;
        for(int v1 = 0; v1 < 0x40; v1 += 7) {
            int v2 = this.zzbby();
            v |= ((long)(v2 & 0x7F)) << v1;
            if((v2 & 0x80) == 0) {
                return v;
            }
        }
        throw zzdzh.zzbdk();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final boolean zzbbr() throws IOException {
        return this.pos == this.limit;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbs() {
        return this.pos - this.zzhot;
    }

    private final int zzbbt() throws IOException {
        int v5;
        int v = this.pos;
        int v1 = this.limit;
        if(v1 != v) {
            byte[] arr_b = this.buffer;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.pos = v + 1;
                return v2;
            }
            if(v1 - (v + 1) >= 9) {
                int v3 = v + 2;
                int v4 = v2 ^ arr_b[v + 1] << 7;
                if(v4 < 0) {
                    v5 = v4 ^ 0xFFFFFF80;
                    this.pos = v3;
                    return v5;
                }
                int v6 = v3 + 1;
                int v7 = v4 ^ arr_b[v3] << 14;
                if(v7 >= 0) {
                    v5 = v7 ^ 0x3F80;
                    v3 = v6;
                    this.pos = v3;
                    return v5;
                }
                v3 = v6 + 1;
                int v8 = v7 ^ arr_b[v6] << 21;
                if(v8 < 0) {
                    v5 = v8 ^ 0xFFE03F80;
                    this.pos = v3;
                    return v5;
                }
                int v9 = v3 + 1;
                int v10 = arr_b[v3];
                v5 = v8 ^ v10 << 28 ^ 0xFE03F80;
                if(v10 >= 0) {
                    v3 = v9;
                    this.pos = v3;
                    return v5;
                }
                v3 = v9 + 1;
                if(arr_b[v9] >= 0) {
                    this.pos = v3;
                    return v5;
                }
                v9 = v3 + 1;
                if(arr_b[v3] >= 0) {
                    v3 = v9;
                    this.pos = v3;
                    return v5;
                }
                v3 = v9 + 1;
                if(arr_b[v9] >= 0) {
                    this.pos = v3;
                    return v5;
                }
                v9 = v3 + 1;
                if(arr_b[v3] >= 0) {
                    v3 = v9;
                    this.pos = v3;
                    return v5;
                }
                v3 = v9 + 1;
                if(arr_b[v9] >= 0) {
                    this.pos = v3;
                    return v5;
                }
            }
        }
        return (int)this.zzbbq();
    }

    private final long zzbbu() throws IOException {
        long v5;
        int v = this.pos;
        int v1 = this.limit;
        if(v1 != v) {
            byte[] arr_b = this.buffer;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.pos = v + 1;
                return (long)v2;
            }
            if(v1 - (v + 1) >= 9) {
                int v3 = v + 2;
                int v4 = v2 ^ arr_b[v + 1] << 7;
                if(v4 < 0) {
                    v5 = (long)(v4 ^ 0xFFFFFF80);
                    this.pos = v3;
                    return v5;
                }
                int v6 = v3 + 1;
                int v7 = v4 ^ arr_b[v3] << 14;
                if(v7 >= 0) {
                    v3 = v6;
                    v5 = (long)(v7 ^ 0x3F80);
                    this.pos = v3;
                    return v5;
                }
                v3 = v6 + 1;
                int v8 = v7 ^ arr_b[v6] << 21;
                if(v8 < 0) {
                    v5 = (long)(v8 ^ 0xFFE03F80);
                    this.pos = v3;
                    return v5;
                }
                int v9 = v3 + 1;
                long v10 = ((long)v8) ^ ((long)arr_b[v3]) << 28;
                if(v10 >= 0L) {
                    v5 = 0xFE03F80L ^ v10;
                    v3 = v9;
                    this.pos = v3;
                    return v5;
                }
                v3 = v9 + 1;
                long v11 = v10 ^ ((long)arr_b[v9]) << 35;
                if(v11 < 0L) {
                    v5 = v11 ^ 0xFFFFFFF80FE03F80L;
                    this.pos = v3;
                    return v5;
                }
                int v12 = v3 + 1;
                long v13 = v11 ^ ((long)arr_b[v3]) << 42;
                if(v13 >= 0L) {
                    v5 = 0x3F80FE03F80L ^ v13;
                    v3 = v12;
                    this.pos = v3;
                    return v5;
                }
                v3 = v12 + 1;
                long v14 = v13 ^ ((long)arr_b[v12]) << 49;
                if(v14 < 0L) {
                    v5 = v14 ^ 0xFFFE03F80FE03F80L;
                    this.pos = v3;
                    return v5;
                }
                int v15 = v3 + 1;
                v5 = v14 ^ ((long)arr_b[v3]) << 56 ^ 0xFE03F80FE03F80L;
                if(v5 >= 0L) {
                    v3 = v15;
                    this.pos = v3;
                    return v5;
                }
                v3 = v15 + 1;
                if(((long)arr_b[v15]) >= 0L) {
                    this.pos = v3;
                    return v5;
                }
            }
        }
        return this.zzbbq();
    }

    private final int zzbbv() throws IOException {
        int v = this.pos;
        if(this.limit - v < 4) {
            throw zzdzh.zzbdi();
        }
        this.pos = v + 4;
        return (this.buffer[v + 3] & 0xFF) << 24 | (this.buffer[v] & 0xFF | (this.buffer[v + 1] & 0xFF) << 8 | (this.buffer[v + 2] & 0xFF) << 16);
    }

    private final long zzbbw() throws IOException {
        int v = this.pos;
        if(this.limit - v < 8) {
            throw zzdzh.zzbdi();
        }
        this.pos = v + 8;
        return (((long)this.buffer[v + 7]) & 0xFFL) << 56 | (((long)this.buffer[v]) & 0xFFL | (((long)this.buffer[v + 1]) & 0xFFL) << 8 | (((long)this.buffer[v + 2]) & 0xFFL) << 16 | (((long)this.buffer[v + 3]) & 0xFFL) << 24 | (((long)this.buffer[v + 4]) & 0xFFL) << 0x20 | (((long)this.buffer[v + 5]) & 0xFFL) << 40 | (((long)this.buffer[v + 6]) & 0xFFL) << 0x30);
    }

    private final void zzbbx() {
        this.limit += this.zzhos;
        int v = this.limit;
        int v1 = v - this.zzhot;
        int v2 = this.zzhov;
        if(v1 > v2) {
            this.zzhos = v1 - v2;
            this.limit = v - this.zzhos;
            return;
        }
        this.zzhos = 0;
    }

    private final byte zzbby() throws IOException {
        int v = this.pos;
        if(v == this.limit) {
            throw zzdzh.zzbdi();
        }
        this.pos = v + 1;
        return this.buffer[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final void zzfh(int v) throws zzdzh {
        if(this.zzhou != v) {
            throw zzdzh.zzbdm();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final boolean zzfi(int v) throws IOException {
        int v1 = 0;
        switch(v & 7) {
            case 0: {
                if(this.limit - this.pos >= 10) {
                    while(v1 < 10) {
                        int v2 = this.pos;
                        this.pos = v2 + 1;
                        if(this.buffer[v2] >= 0) {
                            return true;
                        }
                        ++v1;
                    }
                    throw zzdzh.zzbdk();
                }
                else {
                    while(true) {
                        if(v1 >= 10) {
                            throw zzdzh.zzbdk();
                        }
                        if(this.zzbby() >= 0) {
                            break;
                        }
                        ++v1;
                    }
                }
                return true;
            }
            case 1: {
                this.zzfm(8);
                return true;
            }
            case 2: {
                this.zzfm(this.zzbbt());
                return true;
            }
            case 3: {
                do {
                    int v3 = this.zzbbb();
                }
                while(v3 != 0 && this.zzfi(v3));
                this.zzfh(v >>> 3 << 3 | 4);
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.zzfm(4);
                return true;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzfj(int v) throws zzdzh {
        if(v < 0) {
            throw zzdzh.zzbdj();
        }
        int v1 = v + this.zzbbs();
        int v2 = this.zzhov;
        if(v1 > v2) {
            throw zzdzh.zzbdi();
        }
        this.zzhov = v1;
        this.zzbbx();
        return v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final void zzfk(int v) {
        this.zzhov = v;
        this.zzbbx();
    }

    private final void zzfm(int v) throws IOException {
        if(v >= 0) {
            int v1 = this.pos;
            if(v <= this.limit - v1) {
                this.pos = v1 + v;
                return;
            }
        }
        throw v >= 0 ? zzdzh.zzbdi() : zzdzh.zzbdj();
    }
}

