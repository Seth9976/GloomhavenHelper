package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzff extends zzdp implements zzfi, zzgz, RandomAccess {
    private static final zzff zza;
    private int[] zzb;
    private int zzc;

    static {
        zzff zzff0 = new zzff(new int[0], 0);
        zzff.zza = zzff0;
        zzff0.j_();
    }

    zzff() {
        this(new int[10], 0);
    }

    private zzff(int[] arr_v, int v) {
        this.zzb = arr_v;
        this.zzc = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final void add(int v, Object object0) {
        int v1 = (int)(((Integer)object0));
        this.zzc();
        if(v >= 0) {
            int v2 = this.zzc;
            if(v <= v2) {
                int[] arr_v = this.zzb;
                if(v2 < arr_v.length) {
                    System.arraycopy(arr_v, v, arr_v, v + 1, v2 - v);
                }
                else {
                    int[] arr_v1 = new int[v2 * 3 / 2 + 1];
                    System.arraycopy(arr_v, 0, arr_v1, 0, v);
                    System.arraycopy(this.zzb, v, arr_v1, v + 1, this.zzc - v);
                    this.zzb = arr_v1;
                }
                this.zzb[v] = v1;
                ++this.zzc;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzf(v));
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean add(Object object0) {
        this.zzd(((int)(((Integer)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean addAll(Collection collection0) {
        this.zzc();
        zzfe.zza(collection0);
        if(!(collection0 instanceof zzff)) {
            return super.addAll(collection0);
        }
        int v = ((zzff)collection0).zzc;
        if(v == 0) {
            return false;
        }
        int v1 = this.zzc;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        int[] arr_v = this.zzb;
        if(v2 > arr_v.length) {
            this.zzb = Arrays.copyOf(arr_v, v2);
        }
        System.arraycopy(((zzff)collection0).zzb, 0, this.zzb, this.zzc, ((zzff)collection0).zzc);
        this.zzc = v2;
        ++this.modCount;
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzff)) {
            return super.equals(object0);
        }
        if(this.zzc != ((zzff)object0).zzc) {
            return false;
        }
        int[] arr_v = ((zzff)object0).zzb;
        for(int v = 0; v < this.zzc; ++v) {
            if(this.zzb[v] != arr_v[v]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        return this.zzc(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.zzc; ++v1) {
            v = v * 0x1F + this.zzb[v1];
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object remove(int v) {
        this.zzc();
        this.zze(v);
        int[] arr_v = this.zzb;
        int v1 = arr_v[v];
        int v2 = this.zzc;
        if(v < v2 - 1) {
            System.arraycopy(arr_v, v + 1, arr_v, v, v2 - v - 1);
        }
        --this.zzc;
        ++this.modCount;
        return v1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean remove(Object object0) {
        this.zzc();
        for(int v = 0; v < this.zzc; ++v) {
            if(object0.equals(((int)this.zzb[v]))) {
                System.arraycopy(this.zzb, v + 1, this.zzb, v, this.zzc - v - 1);
                --this.zzc;
                ++this.modCount;
                return true;
            }
        }
        return false;
    }

    @Override
    protected final void removeRange(int v, int v1) {
        this.zzc();
        if(v1 < v) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzb, v1, this.zzb, v, this.zzc - v1);
        this.zzc -= v1 - v;
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object set(int v, Object object0) {
        this.zzc();
        this.zze(v);
        int[] arr_v = this.zzb;
        int v1 = arr_v[v];
        arr_v[v] = (int)(((Integer)object0));
        return v1;
    }

    @Override
    public final int size() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zzfk
    public final zzfk zza(int v) {
        return this.zzb(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfi
    public final zzfi zzb(int v) {
        if(v < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzff(Arrays.copyOf(this.zzb, v), this.zzc);
    }

    public final int zzc(int v) {
        this.zze(v);
        return this.zzb[v];
    }

    public static zzff zzd() {
        return zzff.zza;
    }

    public final void zzd(int v) {
        this.zzc();
        int v1 = this.zzc;
        int[] arr_v = this.zzb;
        if(v1 == arr_v.length) {
            int[] arr_v1 = new int[v1 * 3 / 2 + 1];
            System.arraycopy(arr_v, 0, arr_v1, 0, v1);
            this.zzb = arr_v1;
        }
        int v2 = this.zzc;
        this.zzc = v2 + 1;
        this.zzb[v2] = v;
    }

    private final void zze(int v) {
        if(v < 0 || v >= this.zzc) {
            throw new IndexOutOfBoundsException(this.zzf(v));
        }
    }

    private final String zzf(int v) {
        return "Index:" + v + ", Size:" + this.zzc;
    }
}

