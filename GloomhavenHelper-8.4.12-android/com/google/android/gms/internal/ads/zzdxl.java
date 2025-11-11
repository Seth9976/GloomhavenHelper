package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdxl extends zzdxh implements zzdzi, zzeat, RandomAccess {
    private int size;
    private static final zzdxl zzhny;
    private boolean[] zzhnz;

    static {
        zzdxl zzdxl0 = new zzdxl(new boolean[0], 0);
        zzdxl.zzhny = zzdxl0;
        zzdxl0.zzban();
    }

    zzdxl() {
        this(new boolean[10], 0);
    }

    private zzdxl(boolean[] arr_z, int v) {
        this.zzhnz = arr_z;
        this.size = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void add(int v, Object object0) {
        boolean z = ((Boolean)object0).booleanValue();
        this.zzbao();
        if(v >= 0) {
            int v1 = this.size;
            if(v <= v1) {
                boolean[] arr_z = this.zzhnz;
                if(v1 < arr_z.length) {
                    System.arraycopy(arr_z, v, arr_z, v + 1, v1 - v);
                }
                else {
                    boolean[] arr_z1 = new boolean[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_z, 0, arr_z1, 0, v);
                    System.arraycopy(this.zzhnz, v, arr_z1, v + 1, this.size - v);
                    this.zzhnz = arr_z1;
                }
                this.zzhnz[v] = z;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzfc(v));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean add(Object object0) {
        this.addBoolean(((Boolean)object0).booleanValue());
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean addAll(Collection collection0) {
        this.zzbao();
        zzdzc.checkNotNull(collection0);
        if(!(collection0 instanceof zzdxl)) {
            return super.addAll(collection0);
        }
        int v = ((zzdxl)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        boolean[] arr_z = this.zzhnz;
        if(v2 > arr_z.length) {
            this.zzhnz = Arrays.copyOf(arr_z, v2);
        }
        System.arraycopy(((zzdxl)collection0).zzhnz, 0, this.zzhnz, this.size, ((zzdxl)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    public final void addBoolean(boolean z) {
        this.zzbao();
        int v = this.size;
        boolean[] arr_z = this.zzhnz;
        if(v == arr_z.length) {
            boolean[] arr_z1 = new boolean[v * 3 / 2 + 1];
            System.arraycopy(arr_z, 0, arr_z1, 0, v);
            this.zzhnz = arr_z1;
        }
        int v1 = this.size;
        this.size = v1 + 1;
        this.zzhnz[v1] = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzdxl)) {
            return super.equals(object0);
        }
        if(this.size != ((zzdxl)object0).size) {
            return false;
        }
        boolean[] arr_z = ((zzdxl)object0).zzhnz;
        for(int v = 0; v < this.size; ++v) {
            if(this.zzhnz[v] != arr_z[v]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        this.zzfb(v);
        return Boolean.valueOf(this.zzhnz[v]);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + zzdzc.zzbr(this.zzhnz[v1]);
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object remove(int v) {
        this.zzbao();
        this.zzfb(v);
        boolean[] arr_z = this.zzhnz;
        boolean z = arr_z[v];
        int v1 = this.size;
        if(v < v1 - 1) {
            System.arraycopy(arr_z, v + 1, arr_z, v, v1 - v - 1);
        }
        --this.size;
        ++this.modCount;
        return Boolean.valueOf(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean remove(Object object0) {
        this.zzbao();
        for(int v = 0; v < this.size; ++v) {
            if(object0.equals(Boolean.valueOf(this.zzhnz[v]))) {
                System.arraycopy(this.zzhnz, v + 1, this.zzhnz, v, this.size - v - 1);
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
        System.arraycopy(this.zzhnz, v1, this.zzhnz, v, this.size - v1);
        this.size -= v1 - v;
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object set(int v, Object object0) {
        this.zzbao();
        this.zzfb(v);
        boolean[] arr_z = this.zzhnz;
        boolean z = arr_z[v];
        arr_z[v] = ((Boolean)object0).booleanValue();
        return Boolean.valueOf(z);
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
        return new zzdxl(Arrays.copyOf(this.zzhnz, v), this.size);
    }
}

