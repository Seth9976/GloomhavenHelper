package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzgb extends zzdp implements zzfl, zzgz, RandomAccess {
    private static final zzgb zza;
    private long[] zzb;
    private int zzc;

    static {
        zzgb zzgb0 = new zzgb(new long[0], 0);
        zzgb.zza = zzgb0;
        zzgb0.j_();
    }

    zzgb() {
        this(new long[10], 0);
    }

    private zzgb(long[] arr_v, int v) {
        this.zzb = arr_v;
        this.zzc = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final void add(int v, Object object0) {
        long v1 = (long)(((Long)object0));
        this.zzc();
        if(v >= 0) {
            int v2 = this.zzc;
            if(v <= v2) {
                long[] arr_v = this.zzb;
                if(v2 < arr_v.length) {
                    System.arraycopy(arr_v, v, arr_v, v + 1, v2 - v);
                }
                else {
                    long[] arr_v1 = new long[v2 * 3 / 2 + 1];
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
        throw new IndexOutOfBoundsException(this.zze(v));
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean add(Object object0) {
        this.zza(((long)(((Long)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean addAll(Collection collection0) {
        this.zzc();
        zzfe.zza(collection0);
        if(!(collection0 instanceof zzgb)) {
            return super.addAll(collection0);
        }
        int v = ((zzgb)collection0).zzc;
        if(v == 0) {
            return false;
        }
        int v1 = this.zzc;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        long[] arr_v = this.zzb;
        if(v2 > arr_v.length) {
            this.zzb = Arrays.copyOf(arr_v, v2);
        }
        System.arraycopy(((zzgb)collection0).zzb, 0, this.zzb, this.zzc, ((zzgb)collection0).zzc);
        this.zzc = v2;
        ++this.modCount;
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzgb)) {
            return super.equals(object0);
        }
        if(this.zzc != ((zzgb)object0).zzc) {
            return false;
        }
        long[] arr_v = ((zzgb)object0).zzb;
        for(int v = 0; v < this.zzc; ++v) {
            if(this.zzb[v] != arr_v[v]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        return this.zzb(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.zzc; ++v1) {
            v = v * 0x1F + zzfe.zza(this.zzb[v1]);
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object remove(int v) {
        this.zzc();
        this.zzd(v);
        long[] arr_v = this.zzb;
        long v1 = arr_v[v];
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
            if(object0.equals(((long)this.zzb[v]))) {
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
        this.zzd(v);
        long[] arr_v = this.zzb;
        long v1 = arr_v[v];
        arr_v[v] = (long)(((Long)object0));
        return v1;
    }

    @Override
    public final int size() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zzfk
    public final zzfk zza(int v) {
        return this.zzc(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfl
    public final void zza(long v) {
        this.zzc();
        int v1 = this.zzc;
        long[] arr_v = this.zzb;
        if(v1 == arr_v.length) {
            long[] arr_v1 = new long[v1 * 3 / 2 + 1];
            System.arraycopy(arr_v, 0, arr_v1, 0, v1);
            this.zzb = arr_v1;
        }
        int v2 = this.zzc;
        this.zzc = v2 + 1;
        this.zzb[v2] = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzfl
    public final long zzb(int v) {
        this.zzd(v);
        return this.zzb[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzfl
    public final zzfl zzc(int v) {
        if(v < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzgb(Arrays.copyOf(this.zzb, v), this.zzc);
    }

    public static zzgb zzd() {
        return zzgb.zza;
    }

    private final void zzd(int v) {
        if(v < 0 || v >= this.zzc) {
            throw new IndexOutOfBoundsException(this.zze(v));
        }
    }

    private final String zze(int v) {
        return "Index:" + v + ", Size:" + this.zzc;
    }
}

