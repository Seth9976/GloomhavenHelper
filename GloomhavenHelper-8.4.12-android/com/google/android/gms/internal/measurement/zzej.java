package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

final class zzej extends zzeh {
    private final byte[] zzd;
    private final boolean zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    private zzej(byte[] arr_b, int v, int v1, boolean z) {
        super(null);
        this.zzk = 0x7FFFFFFF;
        this.zzd = arr_b;
        this.zzf = v1 + v;
        this.zzh = v;
        this.zzi = this.zzh;
        this.zze = z;
    }

    zzej(byte[] arr_b, int v, int v1, boolean z, zzeg zzeg0) {
        this(arr_b, v, v1, z);
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zza() throws IOException {
        if(this.zzt()) {
            this.zzj = 0;
            return 0;
        }
        this.zzj = this.zzv();
        int v = this.zzj;
        if(v >>> 3 == 0) {
            throw zzfn.zzd();
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final void zza(int v) throws zzfn {
        if(this.zzj != v) {
            throw zzfn.zze();
        }
    }

    private final byte zzaa() throws IOException {
        int v = this.zzh;
        if(v == this.zzf) {
            throw zzfn.zza();
        }
        this.zzh = v + 1;
        return this.zzd[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(this.zzy());
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final boolean zzb(int v) throws IOException {
        int v1 = 0;
        switch(v & 7) {
            case 0: {
                if(this.zzf - this.zzh >= 10) {
                    while(v1 < 10) {
                        int v2 = this.zzh;
                        this.zzh = v2 + 1;
                        if(this.zzd[v2] >= 0) {
                            return true;
                        }
                        ++v1;
                    }
                    throw zzfn.zzc();
                }
                else {
                    while(true) {
                        if(v1 >= 10) {
                            throw zzfn.zzc();
                        }
                        if(this.zzaa() >= 0) {
                            break;
                        }
                        ++v1;
                    }
                }
                return true;
            }
            case 1: {
                this.zzf(8);
                return true;
            }
            case 2: {
                this.zzf(this.zzv());
                return true;
            }
            case 3: {
                do {
                    int v3 = this.zza();
                }
                while(v3 != 0 && this.zzb(v3));
                this.zza(v >>> 3 << 3 | 4);
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.zzf(4);
                return true;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(this.zzx());
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzc(int v) throws zzfn {
        if(v < 0) {
            throw zzfn.zzb();
        }
        int v1 = v + this.zzu();
        int v2 = this.zzk;
        if(v1 > v2) {
            throw zzfn.zza();
        }
        this.zzk = v1;
        this.zzz();
        return v2;
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final long zzd() throws IOException {
        return this.zzw();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final void zzd(int v) {
        this.zzk = v;
        this.zzz();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final long zze() throws IOException {
        return this.zzw();
    }

    private final void zzf(int v) throws IOException {
        if(v >= 0) {
            int v1 = this.zzh;
            if(v <= this.zzf - v1) {
                this.zzh = v1 + v;
                return;
            }
        }
        throw v >= 0 ? zzfn.zza() : zzfn.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzf() throws IOException {
        return this.zzv();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final long zzg() throws IOException {
        return this.zzy();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzh() throws IOException {
        return this.zzx();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final boolean zzi() throws IOException {
        return this.zzw() != 0L;
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final String zzj() throws IOException {
        int v = this.zzv();
        if(v > 0) {
            int v1 = this.zzh;
            if(v <= this.zzf - v1) {
                String s = new String(this.zzd, v1, v, zzfe.zza);
                this.zzh += v;
                return s;
            }
        }
        if(v != 0) {
            throw v >= 0 ? zzfn.zza() : zzfn.zzb();
        }
        return "";
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final String zzk() throws IOException {
        int v = this.zzv();
        if(v > 0) {
            int v1 = this.zzh;
            if(v <= this.zzf - v1) {
                String s = zzid.zzb(this.zzd, v1, v);
                this.zzh += v;
                return s;
            }
        }
        if(v != 0) {
            throw v > 0 ? zzfn.zza() : zzfn.zzb();
        }
        return "";
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final zzdv zzl() throws IOException {
        int v = this.zzv();
        if(v > 0) {
            int v1 = this.zzh;
            if(v <= this.zzf - v1) {
                zzdv zzdv0 = zzdv.zza(this.zzd, v1, v);
                this.zzh += v;
                return zzdv0;
            }
        }
        if(v == 0) {
            return zzdv.zza;
        }
        if(v > 0) {
            int v2 = this.zzh;
            if(v <= this.zzf - v2) {
                this.zzh = v + v2;
                return zzdv.zza(Arrays.copyOfRange(this.zzd, v2, this.zzh));
            }
        }
        throw v > 0 ? zzfn.zza() : zzfn.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzm() throws IOException {
        return this.zzv();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzn() throws IOException {
        return this.zzv();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzo() throws IOException {
        return this.zzx();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final long zzp() throws IOException {
        return this.zzy();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzq() throws IOException {
        return zzej.zze(this.zzv());
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final long zzr() throws IOException {
        return zzej.zza(this.zzw());
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    final long zzs() throws IOException {
        long v = 0L;
        for(int v1 = 0; v1 < 0x40; v1 += 7) {
            int v2 = this.zzaa();
            v |= ((long)(v2 & 0x7F)) << v1;
            if((v2 & 0x80) == 0) {
                return v;
            }
        }
        throw zzfn.zzc();
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final boolean zzt() throws IOException {
        return this.zzh == this.zzf;
    }

    @Override  // com.google.android.gms.internal.measurement.zzeh
    public final int zzu() {
        return this.zzh - this.zzi;
    }

    private final int zzv() throws IOException {
        int v5;
        int v = this.zzh;
        int v1 = this.zzf;
        if(v1 != v) {
            byte[] arr_b = this.zzd;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.zzh = v + 1;
                return v2;
            }
            if(v1 - (v + 1) >= 9) {
                int v3 = v + 2;
                int v4 = v2 ^ arr_b[v + 1] << 7;
                if(v4 < 0) {
                    v5 = v4 ^ 0xFFFFFF80;
                    this.zzh = v3;
                    return v5;
                }
                int v6 = v3 + 1;
                int v7 = v4 ^ arr_b[v3] << 14;
                if(v7 >= 0) {
                    v5 = v7 ^ 0x3F80;
                    v3 = v6;
                    this.zzh = v3;
                    return v5;
                }
                v3 = v6 + 1;
                int v8 = v7 ^ arr_b[v6] << 21;
                if(v8 < 0) {
                    v5 = v8 ^ 0xFFE03F80;
                    this.zzh = v3;
                    return v5;
                }
                int v9 = v3 + 1;
                int v10 = arr_b[v3];
                v5 = v8 ^ v10 << 28 ^ 0xFE03F80;
                if(v10 >= 0) {
                    v3 = v9;
                    this.zzh = v3;
                    return v5;
                }
                v3 = v9 + 1;
                if(arr_b[v9] >= 0) {
                    this.zzh = v3;
                    return v5;
                }
                v9 = v3 + 1;
                if(arr_b[v3] >= 0) {
                    v3 = v9;
                    this.zzh = v3;
                    return v5;
                }
                v3 = v9 + 1;
                if(arr_b[v9] >= 0) {
                    this.zzh = v3;
                    return v5;
                }
                v9 = v3 + 1;
                if(arr_b[v3] >= 0) {
                    v3 = v9;
                    this.zzh = v3;
                    return v5;
                }
                v3 = v9 + 1;
                if(arr_b[v9] >= 0) {
                    this.zzh = v3;
                    return v5;
                }
            }
        }
        return (int)this.zzs();
    }

    private final long zzw() throws IOException {
        long v5;
        int v = this.zzh;
        int v1 = this.zzf;
        if(v1 != v) {
            byte[] arr_b = this.zzd;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.zzh = v + 1;
                return (long)v2;
            }
            if(v1 - (v + 1) >= 9) {
                int v3 = v + 2;
                int v4 = v2 ^ arr_b[v + 1] << 7;
                if(v4 < 0) {
                    v5 = (long)(v4 ^ 0xFFFFFF80);
                    this.zzh = v3;
                    return v5;
                }
                int v6 = v3 + 1;
                int v7 = v4 ^ arr_b[v3] << 14;
                if(v7 >= 0) {
                    v3 = v6;
                    v5 = (long)(v7 ^ 0x3F80);
                    this.zzh = v3;
                    return v5;
                }
                v3 = v6 + 1;
                int v8 = v7 ^ arr_b[v6] << 21;
                if(v8 < 0) {
                    v5 = (long)(v8 ^ 0xFFE03F80);
                    this.zzh = v3;
                    return v5;
                }
                int v9 = v3 + 1;
                long v10 = ((long)v8) ^ ((long)arr_b[v3]) << 28;
                if(v10 >= 0L) {
                    v5 = 0xFE03F80L ^ v10;
                    v3 = v9;
                    this.zzh = v3;
                    return v5;
                }
                v3 = v9 + 1;
                long v11 = v10 ^ ((long)arr_b[v9]) << 35;
                if(v11 < 0L) {
                    v5 = v11 ^ 0xFFFFFFF80FE03F80L;
                    this.zzh = v3;
                    return v5;
                }
                int v12 = v3 + 1;
                long v13 = v11 ^ ((long)arr_b[v3]) << 42;
                if(v13 >= 0L) {
                    v5 = 0x3F80FE03F80L ^ v13;
                    v3 = v12;
                    this.zzh = v3;
                    return v5;
                }
                v3 = v12 + 1;
                long v14 = v13 ^ ((long)arr_b[v12]) << 49;
                if(v14 < 0L) {
                    v5 = v14 ^ 0xFFFE03F80FE03F80L;
                    this.zzh = v3;
                    return v5;
                }
                int v15 = v3 + 1;
                v5 = v14 ^ ((long)arr_b[v3]) << 56 ^ 0xFE03F80FE03F80L;
                if(v5 >= 0L) {
                    v3 = v15;
                    this.zzh = v3;
                    return v5;
                }
                v3 = v15 + 1;
                if(((long)arr_b[v15]) >= 0L) {
                    this.zzh = v3;
                    return v5;
                }
            }
        }
        return this.zzs();
    }

    private final int zzx() throws IOException {
        int v = this.zzh;
        if(this.zzf - v < 4) {
            throw zzfn.zza();
        }
        this.zzh = v + 4;
        return (this.zzd[v + 3] & 0xFF) << 24 | (this.zzd[v] & 0xFF | (this.zzd[v + 1] & 0xFF) << 8 | (this.zzd[v + 2] & 0xFF) << 16);
    }

    private final long zzy() throws IOException {
        int v = this.zzh;
        if(this.zzf - v < 8) {
            throw zzfn.zza();
        }
        this.zzh = v + 8;
        return (((long)this.zzd[v + 7]) & 0xFFL) << 56 | (((long)this.zzd[v]) & 0xFFL | (((long)this.zzd[v + 1]) & 0xFFL) << 8 | (((long)this.zzd[v + 2]) & 0xFFL) << 16 | (((long)this.zzd[v + 3]) & 0xFFL) << 24 | (((long)this.zzd[v + 4]) & 0xFFL) << 0x20 | (((long)this.zzd[v + 5]) & 0xFFL) << 40 | (((long)this.zzd[v + 6]) & 0xFFL) << 0x30);
    }

    private final void zzz() {
        this.zzf += this.zzg;
        int v = this.zzf;
        int v1 = v - this.zzi;
        int v2 = this.zzk;
        if(v1 > v2) {
            this.zzg = v1 - v2;
            this.zzf = v - this.zzg;
            return;
        }
        this.zzg = 0;
    }
}

