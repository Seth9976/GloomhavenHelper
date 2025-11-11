package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzks {
    private static final int[] zzaxr;

    static {
        zzks.zzaxr = new int[]{zzop.zzbo("isom"), zzop.zzbo("iso2"), zzop.zzbo("iso3"), zzop.zzbo("iso4"), zzop.zzbo("iso5"), zzop.zzbo("iso6"), zzop.zzbo("avc1"), zzop.zzbo("hvc1"), zzop.zzbo("hev1"), zzop.zzbo("mp41"), zzop.zzbo("mp42"), zzop.zzbo("3g2a"), zzop.zzbo("3g2b"), zzop.zzbo("3gr6"), zzop.zzbo("3gs6"), zzop.zzbo("3ge6"), zzop.zzbo("3gg6"), zzop.zzbo("M4V "), zzop.zzbo("M4A "), zzop.zzbo("f4v "), zzop.zzbo("kddi"), zzop.zzbo("M4VP"), zzop.zzbo("qt  "), zzop.zzbo("MSNV")};
    }

    // This method was un-flattened
    public static boolean zzd(zzjf zzjf0) throws IOException, InterruptedException {
        long v = zzjf0.getLength();
        if(v == -1L || v > 0x1000L) {
            v = 0x1000L;
        }
        zzom zzom0 = new zzom(0x40);
        int v1 = 0;
        boolean z = false;
        while(v1 < ((int)v)) {
            zzom0.reset(8);
            zzjf0.zza(zzom0.data, 0, 8);
            long v2 = zzom0.zziz();
            int v3 = zzom0.readInt();
            int v4 = 16;
            if(v2 == 1L) {
                zzjf0.zza(zzom0.data, 8, 8);
                zzom0.zzbg(16);
                v2 = zzom0.zzjd();
            }
            else {
                v4 = 8;
            }
            if(v2 >= ((long)v4)) {
                v1 += v4;
                if(v3 == zzkc.zzasi) {
                    continue;
                }
                if(v3 == zzkc.zzasr || v3 == zzkc.zzast) {
                    return false;
                }
                if(((long)v1) + v2 - ((long)v4) >= ((long)(((int)v)))) {
                    break;
                }
                int v5 = (int)(v2 - ((long)v4));
                v1 += v5;
                if(v3 != zzkc.zzarh) {
                    goto label_50;
                }
                if(v5 < 8) {
                    return false;
                }
                zzom0.reset(v5);
                zzjf0.zza(zzom0.data, 0, v5);
                int v6 = v5 / 4;
                int v7 = 0;
                while(v7 < v6) {
                    if(v7 == 1) {
                        zzom0.zzbi(4);
                        v7 = 2;
                    }
                    else {
                        int v8 = zzom0.readInt();
                        if(v8 >>> 8 == zzop.zzbo("3gp")) {
                        label_46:
                            z = true;
                            if(true) {
                                break;
                            }
                        }
                        else {
                            int[] arr_v = zzks.zzaxr;
                            int v9 = 0;
                            while(v9 < arr_v.length) {
                                if(arr_v[v9] == v8) {
                                    goto label_46;
                                }
                                ++v9;
                            }
                            ++v7;
                        }
                    }
                }
                if(z) {
                    continue;
                label_50:
                    if(v5 == 0) {
                        continue;
                    }
                    zzjf0.zzad(v5);
                    continue;
                }
            }
            return false;
        }
        return z;
    }
}

