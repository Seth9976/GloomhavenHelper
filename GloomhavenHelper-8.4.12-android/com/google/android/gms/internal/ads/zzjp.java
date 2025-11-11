package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Stack;

final class zzjp implements zzju {
    private final byte[] zzank;
    private final Stack zzanl;
    private final zzjz zzanm;
    private zzjt zzann;
    private int zzano;
    private int zzanp;
    private long zzanq;

    zzjp() {
        this.zzank = new byte[8];
        this.zzanl = new Stack();
        this.zzanm = new zzjz();
    }

    @Override  // com.google.android.gms.internal.ads.zzju
    public final void reset() {
        this.zzano = 0;
        this.zzanl.clear();
        this.zzanm.reset();
    }

    private final long zza(zzjf zzjf0, int v) throws IOException, InterruptedException {
        zzjf0.readFully(this.zzank, 0, v);
        long v2 = 0L;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 = v2 << 8 | ((long)(this.zzank[v1] & 0xFF));
        }
        return v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzju
    public final void zza(zzjt zzjt0) {
        this.zzann = zzjt0;
    }

    @Override  // com.google.android.gms.internal.ads.zzju
    public final boolean zzb(zzjf zzjf0) throws IOException, InterruptedException {
        String s;
        int v2;
        int v1;
        zzob.checkState(this.zzann != null);
        while(true) {
            if(!this.zzanl.isEmpty() && zzjf0.getPosition() >= ((zzjr)this.zzanl.peek()).zzant) {
                this.zzann.zzaj(((zzjr)this.zzanl.pop()).zzanp);
                return true;
            }
            if(this.zzano == 0) {
                long v = this.zzanm.zza(zzjf0, true, false, 4);
                if(v == -2L) {
                    zzjf0.zzgm();
                    while(true) {
                        zzjf0.zza(this.zzank, 0, 4);
                        v1 = zzjz.zzak(this.zzank[0]);
                        if(v1 != -1 && v1 <= 4) {
                            v2 = (int)zzjz.zza(this.zzank, v1, false);
                            if(!this.zzann.zzai(v2)) {
                                goto label_13;
                            }
                            break;
                        }
                    label_13:
                        zzjf0.zzac(1);
                    }
                    zzjf0.zzac(v1);
                    v = (long)v2;
                }
                if(v == -1L) {
                    return false;
                }
                this.zzanp = (int)v;
                this.zzano = 1;
            }
            if(this.zzano == 1) {
                this.zzanq = this.zzanm.zza(zzjf0, false, true, 8);
                this.zzano = 2;
            }
            int v3 = this.zzann.zzah(this.zzanp);
            switch(v3) {
                case 0: {
                    zzjf0.zzac(((int)this.zzanq));
                    this.zzano = 0;
                    break;
                }
                case 1: {
                    long v4 = zzjf0.getPosition();
                    zzjr zzjr0 = new zzjr(this.zzanp, this.zzanq + v4, null);
                    this.zzanl.add(zzjr0);
                    this.zzann.zzd(this.zzanp, v4, this.zzanq);
                    this.zzano = 0;
                    return true;
                }
                case 2: {
                    long v5 = this.zzanq;
                    if(v5 > 8L) {
                        throw new zzhc("Invalid integer size: " + v5);
                    }
                    this.zzann.zzc(this.zzanp, this.zza(zzjf0, ((int)v5)));
                    this.zzano = 0;
                    return true;
                }
                case 3: {
                    long v6 = this.zzanq;
                    if(v6 > 0x7FFFFFFFL) {
                        throw new zzhc("String element size: " + v6);
                    }
                    zzjt zzjt0 = this.zzann;
                    int v7 = this.zzanp;
                    if(((int)v6) == 0) {
                        s = "";
                    }
                    else {
                        byte[] arr_b = new byte[((int)v6)];
                        zzjf0.readFully(arr_b, 0, ((int)v6));
                        s = new String(arr_b);
                    }
                    zzjt0.zza(v7, s);
                    this.zzano = 0;
                    return true;
                }
                case 4: {
                    this.zzann.zza(this.zzanp, ((int)this.zzanq), zzjf0);
                    this.zzano = 0;
                    return true;
                }
                case 5: {
                    long v8 = this.zzanq;
                    if(v8 != 4L && v8 != 8L) {
                        throw new zzhc("Invalid float size: " + v8);
                    }
                    zzjt zzjt1 = this.zzann;
                    int v9 = this.zzanp;
                    int v10 = (int)this.zzanq;
                    long v11 = this.zza(zzjf0, v10);
                    zzjt1.zza(v9, (v10 == 4 ? ((double)Float.intBitsToFloat(((int)v11))) : Double.longBitsToDouble(v11)));
                    this.zzano = 0;
                    return true;
                }
                default: {
                    throw new zzhc("Invalid element type " + v3);
                }
            }
        }
    }
}

