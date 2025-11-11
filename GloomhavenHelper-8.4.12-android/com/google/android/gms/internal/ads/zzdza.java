package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdza extends zzdxh implements zzdzg, zzeat, RandomAccess {
    private int size;
    private static final zzdza zzhta;
    private int[] zzhtb;

    static {
        zzdza zzdza0 = new zzdza(new int[0], 0);
        zzdza.zzhta = zzdza0;
        zzdza0.zzban();
    }

    zzdza() {
        this(new int[10], 0);
    }

    private zzdza(int[] arr_v, int v) {
        this.zzhtb = arr_v;
        this.size = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void add(int v, Object object0) {
        int v1 = (int)(((Integer)object0));
        this.zzbao();
        if(v >= 0) {
            int v2 = this.size;
            if(v <= v2) {
                int[] arr_v = this.zzhtb;
                if(v2 < arr_v.length) {
                    System.arraycopy(arr_v, v, arr_v, v + 1, v2 - v);
                }
                else {
                    int[] arr_v1 = new int[v2 * 3 / 2 + 1];
                    System.arraycopy(arr_v, 0, arr_v1, 0, v);
                    System.arraycopy(this.zzhtb, v, arr_v1, v + 1, this.size - v);
                    this.zzhtb = arr_v1;
                }
                this.zzhtb[v] = v1;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzfc(v));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean add(Object object0) {
        this.zzgl(((int)(((Integer)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean addAll(Collection collection0) {
        this.zzbao();
        zzdzc.checkNotNull(collection0);
        if(!(collection0 instanceof zzdza)) {
            return super.addAll(collection0);
        }
        int v = ((zzdza)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        int[] arr_v = this.zzhtb;
        if(v2 > arr_v.length) {
            this.zzhtb = Arrays.copyOf(arr_v, v2);
        }
        System.arraycopy(((zzdza)collection0).zzhtb, 0, this.zzhtb, this.size, ((zzdza)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzdza)) {
            return super.equals(object0);
        }
        if(this.size != ((zzdza)object0).size) {
            return false;
        }
        int[] arr_v = ((zzdza)object0).zzhtb;
        for(int v = 0; v < this.size; ++v) {
            if(this.zzhtb[v] != arr_v[v]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        return this.getInt(v);
    }

    public final int getInt(int v) {
        this.zzfb(v);
        return this.zzhtb[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + this.zzhtb[v1];
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object remove(int v) {
        this.zzbao();
        this.zzfb(v);
        int[] arr_v = this.zzhtb;
        int v1 = arr_v[v];
        int v2 = this.size;
        if(v < v2 - 1) {
            System.arraycopy(arr_v, v + 1, arr_v, v, v2 - v - 1);
        }
        --this.size;
        ++this.modCount;
        return v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean remove(Object object0) {
        this.zzbao();
        for(int v = 0; v < this.size; ++v) {
            if(object0.equals(((int)this.zzhtb[v]))) {
                System.arraycopy(this.zzhtb, v + 1, this.zzhtb, v, this.size - v - 1);
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
        System.arraycopy(this.zzhtb, v1, this.zzhtb, v, this.size - v1);
        this.size -= v1 - v;
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object set(int v, Object object0) {
        this.zzbao();
        this.zzfb(v);
        int[] arr_v = this.zzhtb;
        int v1 = arr_v[v];
        arr_v[v] = (int)(((Integer)object0));
        return v1;
    }

    @Override
    public final int size() {
        return this.size;
    }

    public static zzdza zzbdg() {
        return zzdza.zzhta;
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
        return this.zzgk(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdzg
    public final zzdzg zzgk(int v) {
        if(v < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzdza(Arrays.copyOf(this.zzhtb, v), this.size);
    }

    @Override  // com.google.android.gms.internal.ads.zzdzg
    public final void zzgl(int v) {
        this.zzbao();
        int v1 = this.size;
        int[] arr_v = this.zzhtb;
        if(v1 == arr_v.length) {
            int[] arr_v1 = new int[v1 * 3 / 2 + 1];
            System.arraycopy(arr_v, 0, arr_v1, 0, v1);
            this.zzhtb = arr_v1;
        }
        int v2 = this.size;
        this.size = v2 + 1;
        this.zzhtb[v2] = v;
    }
}

