package com.google.android.gms.internal.ads;

import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdme extends zzdlv {
    private final transient int size;
    private final transient Object[] zzhas;
    private static final zzdlv zzhau;
    private final transient Object zzhav;

    static {
        zzdme.zzhau = new zzdme(null, new Object[0], 0);
    }

    private zzdme(Object object0, Object[] arr_object, int v) {
        this.zzhav = object0;
        this.zzhas = arr_object;
        this.size = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlv
    @NullableDecl
    public final Object get(@NullableDecl Object object0) {
        Object object1 = this.zzhav;
        Object[] arr_object = this.zzhas;
        int v = this.size;
        if(object0 == null) {
            return null;
        }
        if(v == 1) {
            return arr_object[0].equals(object0) ? arr_object[1] : null;
        }
        if(object1 == null) {
            return null;
        }
        if(object1 instanceof byte[]) {
            int v1 = ((byte[])object1).length - 1;
            for(int v2 = zzdln.zzdv(object0.hashCode()); true; v2 = v3 + 1) {
                int v3 = v2 & v1;
                int v4 = ((byte[])object1)[v3] & 0xFF;
                if(v4 == 0xFF) {
                    return null;
                }
                if(arr_object[v4].equals(object0)) {
                    return arr_object[v4 ^ 1];
                }
            }
        }
        if(object1 instanceof short[]) {
            int v5 = ((short[])object1).length - 1;
            for(int v6 = zzdln.zzdv(object0.hashCode()); true; v6 = v7 + 1) {
                int v7 = v6 & v5;
                int v8 = ((short[])object1)[v7] & 0xFFFF;
                if(v8 == 0xFFFF) {
                    return null;
                }
                if(arr_object[v8].equals(object0)) {
                    return arr_object[v8 ^ 1];
                }
            }
        }
        int v9 = ((int[])object1).length - 1;
        for(int v10 = zzdln.zzdv(object0.hashCode()); true; v10 = v11 + 1) {
            int v11 = v10 & v9;
            int v12 = ((int[])object1)[v11];
            if(v12 == -1) {
                return null;
            }
            if(arr_object[v12].equals(object0)) {
                return arr_object[v12 ^ 1];
            }
        }
    }

    @Override
    public final int size() {
        return this.size;
    }

    private static IllegalArgumentException zza(Object object0, Object object1, Object[] arr_object, int v) {
        return new IllegalArgumentException("Multiple entries with same key: " + object0 + "=" + object1 + " and " + arr_object[v] + "=" + arr_object[v ^ 1]);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlv
    final zzdly zzaud() {
        return new zzdmd(this, this.zzhas, 0, this.size);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlv
    final zzdly zzaue() {
        return new zzdmf(this, new zzdmi(this.zzhas, 0, this.size));
    }

    @Override  // com.google.android.gms.internal.ads.zzdlv
    final zzdlq zzauf() {
        return new zzdmi(this.zzhas, 1, this.size);
    }

    static zzdme zzc(int v, Object[] arr_object) {
        byte[] arr_b;
        zzdlg.zzt(5, arr_object.length >> 1);
        int v1 = zzdly.zzdx(5);
        int v2 = 0;
        if(v1 <= 0x80) {
            arr_b = new byte[v1];
            Arrays.fill(arr_b, -1);
            while(v2 < 5) {
                Object object0 = arr_object[v2 * 2];
                Object object1 = arr_object[v2 * 2 ^ 1];
                zzdll.zzb(object0, object1);
                int v3 = zzdln.zzdv(object0.hashCode());
            label_11:
                int v4 = v3 & v1 - 1;
                int v5 = arr_b[v4] & 0xFF;
                if(v5 == 0xFF) {
                    arr_b[v4] = (byte)(v2 * 2);
                    ++v2;
                    continue;
                }
                if(arr_object[v5].equals(object0)) {
                    throw zzdme.zza(object0, object1, arr_object, v5);
                }
                v3 = v4 + 1;
                goto label_11;
            }
        }
        else if(v1 <= 0x8000) {
            arr_b = new short[v1];
            Arrays.fill(((short[])arr_b), -1);
            while(v2 < 5) {
                Object object2 = arr_object[v2 * 2];
                Object object3 = arr_object[v2 * 2 ^ 1];
                zzdll.zzb(object2, object3);
                int v6 = zzdln.zzdv(object2.hashCode());
            label_29:
                int v7 = v6 & v1 - 1;
                int v8 = arr_b[v7] & 0xFFFF;
                if(v8 == 0xFFFF) {
                    arr_b[v7] = (short)(v2 * 2);
                    ++v2;
                    continue;
                }
                if(arr_object[v8].equals(object2)) {
                    throw zzdme.zza(object2, object3, arr_object, v8);
                }
                v6 = v7 + 1;
                goto label_29;
            }
        }
        else {
            arr_b = new int[v1];
            Arrays.fill(((int[])arr_b), -1);
            while(v2 < 5) {
                Object object4 = arr_object[v2 * 2];
                Object object5 = arr_object[v2 * 2 ^ 1];
                zzdll.zzb(object4, object5);
                int v9 = zzdln.zzdv(object4.hashCode());
            label_46:
                int v10 = v9 & v1 - 1;
                int v11 = arr_b[v10];
                if(v11 == -1) {
                    arr_b[v10] = v2 * 2;
                    ++v2;
                    continue;
                }
                if(arr_object[v11].equals(object4)) {
                    throw zzdme.zza(object4, object5, arr_object, v11);
                }
                v9 = v10 + 1;
                goto label_46;
            }
        }
        return new zzdme(arr_b, arr_object, 5);
    }
}

