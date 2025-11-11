package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdyv extends zzdxh implements zzdzi, zzeat, RandomAccess {
    private int size;
    private static final zzdyv zzhsj;
    private float[] zzhsk;

    static {
        zzdyv zzdyv0 = new zzdyv(new float[0], 0);
        zzdyv.zzhsj = zzdyv0;
        zzdyv0.zzban();
    }

    zzdyv() {
        this(new float[10], 0);
    }

    private zzdyv(float[] arr_f, int v) {
        this.zzhsk = arr_f;
        this.size = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void add(int v, Object object0) {
        float f = (float)(((Float)object0));
        this.zzbao();
        if(v >= 0) {
            int v1 = this.size;
            if(v <= v1) {
                float[] arr_f = this.zzhsk;
                if(v1 < arr_f.length) {
                    System.arraycopy(arr_f, v, arr_f, v + 1, v1 - v);
                }
                else {
                    float[] arr_f1 = new float[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_f, 0, arr_f1, 0, v);
                    System.arraycopy(this.zzhsk, v, arr_f1, v + 1, this.size - v);
                    this.zzhsk = arr_f1;
                }
                this.zzhsk[v] = f;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzfc(v));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean add(Object object0) {
        this.zzh(((float)(((Float)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean addAll(Collection collection0) {
        this.zzbao();
        zzdzc.checkNotNull(collection0);
        if(!(collection0 instanceof zzdyv)) {
            return super.addAll(collection0);
        }
        int v = ((zzdyv)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        float[] arr_f = this.zzhsk;
        if(v2 > arr_f.length) {
            this.zzhsk = Arrays.copyOf(arr_f, v2);
        }
        System.arraycopy(((zzdyv)collection0).zzhsk, 0, this.zzhsk, this.size, ((zzdyv)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzdyv)) {
            return super.equals(object0);
        }
        if(this.size != ((zzdyv)object0).size) {
            return false;
        }
        float[] arr_f = ((zzdyv)object0).zzhsk;
        for(int v = 0; v < this.size; ++v) {
            if(Float.floatToIntBits(this.zzhsk[v]) != Float.floatToIntBits(arr_f[v])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        this.zzfb(v);
        return (float)this.zzhsk[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + Float.floatToIntBits(this.zzhsk[v1]);
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object remove(int v) {
        this.zzbao();
        this.zzfb(v);
        float[] arr_f = this.zzhsk;
        float f = arr_f[v];
        int v1 = this.size;
        if(v < v1 - 1) {
            System.arraycopy(arr_f, v + 1, arr_f, v, v1 - v - 1);
        }
        --this.size;
        ++this.modCount;
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean remove(Object object0) {
        this.zzbao();
        for(int v = 0; v < this.size; ++v) {
            if(object0.equals(((float)this.zzhsk[v]))) {
                System.arraycopy(this.zzhsk, v + 1, this.zzhsk, v, this.size - v - 1);
                --this.size;
                ++this.modCount;
                return true;
            }
        }
        return false;
    }

    @Override
    protected final void removeRange(int v, int v1) {
        this.zzbao();
        if(v1 < v) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzhsk, v1, this.zzhsk, v, this.size - v1);
        this.size -= v1 - v;
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object set(int v, Object object0) {
        this.zzbao();
        this.zzfb(v);
        float[] arr_f = this.zzhsk;
        float f = arr_f[v];
        arr_f[v] = (float)(((Float)object0));
        return f;
    }

    @Override
    public final int size() {
        return this.size;
    }

    private final void zzfb(int v) {
        if(v < 0 || v >= this.size) {
            throw new IndexOutOfBoundsException(this.zzfc(v));
        }
    }

    private final String zzfc(int v) {
        return "Index:" + v + ", Size:" + this.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdzi
    public final zzdzi zzfd(int v) {
        if(v < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzdyv(Arrays.copyOf(this.zzhsk, v), this.size);
    }

    public final void zzh(float f) {
        this.zzbao();
        int v = this.size;
        float[] arr_f = this.zzhsk;
        if(v == arr_f.length) {
            float[] arr_f1 = new float[v * 3 / 2 + 1];
            System.arraycopy(arr_f, 0, arr_f1, 0, v);
            this.zzhsk = arr_f1;
        }
        int v1 = this.size;
        this.size = v1 + 1;
        this.zzhsk[v1] = f;
    }
}

