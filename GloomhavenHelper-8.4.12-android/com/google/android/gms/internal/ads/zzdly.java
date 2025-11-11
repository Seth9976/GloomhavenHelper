package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzdly extends zzdlq implements Set {
    @NullableDecl
    private transient zzdlr zzham;

    @Override
    public boolean equals(@NullableDecl Object object0) {
        if(object0 == this) {
            return true;
        }
        return !(object0 instanceof zzdly) || !this.zzauh() || !((zzdly)object0).zzauh() || this.hashCode() == object0.hashCode() ? zzdmk.zza(this, object0) : false;
    }

    @Override
    public int hashCode() {
        return zzdmk.zzg(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public Iterator iterator() {
        return this.zzatw();
    }

    private static zzdly zza(int v, Object[] arr_object) {
        int v5;
        int v4;
        int v2;
        Object[] arr_object1;
    alab1:
        while(true) {
            switch(v) {
                case 0: {
                    return zzdmh.zzhax;
                }
                case 1: {
                    return zzdly.zzah(arr_object[0]);
                label_4:
                    int v1 = zzdly.zzdx(v);
                    arr_object1 = new Object[v1];
                    v2 = v1 - 1;
                    v4 = 0;
                    v5 = 0;
                    for(int v3 = 0; v3 < v; ++v3) {
                        Object object0 = zzdmc.zzd(arr_object[v3], v3);
                        int v6 = object0.hashCode();
                        for(int v7 = zzdln.zzdv(v6); true; ++v7) {
                            int v8 = v7 & v2;
                            Object object1 = arr_object1[v8];
                            if(object1 == null) {
                                arr_object[v5] = object0;
                                arr_object1[v8] = object0;
                                v4 += v6;
                                ++v5;
                                break;
                            }
                            if(object1.equals(object0)) {
                                break;
                            }
                        }
                    }
                    Arrays.fill(arr_object, v5, v, null);
                    if(v5 == 1) {
                        return new zzdmj(arr_object[0], v4);
                    }
                    if(zzdly.zzdx(v5) < v1 / 2) {
                        break;
                    }
                    break alab1;
                }
                default: {
                    goto label_4;
                }
            }
            v = v5;
        }
        if(zzdly.zzv(v5, arr_object.length)) {
            arr_object = Arrays.copyOf(arr_object, v5);
        }
        return new zzdmh(arr_object, v4, arr_object1, v2, v5);
    }

    @SafeVarargs
    public static zzdly zza(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object[] arr_object) {
        zzdlg.checkArgument(arr_object.length <= 0x7FFFFFF9, "the total number of elements must fit in an int");
        Object[] arr_object1 = new Object[arr_object.length + 6];
        arr_object1[0] = object0;
        arr_object1[1] = object1;
        arr_object1[2] = object2;
        arr_object1[3] = object3;
        arr_object1[4] = object4;
        arr_object1[5] = object5;
        System.arraycopy(arr_object, 0, arr_object1, 6, arr_object.length);
        return zzdly.zza(arr_object1.length, arr_object1);
    }

    public static zzdly zzah(Object object0) {
        return new zzdmj(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public zzdlr zzaua() {
        zzdlr zzdlr0 = this.zzham;
        if(zzdlr0 == null) {
            zzdlr0 = this.zzaui();
            this.zzham = zzdlr0;
        }
        return zzdlr0;
    }

    boolean zzauh() {
        return false;
    }

    zzdlr zzaui() {
        return zzdlr.zzc(this.toArray());
    }

    static int zzdx(int v) {
        int v1 = Math.max(v, 2);
        boolean z = true;
        if(v1 < 0x2CCCCCCC) {
            int v2;
            for(v2 = Integer.highestOneBit(v1 - 1) << 1; ((double)v2) * 0.7 < ((double)v1); v2 <<= 1) {
            }
            return v2;
        }
        if(v1 >= 0x40000000) {
            z = false;
        }
        zzdlg.checkArgument(z, "collection too large");
        return 0x40000000;
    }

    public static zzdlx zzdy(int v) {
        zzdll.zze(v, "expectedSize");
        return new zzdlx(v);
    }

    private static boolean zzv(int v, int v1) {
        return v < (v1 >> 1) + (v1 >> 2);
    }
}

