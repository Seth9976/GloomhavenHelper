package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.RandomAccess;

final class zzhb extends zzdp implements RandomAccess {
    private static final zzhb zza;
    private Object[] zzb;
    private int zzc;

    static {
        zzhb zzhb0 = new zzhb(new Object[0], 0);
        zzhb.zza = zzhb0;
        zzhb0.j_();
    }

    zzhb() {
        this(new Object[10], 0);
    }

    private zzhb(Object[] arr_object, int v) {
        this.zzb = arr_object;
        this.zzc = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final void add(int v, Object object0) {
        this.zzc();
        if(v >= 0) {
            int v1 = this.zzc;
            if(v <= v1) {
                Object[] arr_object = this.zzb;
                if(v1 < arr_object.length) {
                    System.arraycopy(arr_object, v, arr_object, v + 1, v1 - v);
                }
                else {
                    Object[] arr_object1 = new Object[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_object, 0, arr_object1, 0, v);
                    System.arraycopy(this.zzb, v, arr_object1, v + 1, this.zzc - v);
                    this.zzb = arr_object1;
                }
                this.zzb[v] = object0;
                ++this.zzc;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzc(v));
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean add(Object object0) {
        this.zzc();
        int v = this.zzc;
        Object[] arr_object = this.zzb;
        if(v == arr_object.length) {
            this.zzb = Arrays.copyOf(arr_object, v * 3 / 2 + 1);
        }
        int v1 = this.zzc;
        this.zzc = v1 + 1;
        this.zzb[v1] = object0;
        ++this.modCount;
        return true;
    }

    @Override
    public final Object get(int v) {
        this.zzb(v);
        return this.zzb[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object remove(int v) {
        this.zzc();
        this.zzb(v);
        Object[] arr_object = this.zzb;
        Object object0 = arr_object[v];
        int v1 = this.zzc;
        if(v < v1 - 1) {
            System.arraycopy(arr_object, v + 1, arr_object, v, v1 - v - 1);
        }
        --this.zzc;
        ++this.modCount;
        return object0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object set(int v, Object object0) {
        this.zzc();
        this.zzb(v);
        Object[] arr_object = this.zzb;
        Object object1 = arr_object[v];
        arr_object[v] = object0;
        ++this.modCount;
        return object1;
    }

    @Override
    public final int size() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zzfk
    public final zzfk zza(int v) {
        if(v < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzhb(Arrays.copyOf(this.zzb, v), this.zzc);
    }

    private final void zzb(int v) {
        if(v < 0 || v >= this.zzc) {
            throw new IndexOutOfBoundsException(this.zzc(v));
        }
    }

    private final String zzc(int v) {
        return "Index:" + v + ", Size:" + this.zzc;
    }

    public static zzhb zzd() {
        return zzhb.zza;
    }
}

