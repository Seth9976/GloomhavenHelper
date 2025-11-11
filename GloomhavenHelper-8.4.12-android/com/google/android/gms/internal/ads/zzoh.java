package com.google.android.gms.internal.ads;

import android.util.Log;
import java.nio.ByteBuffer;

public final class zzoh {
    public static final byte[] zzbgi;
    private static final float[] zzbgm;
    private static final Object zzbgn;
    private static int[] zzbgo;

    static {
        zzoh.zzbgi = new byte[]{0, 0, 0, 1};
        zzoh.zzbgm = new float[]{1.0f, 1.0f, 1.090909f, 0.909091f, 1.454545f, 1.212121f, 2.181818f, 1.818182f, 2.909091f, 2.424242f, 1.636364f, 1.363636f, 1.939394f, 1.616162f, 1.333333f, 1.5f, 2.0f};
        zzoh.zzbgn = new Object();
        zzoh.zzbgo = new int[10];
    }

    public static zzok zzd(byte[] arr_b, int v, int v1) {
        int v28;
        int v27;
        int v26;
        int v25;
        boolean z2;
        int v13;
        boolean z;
        int v5;
        zzol zzol0 = new zzol(arr_b, v, v1);
        zzol0.zzbe(8);
        int v2 = zzol0.zzbd(8);
        zzol0.zzbe(16);
        int v3 = zzol0.zzit();
        int v4 = 1;
        switch(v2) {
            case 44: 
            case 83: 
            case 86: 
            case 100: 
            case 110: 
            case 0x76: 
            case 0x7A: 
            case 0x80: 
            case 0x8A: 
            case 0xF4: {
                v5 = zzol0.zzit();
                boolean z1 = v5 == 3 ? zzol0.zzis() : false;
                zzol0.zzit();
                zzol0.zzit();
                zzol0.zzbe(1);
                if(zzol0.zzis()) {
                    for(int v6 = 0; v6 < (v5 == 3 ? 12 : 8); ++v6) {
                        if(zzol0.zzis()) {
                            int v7 = v6 >= 6 ? 0x40 : 16;
                            int v9 = 8;
                            int v10 = 8;
                            for(int v8 = 0; v8 < v7; ++v8) {
                                if(v9 != 0) {
                                    v9 = (zzol0.zziu() + v10 + 0x100) % 0x100;
                                }
                                if(v9 != 0) {
                                    v10 = v9;
                                }
                            }
                        }
                    }
                }
                z = z1;
                break;
            }
            default: {
                v5 = 1;
                z = false;
            }
        }
        int v11 = zzol0.zzit();
        int v12 = zzol0.zzit();
        if(v12 == 0) {
            v13 = zzol0.zzit() + 4;
            z2 = false;
        }
        else if(v12 == 1) {
            boolean z3 = zzol0.zzis();
            zzol0.zziu();
            zzol0.zziu();
            long v14 = (long)zzol0.zzit();
            for(int v15 = 0; ((long)v15) < v14; ++v15) {
                zzol0.zzit();
            }
            z2 = z3;
            v13 = 0;
        }
        else {
            v13 = 0;
            z2 = false;
        }
        zzol0.zzit();
        zzol0.zzbe(1);
        int v16 = zzol0.zzit();
        int v17 = zzol0.zzit();
        int v18 = zzol0.zzis();
        if(v18 == 0) {
            zzol0.zzbe(1);
        }
        zzol0.zzbe(1);
        int v19 = v16 + 1 << 4;
        int v20 = (2 - v18) * (v17 + 1) << 4;
        if(zzol0.zzis()) {
            int v21 = zzol0.zzit();
            int v22 = zzol0.zzit();
            int v23 = zzol0.zzit();
            int v24 = zzol0.zzit();
            if(v5 == 0) {
                v25 = 2 - v18;
                v26 = 1;
            }
            else {
                v26 = v5 == 3 ? 1 : 2;
                if(v5 == 1) {
                    v4 = 2;
                }
                v25 = (2 - v18) * v4;
            }
            v27 = v19 - (v21 + v22) * v26;
            v28 = v20 - (v23 + v24) * v25;
        }
        else {
            v27 = v19;
            v28 = v20;
        }
        float f = 1.0f;
        if(zzol0.zzis() && zzol0.zzis()) {
            int v29 = zzol0.zzbd(8);
            if(v29 == 0xFF) {
                int v30 = zzol0.zzbd(16);
                int v31 = zzol0.zzbd(16);
                if(v30 != 0 && v31 != 0) {
                    f = ((float)v30) / ((float)v31);
                }
                return new zzok(v3, v27, v28, f, z, ((boolean)v18), v11 + 4, v12, v13, z2);
            }
            float[] arr_f = zzoh.zzbgm;
            if(v29 < arr_f.length) {
                return new zzok(v3, v27, v28, arr_f[v29], z, ((boolean)v18), v11 + 4, v12, v13, z2);
            }
            Log.w("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + v29);
        }
        return new zzok(v3, v27, v28, 1.0f, z, ((boolean)v18), v11 + 4, v12, v13, z2);
    }

    public static void zzk(ByteBuffer byteBuffer0) {
        int v = byteBuffer0.position();
        int v2 = 0;
        for(int v1 = 0; v1 + 1 < v; ++v1) {
            int v3 = byteBuffer0.get(v1) & 0xFF;
            if(v2 == 3) {
                if(v3 == 1 && (byteBuffer0.get(v1 + 1) & 0x1F) == 7) {
                    ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
                    byteBuffer1.position(v1 - 3);
                    byteBuffer1.limit(v);
                    byteBuffer0.position(0);
                    byteBuffer0.put(byteBuffer1);
                    return;
                }
            }
            else if(v3 == 0) {
                ++v2;
            }
            if(v3 != 0) {
                v2 = 0;
            }
        }
        byteBuffer0.clear();
    }
}

