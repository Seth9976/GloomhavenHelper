package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzez extends zzdp implements zzfk, zzgz, RandomAccess {
    private static final zzez zza;
    private float[] zzb;
    private int zzc;

    static {
        zzez zzez0 = new zzez(new float[0], 0);
        zzez.zza = zzez0;
        zzez0.j_();
    }

    zzez() {
        this(new float[10], 0);
    }

    private zzez(float[] arr_f, int v) {
        this.zzb = arr_f;
        this.zzc = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final void add(int v, Object object0) {
        float f = (float)(((Float)object0));
        this.zzc();
        if(v >= 0) {
            int v1 = this.zzc;
            if(v <= v1) {
                float[] arr_f = this.zzb;
                if(v1 < arr_f.length) {
                    System.arraycopy(arr_f, v, arr_f, v + 1, v1 - v);
                }
                else {
                    float[] arr_f1 = new float[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_f, 0, arr_f1, 0, v);
                    System.arraycopy(this.zzb, v, arr_f1, v + 1, this.zzc - v);
                    this.zzb = arr_f1;
                }
                this.zzb[v] = f;
                ++this.zzc;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzc(v));
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean add(Object object0) {
        this.zza(((float)(((Float)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean addAll(Collection collection0) {
        this.zzc();
        zzfe.zza(collection0);
        if(!(collection0 instanceof zzez)) {
            return super.addAll(collection0);
        }
        int v = ((zzez)collection0).zzc;
        if(v == 0) {
            return false;
        }
        int v1 = this.zzc;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        float[] arr_f = this.zzb;
        if(v2 > arr_f.length) {
            this.zzb = Arrays.copyOf(arr_f, v2);
        }
        System.arraycopy(((zzez)collection0).zzb, 0, this.zzb, this.zzc, ((zzez)collection0).zzc);
        this.zzc = v2;
        ++this.modCount;
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzez)) {
            return super.equals(object0);
        }
        if(this.zzc != ((zzez)object0).zzc) {
            return false;
        }
        float[] arr_f = ((zzez)object0).zzb;
        for(int v = 0; v < this.zzc; ++v) {
            if(Float.floatToIntBits(this.zzb[v]) != Float.floatToIntBits(arr_f[v])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        this.zzb(v);
        return (float)this.zzb[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.zzc; ++v1) {
            v = v * 0x1F + Float.floatToIntBits(this.zzb[v1]);
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object remove(int v) {
        this.zzc();
        this.zzb(v);
        float[] arr_f = this.zzb;
        float f = arr_f[v];
        int v1 = this.zzc;
        if(v < v1 - 1) {
            System.arraycopy(arr_f, v + 1, arr_f, v, v1 - v - 1);
        }
        --this.zzc;
        ++this.modCount;
        return f;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean remove(Object object0) {
        this.zzc();
        for(int v = 0; v < this.zzc; ++v) {
            if(object0.equals(((float)this.zzb[v]))) {
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
        this.zzb(v);
        float[] arr_f = this.zzb;
        float f = arr_f[v];
        arr_f[v] = (float)(((Float)object0));
        return f;
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
        return new zzez(Arrays.copyOf(this.zzb, v), this.zzc);
    }

    public final void zza(float f) {
        this.zzc();
        int v = this.zzc;
        float[] arr_f = this.zzb;
        if(v == arr_f.length) {
            float[] arr_f1 = new float[v * 3 / 2 + 1];
            System.arraycopy(arr_f, 0, arr_f1, 0, v);
            this.zzb = arr_f1;
        }
        int v1 = this.zzc;
        this.zzc = v1 + 1;
        this.zzb[v1] = f;
    }

    private final void zzb(int v) {
        if(v < 0 || v >= this.zzc) {
            throw new IndexOutOfBoundsException(this.zzc(v));
        }
    }

    private final String zzc(int v) {
        return "Index:" + v + ", Size:" + this.zzc;
    }
}

