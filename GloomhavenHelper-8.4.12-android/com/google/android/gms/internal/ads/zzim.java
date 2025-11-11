package com.google.android.gms.internal.ads;

import java.nio.ShortBuffer;
import java.util.Arrays;

final class zzim {
    private final int zzafy;
    private float zzagk;
    private float zzagl;
    private final int zzalg;
    private final int zzalh;
    private final int zzali;
    private final int zzalj;
    private final short[] zzalk;
    private int zzall;
    private short[] zzalm;
    private int zzaln;
    private short[] zzalo;
    private int zzalp;
    private short[] zzalq;
    private int zzalr;
    private int zzals;
    private int zzalt;
    private int zzalu;
    private int zzalv;
    private int zzalw;
    private int zzalx;
    private int zzaly;
    private int zzalz;
    private int zzama;

    public zzim(int v, int v1) {
        this.zzafy = v;
        this.zzalg = v1;
        this.zzalh = v / 400;
        this.zzali = v / 65;
        this.zzalj = this.zzali * 2;
        this.zzalk = new short[this.zzalj];
        this.zzall = this.zzalj;
        this.zzalm = new short[this.zzalj * v1];
        this.zzaln = this.zzalj;
        this.zzalo = new short[this.zzalj * v1];
        this.zzalp = this.zzalj;
        this.zzalq = new short[this.zzalj * v1];
        this.zzalr = 0;
        this.zzals = 0;
        this.zzalx = 0;
        this.zzagk = 1.0f;
        this.zzagl = 1.0f;
    }

    public final void setSpeed(float f) {
        this.zzagk = f;
    }

    private final int zza(short[] arr_v, int v, int v1, int v2) {
        int v3 = v * this.zzalg;
        int v4 = 1;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0xFF;
        while(v1 <= v2) {
            int v9 = 0;
            for(int v8 = 0; v8 < v1; ++v8) {
                int v10 = arr_v[v3 + v8];
                int v11 = arr_v[v3 + v1 + v8];
                v9 += (v10 < v11 ? v11 - v10 : v10 - v11);
            }
            if(v9 * v5 < v4 * v1) {
                v5 = v1;
                v4 = v9;
            }
            if(v9 * v7 > v6 * v1) {
                v7 = v1;
                v6 = v9;
            }
            ++v1;
        }
        this.zzalz = v4 / v5;
        this.zzama = v6 / v7;
        return v5;
    }

    private static void zza(int v, int v1, short[] arr_v, int v2, short[] arr_v1, int v3, short[] arr_v2, int v4) {
        for(int v5 = 0; v5 < v1; ++v5) {
            int v6 = v3 * v1 + v5;
            int v7 = v4 * v1 + v5;
            int v8 = v2 * v1 + v5;
            for(int v9 = 0; v9 < v; ++v9) {
                arr_v[v8] = (short)((arr_v1[v6] * (v - v9) + arr_v2[v7] * v9) / v);
                v8 += v1;
                v6 += v1;
                v7 += v1;
            }
        }
    }

    private final void zza(short[] arr_v, int v, int v1) {
        this.zzu(v1);
        System.arraycopy(arr_v, v * this.zzalg, this.zzalo, this.zzalu * this.zzalg, this.zzalg * v1);
        this.zzalu += v1;
    }

    public final void zza(float f) {
        this.zzagl = f;
    }

    public final void zza(ShortBuffer shortBuffer0) {
        int v = shortBuffer0.remaining() / this.zzalg;
        this.zzv(v);
        shortBuffer0.get(this.zzalm, this.zzalt * this.zzalg, (this.zzalg * v << 1) / 2);
        this.zzalt += v;
        this.zzgb();
    }

    private final void zzb(short[] arr_v, int v, int v1) {
        int v2 = this.zzalj / v1;
        int v3 = v1 * this.zzalg;
        int v4 = v * this.zzalg;
        for(int v5 = 0; v5 < v2; ++v5) {
            int v7 = 0;
            for(int v6 = 0; v6 < v3; ++v6) {
                v7 += arr_v[v5 * v3 + v4 + v6];
            }
            this.zzalk[v5] = (short)(v7 / v3);
        }
    }

    public final void zzb(ShortBuffer shortBuffer0) {
        int v = Math.min(shortBuffer0.remaining() / this.zzalg, this.zzalu);
        shortBuffer0.put(this.zzalo, 0, this.zzalg * v);
        this.zzalu -= v;
        System.arraycopy(this.zzalo, v * this.zzalg, this.zzalo, 0, this.zzalu * this.zzalg);
    }

    public final void zzfg() {
        int v3;
        int v = this.zzalt;
        int v1 = this.zzalu + ((int)((((float)v) / (this.zzagk / this.zzagl) + ((float)this.zzalv)) / this.zzagl + 0.5f));
        this.zzv(this.zzalj * 2 + v);
        for(int v2 = 0; true; ++v2) {
            v3 = this.zzalj;
            int v4 = this.zzalg;
            if(v2 >= v3 * 2 * v4) {
                break;
            }
            this.zzalm[v4 * v + v2] = 0;
        }
        this.zzalt += v3 * 2;
        this.zzgb();
        if(this.zzalu > v1) {
            this.zzalu = v1;
        }
        this.zzalt = 0;
        this.zzalw = 0;
        this.zzalv = 0;
    }

    public final int zzga() {
        return this.zzalu;
    }

    // This method was un-flattened
    private final void zzgb() {
        int v25;
        int v24;
        int v23;
        int v15;
        int v14;
        int v6;
        int v = this.zzalu;
        float f = this.zzagk / this.zzagl;
        if(((double)f) > 1.00001 || ((double)f) < 0.99999) {
            int v1 = this.zzalt;
            if(v1 >= this.zzalj) {
                int v2 = 0;
                do {
                    int v3 = this.zzalw;
                    if(v3 > 0) {
                        int v4 = Math.min(this.zzalj, v3);
                        this.zza(this.zzalm, v2, v4);
                        this.zzalw -= v4;
                        v2 += v4;
                    }
                    else {
                        short[] arr_v = this.zzalm;
                        int v5 = this.zzafy <= 4000 ? 1 : this.zzafy / 4000;
                        if(this.zzalg != 1 || v5 != 1) {
                            this.zzb(arr_v, v2, v5);
                            int v7 = this.zza(this.zzalk, 0, this.zzalh / v5, this.zzali / v5);
                            if(v5 == 1) {
                                v6 = v7;
                            }
                            else {
                                int v8 = v7 * v5;
                                int v9 = v8 - (v5 << 2);
                                int v10 = v8 + (v5 << 2);
                                int v11 = v9 < this.zzalh ? this.zzalh : v9;
                                int v12 = this.zzali;
                                if(v10 > v12) {
                                    v10 = v12;
                                }
                                if(this.zzalg == 1) {
                                    v6 = this.zza(arr_v, v2, v11, v10);
                                }
                                else {
                                    this.zzb(arr_v, v2, 1);
                                    v6 = this.zza(this.zzalk, 0, v11, v10);
                                }
                            }
                        }
                        else {
                            v6 = this.zza(arr_v, v2, this.zzalh, this.zzali);
                        }
                        int v13 = this.zzalz == 0 || this.zzalx == 0 || this.zzama > this.zzalz * 3 || this.zzalz << 1 <= this.zzaly * 3 ? v6 : this.zzalx;
                        this.zzaly = this.zzalz;
                        this.zzalx = v6;
                        if(((double)f) > 1.0) {
                            short[] arr_v1 = this.zzalm;
                            if(f >= 2.0f) {
                                v14 = (int)(((float)v13) / (f - 1.0f));
                            }
                            else {
                                this.zzalw = (int)(((float)v13) * (2.0f - f) / (f - 1.0f));
                                v14 = v13;
                            }
                            this.zzu(v14);
                            zzim.zza(v14, this.zzalg, this.zzalo, this.zzalu, arr_v1, v2, arr_v1, v2 + v13);
                            this.zzalu += v14;
                            v2 += v13 + v14;
                        }
                        else {
                            short[] arr_v2 = this.zzalm;
                            if(f < 0.5f) {
                                v15 = (int)(((float)v13) * f / (1.0f - f));
                            }
                            else {
                                this.zzalw = (int)(((float)v13) * (2.0f * f - 1.0f) / (1.0f - f));
                                v15 = v13;
                            }
                            int v16 = v13 + v15;
                            this.zzu(v16);
                            System.arraycopy(arr_v2, v2 * this.zzalg, this.zzalo, this.zzalu * this.zzalg, this.zzalg * v13);
                            zzim.zza(v15, this.zzalg, this.zzalo, this.zzalu + v13, arr_v2, v13 + v2, arr_v2, v2);
                            this.zzalu += v16;
                            v2 += v15;
                        }
                    }
                }
                while(this.zzalj + v2 <= v1);
                int v17 = this.zzalt - v2;
                System.arraycopy(this.zzalm, v2 * this.zzalg, this.zzalm, 0, this.zzalg * v17);
                this.zzalt = v17;
            }
        }
        else {
            this.zza(this.zzalm, 0, this.zzalt);
            this.zzalt = 0;
        }
        float f1 = this.zzagl;
        if(f1 != 1.0f && this.zzalu != v) {
            int v18 = this.zzafy;
            int v19 = (int)(((float)v18) / f1);
            while(v19 > 0x4000 || v18 > 0x4000) {
                v19 /= 2;
                v18 /= 2;
            }
            int v20 = this.zzalu - v;
            int v21 = this.zzalp;
            if(this.zzalv + v20 > v21) {
                this.zzalp = v21 + (v21 / 2 + v20);
                this.zzalq = Arrays.copyOf(this.zzalq, this.zzalp * this.zzalg);
            }
            System.arraycopy(this.zzalo, v * this.zzalg, this.zzalq, this.zzalv * this.zzalg, this.zzalg * v20);
            this.zzalu = v;
            this.zzalv += v20;
            for(int v22 = 0; true; ++v22) {
                v23 = this.zzalv;
                if(v22 >= v23 - 1) {
                    break;
                }
                while(true) {
                    v24 = this.zzalr;
                    v25 = this.zzals;
                    if((v24 + 1) * v19 <= v25 * v18) {
                        break;
                    }
                    this.zzu(1);
                    for(int v26 = 0; true; ++v26) {
                        int v27 = this.zzalg;
                        if(v26 >= v27) {
                            break;
                        }
                        int v28 = v22 * v27 + v26;
                        int v29 = (this.zzalr + 1) * v19;
                        int v30 = v29 - this.zzals * v18;
                        int v31 = v29 - this.zzalr * v19;
                        this.zzalo[this.zzalu * v27 + v26] = (short)((this.zzalq[v28] * v30 + (v31 - v30) * this.zzalq[v28 + v27]) / v31);
                    }
                    ++this.zzals;
                    ++this.zzalu;
                }
                this.zzalr = v24 + 1;
                if(this.zzalr == v18) {
                    this.zzalr = 0;
                    zzob.checkState(v25 == v19);
                    this.zzals = 0;
                }
            }
            if(v23 - 1 != 0) {
                System.arraycopy(this.zzalq, (v23 - 1) * this.zzalg, this.zzalq, 0, this.zzalg);
                this.zzalv -= v23 - 1;
            }
        }
    }

    private final void zzu(int v) {
        int v1 = this.zzaln;
        if(this.zzalu + v > v1) {
            this.zzaln = v1 + (v1 / 2 + v);
            this.zzalo = Arrays.copyOf(this.zzalo, this.zzaln * this.zzalg);
        }
    }

    private final void zzv(int v) {
        int v1 = this.zzall;
        if(this.zzalt + v > v1) {
            this.zzall = v1 + (v1 / 2 + v);
            this.zzalm = Arrays.copyOf(this.zzalm, this.zzall * this.zzalg);
        }
    }
}

