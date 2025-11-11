package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdyl extends zzdxh implements zzdzi, zzeat, RandomAccess {
    private int size;
    private static final zzdyl zzhpm;
    private double[] zzhpn;

    static {
        zzdyl zzdyl0 = new zzdyl(new double[0], 0);
        zzdyl.zzhpm = zzdyl0;
        zzdyl0.zzban();
    }

    zzdyl() {
        this(new double[10], 0);
    }

    private zzdyl(double[] arr_f, int v) {
        this.zzhpn = arr_f;
        this.size = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void add(int v, Object object0) {
        double f = (double)(((Double)object0));
        this.zzbao();
        if(v >= 0) {
            int v1 = this.size;
            if(v <= v1) {
                double[] arr_f = this.zzhpn;
                if(v1 < arr_f.length) {
                    System.arraycopy(arr_f, v, arr_f, v + 1, v1 - v);
                }
                else {
                    double[] arr_f1 = new double[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_f, 0, arr_f1, 0, v);
                    System.arraycopy(this.zzhpn, v, arr_f1, v + 1, this.size - v);
                    this.zzhpn = arr_f1;
                }
                this.zzhpn[v] = f;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzfc(v));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean add(Object object0) {
        this.zzd(((double)(((Double)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean addAll(Collection collection0) {
        this.zzbao();
        zzdzc.checkNotNull(collection0);
        if(!(collection0 instanceof zzdyl)) {
            return super.addAll(collection0);
        }
        int v = ((zzdyl)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        double[] arr_f = this.zzhpn;
        if(v2 > arr_f.length) {
            this.zzhpn = Arrays.copyOf(arr_f, v2);
        }
        System.arraycopy(((zzdyl)collection0).zzhpn, 0, this.zzhpn, this.size, ((zzdyl)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzdyl)) {
            return super.equals(object0);
        }
        if(this.size != ((zzdyl)object0).size) {
            return false;
        }
        double[] arr_f = ((zzdyl)object0).zzhpn;
        for(int v = 0; v < this.size; ++v) {
            if(Double.doubleToLongBits(this.zzhpn[v]) != Double.doubleToLongBits(arr_f[v])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        this.zzfb(v);
        return (double)this.zzhpn[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + zzdzc.zzfr(Double.doubleToLongBits(this.zzhpn[v1]));
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object remove(int v) {
        this.zzbao();
        this.zzfb(v);
        double[] arr_f = this.zzhpn;
        double f = arr_f[v];
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
            if(object0.equals(((double)this.zzhpn[v]))) {
                System.arraycopy(this.zzhpn, v + 1, this.zzhpn, v, this.size - v - 1);
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
        System.arraycopy(this.zzhpn, v1, this.zzhpn, v, this.size - v1);
        this.size -= v1 - v;
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object set(int v, Object object0) {
        this.zzbao();
        this.zzfb(v);
        double[] arr_f = this.zzhpn;
        double f = arr_f[v];
        arr_f[v] = (double)(((Double)object0));
        return f;
    }

    @Override
    public final int size() {
        return this.size;
    }

    public final void zzd(double f) {
        this.zzbao();
        int v = this.size;
        double[] arr_f = this.zzhpn;
        if(v == arr_f.length) {
            double[] arr_f1 = new double[v * 3 / 2 + 1];
            System.arraycopy(arr_f, 0, arr_f1, 0, v);
            this.zzhpn = arr_f1;
        }
        int v1 = this.size;
        this.size = v1 + 1;
        this.zzhpn[v1] = f;
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
        return new zzdyl(Arrays.copyOf(this.zzhpn, v), this.size);
    }
}

