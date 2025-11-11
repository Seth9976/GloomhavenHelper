package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.Arrays;
import java.util.Map;

public abstract class zznc extends zzni {
    private int zzago;
    private final SparseArray zzbei;
    private final SparseBooleanArray zzbej;
    private zznb zzbek;

    public zznc() {
        this.zzbei = new SparseArray();
        this.zzbej = new SparseBooleanArray();
        this.zzago = 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzni
    public final zznk zza(zzhh[] arr_zzhh, zzmu zzmu0) throws zzgk {
        int[] arr_v2;
        int[] arr_v = new int[arr_zzhh.length + 1];
        zzmr[][] arr2_zzmr = new zzmr[arr_zzhh.length + 1][];
        int[][][] arr3_v = new int[arr_zzhh.length + 1][][];
        for(int v = 0; v < arr2_zzmr.length; ++v) {
            arr2_zzmr[v] = new zzmr[zzmu0.length];
            arr3_v[v] = new int[zzmu0.length][];
        }
        int[] arr_v1 = new int[arr_zzhh.length];
        for(int v1 = 0; v1 < arr_v1.length; ++v1) {
            arr_v1[v1] = arr_zzhh[v1].zzeb();
        }
        for(int v2 = 0; v2 < zzmu0.length; ++v2) {
            zzmr zzmr0 = zzmu0.zzaw(v2);
            int v3 = arr_zzhh.length;
            int v4 = 0;
            int v5 = 0;
        alab1:
            while(true) {
                if(v4 >= arr_zzhh.length) {
                    v4 = v3;
                    break;
                }
                zzhh zzhh0 = arr_zzhh[v4];
                int v6 = v3;
                int v7 = v5;
                for(int v8 = 0; v8 < zzmr0.length; ++v8) {
                    int v9 = zzhh0.zza(zzmr0.zzav(v8)) & 3;
                    if(v9 > v7) {
                        if(v9 == 3) {
                            break alab1;
                        }
                        else {
                            v6 = v4;
                            v7 = v9;
                        }
                    }
                }
                ++v4;
                v5 = v7;
                v3 = v6;
            }
            if(v4 == arr_zzhh.length) {
                arr_v2 = new int[zzmr0.length];
            }
            else {
                zzhh zzhh1 = arr_zzhh[v4];
                int[] arr_v3 = new int[zzmr0.length];
                for(int v10 = 0; v10 < zzmr0.length; ++v10) {
                    arr_v3[v10] = zzhh1.zza(zzmr0.zzav(v10));
                }
                arr_v2 = arr_v3;
            }
            int v11 = arr_v[v4];
            arr2_zzmr[v4][v11] = zzmr0;
            arr3_v[v4][v11] = arr_v2;
            ++arr_v[v4];
        }
        zzmu[] arr_zzmu = new zzmu[arr_zzhh.length];
        int[] arr_v4 = new int[arr_zzhh.length];
        for(int v12 = 0; v12 < arr_zzhh.length; ++v12) {
            int v13 = arr_v[v12];
            arr_zzmu[v12] = new zzmu(((zzmr[])Arrays.copyOf(arr2_zzmr[v12], v13)));
            arr3_v[v12] = (int[][])Arrays.copyOf(arr3_v[v12], v13);
            arr_v4[v12] = arr_zzhh[v12].getTrackType();
        }
        zzmu zzmu1 = new zzmu(((zzmr[])Arrays.copyOf(arr2_zzmr[arr_zzhh.length], arr_v[arr_zzhh.length])));
        zznd[] arr_zznd = this.zza(arr_zzhh, arr_zzmu, arr3_v);
        for(int v14 = 0; true; ++v14) {
            zzne zzne0 = null;
            if(v14 >= arr_zzhh.length) {
                break;
            }
            if(this.zzbej.get(v14)) {
                arr_zznd[v14] = null;
            }
            else {
                zzmu zzmu2 = arr_zzmu[v14];
                Map map0 = (Map)this.zzbei.get(v14);
                if(map0 != null) {
                    zzne0 = (zzne)map0.get(zzmu2);
                }
                if(zzne0 != null) {
                    throw new NoSuchMethodError();
                }
                continue;
            }
        }
        zznb zznb0 = new zznb(arr_v4, arr_zzmu, arr_v1, arr3_v, zzmu1);
        zzhg[] arr_zzhg = new zzhg[arr_zzhh.length];
        for(int v15 = 0; v15 < arr_zzhh.length; ++v15) {
            arr_zzhg[v15] = arr_zznd[v15] == null ? null : zzhg.zzagn;
        }
        return new zznk(zzmu0, new zznf(arr_zznd), zznb0, arr_zzhg);
    }

    protected abstract zznd[] zza(zzhh[] arg1, zzmu[] arg2, int[][][] arg3) throws zzgk;

    @Override  // com.google.android.gms.internal.ads.zzni
    public final void zzd(Object object0) {
        this.zzbek = (zznb)object0;
    }

    public final void zzf(int v, boolean z) {
        if(this.zzbej.get(v) == z) {
            return;
        }
        this.zzbej.put(v, z);
        this.invalidate();
    }
}

