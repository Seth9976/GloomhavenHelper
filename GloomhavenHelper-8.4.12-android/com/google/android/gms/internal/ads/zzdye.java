package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

final class zzdye extends zzdxz {
    private final byte[] buffer;
    private int pos;
    private int zzaiq;
    private int zzhos;
    private int zzhou;
    private int zzhov;
    private final InputStream zzhow;
    private int zzhox;
    private zzdyd zzhoy;

    private zzdye(InputStream inputStream0, int v) {
        super(null);
        this.zzhov = 0x7FFFFFFF;
        this.zzhoy = null;
        zzdzc.zza(inputStream0, "input");
        this.zzhow = inputStream0;
        this.buffer = new byte[v];
        this.zzaiq = 0;
        this.pos = 0;
        this.zzhox = 0;
    }

    zzdye(InputStream inputStream0, int v, zzdyc zzdyc0) {
        this(inputStream0, 0x1000);
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
            if(v <= this.zzaiq - v1) {
                String s = new String(this.buffer, v1, v, zzdzc.UTF_8);
                this.pos += v;
                return s;
            }
        }
        if(v == 0) {
            return "";
        }
        if(v <= this.zzaiq) {
            this.zzfn(v);
            String s1 = new String(this.buffer, this.pos, v, zzdzc.UTF_8);
            this.pos += v;
            return s1;
        }
        return new String(this.zzg(v, false), zzdzc.UTF_8);
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
        int v1 = this.pos;
        if(v <= this.zzaiq - v1 && v > 0) {
            this.pos = v1 + v;
            return zzece.zzo(this.buffer, v1, v);
        }
        if(v == 0) {
            return "";
        }
        if(v <= this.zzaiq) {
            this.zzfn(v);
            this.pos = v;
            return zzece.zzo(this.buffer, 0, v);
        }
        return zzece.zzo(this.zzg(v, false), 0, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final zzdxn zzbbj() throws IOException {
        int v = this.zzbbt();
        int v1 = this.pos;
        if(v <= this.zzaiq - v1 && v > 0) {
            zzdxn zzdxn0 = zzdxn.zzh(this.buffer, v1, v);
            this.pos += v;
            return zzdxn0;
        }
        if(v == 0) {
            return zzdxn.zzhoe;
        }
        byte[] arr_b = this.zzfp(v);
        if(arr_b != null) {
            return zzdxn.zzt(arr_b);
        }
        int v2 = this.pos;
        int v3 = this.zzaiq - v2;
        this.zzhox += this.zzaiq;
        this.pos = 0;
        this.zzaiq = 0;
        List list0 = this.zzfq(v - v3);
        byte[] arr_b1 = new byte[v];
        System.arraycopy(this.buffer, v2, arr_b1, 0, v3);
        for(Object object0: list0) {
            System.arraycopy(((byte[])object0), 0, arr_b1, v3, ((byte[])object0).length);
            v3 += ((byte[])object0).length;
        }
        return zzdxn.zzu(arr_b1);
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
        return zzdye.zzfl(this.zzbbt());
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final long zzbbp() throws IOException {
        return zzdye.zzfg(this.zzbbu());
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
        return this.pos == this.zzaiq && !this.zzfo(1);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxz
    public final int zzbbs() {
        return this.zzhox + this.pos;
    }

    private final int zzbbt() throws IOException {
        int v5;
        int v = this.pos;
        int v1 = this.zzaiq;
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
        int v1 = this.zzaiq;
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
        if(this.zzaiq - v < 4) {
            this.zzfn(4);
            v = this.pos;
        }
        this.pos = v + 4;
        return (this.buffer[v + 3] & 0xFF) << 24 | (this.buffer[v] & 0xFF | (this.buffer[v + 1] & 0xFF) << 8 | (this.buffer[v + 2] & 0xFF) << 16);
    }

    private final long zzbbw() throws IOException {
        int v = this.pos;
        if(this.zzaiq - v < 8) {
            this.zzfn(8);
            v = this.pos;
        }
        this.pos = v + 8;
        return (((long)this.buffer[v + 7]) & 0xFFL) << 56 | (((long)this.buffer[v]) & 0xFFL | (((long)this.buffer[v + 1]) & 0xFFL) << 8 | (((long)this.buffer[v + 2]) & 0xFFL) << 16 | (((long)this.buffer[v + 3]) & 0xFFL) << 24 | (((long)this.buffer[v + 4]) & 0xFFL) << 0x20 | (((long)this.buffer[v + 5]) & 0xFFL) << 40 | (((long)this.buffer[v + 6]) & 0xFFL) << 0x30);
    }

    private final void zzbbx() {
        this.zzaiq += this.zzhos;
        int v = this.zzaiq;
        int v1 = this.zzhox + v;
        int v2 = this.zzhov;
        if(v1 > v2) {
            this.zzhos = v1 - v2;
            this.zzaiq = v - this.zzhos;
            return;
        }
        this.zzhos = 0;
    }

    private final byte zzbby() throws IOException {
        if(this.pos == this.zzaiq) {
            this.zzfn(1);
        }
        int v = this.pos;
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
                if(this.zzaiq - this.pos >= 10) {
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
        int v1 = v + (this.zzhox + this.pos);
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
        int v10;
        int v1 = this.pos;
        if(v <= this.zzaiq - v1 && v >= 0) {
            this.pos = v1 + v;
            return;
        }
        if(v < 0) {
            throw zzdzh.zzbdj();
        }
        int v2 = this.zzhox;
        int v3 = this.pos;
        int v4 = this.zzhov;
        if(v2 + v3 + v <= v4) {
            this.zzhox = v2 + v3;
            int v5 = this.zzaiq - v3;
            this.zzaiq = 0;
            this.pos = 0;
            try {
                while(v5 < v) {
                    long v7 = (long)(v - v5);
                    long v8 = this.zzhow.skip(v7);
                    if(v8 < 0L || v8 > v7) {
                        throw new IllegalStateException(this.zzhow.getClass() + "#skip returned invalid result: " + v8 + "\nThe InputStream implementation is buggy.");
                    }
                    if(v8 == 0L) {
                        break;
                    }
                    v5 += (int)v8;
                }
            }
            finally {
                this.zzhox += v5;
                this.zzbbx();
            }
            if(v5 < v) {
                int v9 = this.zzaiq - this.pos;
                this.pos = this.zzaiq;
                this.zzfn(1);
                while(true) {
                    v10 = v - v9;
                    int v11 = this.zzaiq;
                    if(v10 <= v11) {
                        break;
                    }
                    v9 += v11;
                    this.pos = v11;
                    this.zzfn(1);
                }
                this.pos = v10;
            }
            return;
        }
        this.zzfm(v4 - v2 - v3);
        throw zzdzh.zzbdi();
    }

    private final void zzfn(int v) throws IOException {
        if(!this.zzfo(v)) {
            throw v <= this.zzhoo - this.zzhox - this.pos ? zzdzh.zzbdi() : zzdzh.zzbdo();
        }
    }

    private final boolean zzfo(int v) throws IOException {
        while(this.pos + v > this.zzaiq) {
            int v1 = this.zzhox;
            int v2 = this.pos;
            if(v > this.zzhoo - v1 - v2) {
                return false;
            }
            if(v1 + v2 + v > this.zzhov) {
                return false;
            }
            if(v2 > 0) {
                int v3 = this.zzaiq;
                if(v3 > v2) {
                    System.arraycopy(this.buffer, v2, this.buffer, 0, v3 - v2);
                }
                this.zzhox += v2;
                this.zzaiq -= v2;
                this.pos = 0;
            }
            int v4 = this.zzhow.read(this.buffer, this.zzaiq, Math.min(this.buffer.length - this.zzaiq, this.zzhoo - this.zzhox - this.zzaiq));
            if(v4 == 0 || v4 < -1 || v4 > this.buffer.length) {
                throw new IllegalStateException(this.zzhow.getClass() + "#read(byte[]) returned invalid result: " + v4 + "\nThe InputStream implementation is buggy.");
            }
            if(v4 > 0) {
                this.zzaiq += v4;
                this.zzbbx();
                if(this.zzaiq < v) {
                    continue;
                }
                return true;
            }
            return false;
        }
        throw new IllegalStateException("refillBuffer() called when " + v + " bytes were already available in buffer");
    }

    private final byte[] zzfp(int v) throws IOException {
        if(v == 0) {
            return zzdzc.zzhtq;
        }
        if(v < 0) {
            throw zzdzh.zzbdj();
        }
        int v1 = this.zzhox + this.pos + v;
        if(v1 - this.zzhoo > 0) {
            throw zzdzh.zzbdo();
        }
        int v2 = this.zzhov;
        if(v1 <= v2) {
            int v3 = this.zzaiq - this.pos;
            if(v - v3 >= 0x1000 && v - v3 > this.zzhow.available()) {
                return null;
            }
            byte[] arr_b = new byte[v];
            System.arraycopy(this.buffer, this.pos, arr_b, 0, v3);
            this.zzhox += this.zzaiq;
            this.pos = 0;
            this.zzaiq = 0;
            while(v3 < arr_b.length) {
                int v4 = this.zzhow.read(arr_b, v3, v - v3);
                if(v4 == -1) {
                    throw zzdzh.zzbdi();
                }
                this.zzhox += v4;
                v3 += v4;
            }
            return arr_b;
        }
        this.zzfm(v2 - this.zzhox - this.pos);
        throw zzdzh.zzbdi();
    }

    private final List zzfq(int v) throws IOException {
        List list0 = new ArrayList();
        while(v > 0) {
            byte[] arr_b = new byte[Math.min(v, 0x1000)];
            for(int v1 = 0; v1 < arr_b.length; v1 += v2) {
                int v2 = this.zzhow.read(arr_b, v1, arr_b.length - v1);
                if(v2 == -1) {
                    throw zzdzh.zzbdi();
                }
                this.zzhox += v2;
            }
            v -= arr_b.length;
            list0.add(arr_b);
        }
        return list0;
    }

    private final byte[] zzg(int v, boolean z) throws IOException {
        byte[] arr_b = this.zzfp(v);
        if(arr_b != null) {
            return arr_b;
        }
        int v1 = this.pos;
        int v2 = this.zzaiq - v1;
        this.zzhox += this.zzaiq;
        this.pos = 0;
        this.zzaiq = 0;
        List list0 = this.zzfq(v - v2);
        byte[] arr_b1 = new byte[v];
        System.arraycopy(this.buffer, v1, arr_b1, 0, v2);
        for(Object object0: list0) {
            System.arraycopy(((byte[])object0), 0, arr_b1, v2, ((byte[])object0).length);
            v2 += ((byte[])object0).length;
        }
        return arr_b1;
    }
}

