package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

public final class zzox {
    public final List zzafp;
    public final int zzara;

    private zzox(List list0, int v) {
        this.zzafp = list0;
        this.zzara = v;
    }

    public static zzox zzh(zzom zzom0) throws zzhc {
        try {
            zzom0.zzbi(21);
            int v = zzom0.readUnsignedByte();
            int v1 = zzom0.readUnsignedByte();
            int v2 = zzom0.getPosition();
            int v3 = 0;
            int v4;
            for(v4 = 0; v3 < v1; v4 = v6) {
                zzom0.zzbi(1);
                int v5 = zzom0.readUnsignedShort();
                int v6 = v4;
                for(int v7 = 0; v7 < v5; ++v7) {
                    int v8 = zzom0.readUnsignedShort();
                    v6 += v8 + 4;
                    zzom0.zzbi(v8);
                }
                ++v3;
            }
            zzom0.zzbh(v2);
            byte[] arr_b = new byte[v4];
            int v9 = 0;
            for(int v10 = 0; v9 < v1; v10 = v12) {
                zzom0.zzbi(1);
                int v11 = zzom0.readUnsignedShort();
                int v12 = v10;
                for(int v13 = 0; v13 < v11; ++v13) {
                    int v14 = zzom0.readUnsignedShort();
                    System.arraycopy(zzoh.zzbgi, 0, arr_b, v12, zzoh.zzbgi.length);
                    System.arraycopy(zzom0.data, zzom0.getPosition(), arr_b, v12 + zzoh.zzbgi.length, v14);
                    v12 = v12 + zzoh.zzbgi.length + v14;
                    zzom0.zzbi(v14);
                }
                ++v9;
            }
            return new zzox((v4 == 0 ? null : Collections.singletonList(arr_b)), (v & 3) + 1);
        }
        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
            throw new zzhc("Error parsing HEVC config", arrayIndexOutOfBoundsException0);
        }
    }
}

