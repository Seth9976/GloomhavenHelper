package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzor {
    private final int height;
    private final int width;
    public final List zzafp;
    public final int zzara;
    public final float zzbgt;

    private zzor(List list0, int v, int v1, int v2, float f) {
        this.zzafp = list0;
        this.zzara = v;
        this.width = v1;
        this.height = v2;
        this.zzbgt = f;
    }

    public static zzor zzf(zzom zzom0) throws zzhc {
        int v6;
        int v5;
        float f;
        try {
            zzom0.zzbi(4);
            int v = (zzom0.readUnsignedByte() & 3) + 1;
            if(v == 3) {
                throw new IllegalStateException();
            }
            ArrayList arrayList0 = new ArrayList();
            int v1 = zzom0.readUnsignedByte();
            for(int v2 = 0; v2 < (v1 & 0x1F); ++v2) {
                arrayList0.add(zzor.zzg(zzom0));
            }
            int v3 = zzom0.readUnsignedByte();
            for(int v4 = 0; v4 < v3; ++v4) {
                arrayList0.add(zzor.zzg(zzom0));
            }
            if((v1 & 0x1F) > 0) {
                byte[] arr_b = (byte[])arrayList0.get(0);
                zzok zzok0 = zzoh.zzd(((byte[])arrayList0.get(0)), v, arr_b.length);
                f = zzok0.zzbgt;
                v5 = zzok0.width;
                v6 = zzok0.height;
            }
            else {
                v5 = -1;
                v6 = -1;
                f = 1.0f;
            }
            return new zzor(arrayList0, v, v5, v6, f);
        }
        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
            throw new zzhc("Error parsing AVC config", arrayIndexOutOfBoundsException0);
        }
    }

    private static byte[] zzg(zzom zzom0) {
        int v = zzom0.readUnsignedShort();
        zzom0.zzbi(v);
        return zzoe.zzc(zzom0.data, zzom0.getPosition(), v);
    }
}

