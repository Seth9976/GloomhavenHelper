package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzka {
    private final zzom zzaod;
    private int zzard;

    public zzka() {
        this.zzaod = new zzom(8);
    }

    public final boolean zza(zzjf zzjf0) throws IOException, InterruptedException {
        long v6;
        int v5;
        long v = zzjf0.getLength();
        zzjf0.zza(this.zzaod.data, 0, 4);
        long v1 = this.zzaod.zziz();
        this.zzard = 4;
        while(v1 != 0x1A45DFA3L) {
            int v2 = this.zzard + 1;
            this.zzard = v2;
            if(v2 == ((int)(v == -1L || v > 0x400L ? 0x400L : v))) {
                return false;
            }
            zzjf0.zza(this.zzaod.data, 0, 1);
            v1 = v1 << 8 & 0xFFFFFFFFFFFFFF00L | ((long)(this.zzaod.data[0] & 0xFF));
        }
        long v3 = this.zzc(zzjf0);
        long v4 = (long)this.zzard;
        if(v3 != 0x8000000000000000L && (v == -1L || v4 + v3 < v)) {
            while(true) {
                v5 = this.zzard;
                v6 = v4 + v3;
                if(((long)v5) >= v6) {
                    break;
                }
                if(this.zzc(zzjf0) == 0x8000000000000000L) {
                    return false;
                }
                long v7 = this.zzc(zzjf0);
                if(v7 >= 0L && v7 <= 0x7FFFFFFFL) {
                    if(v7 == 0L) {
                        continue;
                    }
                    zzjf0.zzad(((int)v7));
                    this.zzard = (int)(((long)this.zzard) + v7);
                    continue;
                }
                return false;
            }
            return ((long)v5) == v6;
        }
        return false;
    }

    private final long zzc(zzjf zzjf0) throws IOException, InterruptedException {
        int v = 0;
        zzjf0.zza(this.zzaod.data, 0, 1);
        int v1 = this.zzaod.data[0] & 0xFF;
        if(v1 == 0) {
            return 0x8000000000000000L;
        }
        int v2 = 0x80;
        int v3;
        for(v3 = 0; (v1 & v2) == 0; ++v3) {
            v2 >>= 1;
        }
        int v4 = v1 & ~v2;
        zzjf0.zza(this.zzaod.data, 1, v3);
        while(v < v3) {
            ++v;
            v4 = (this.zzaod.data[v] & 0xFF) + (v4 << 8);
        }
        this.zzard += v3 + 1;
        return (long)v4;
    }
}

