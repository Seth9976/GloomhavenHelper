package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdzv extends zzdxh implements zzdzi, zzeat, RandomAccess {
    private int size;
    private static final zzdzv zzhut;
    private long[] zzhuu;

    static {
        zzdzv zzdzv0 = new zzdzv(new long[0], 0);
        zzdzv.zzhut = zzdzv0;
        zzdzv0.zzban();
    }

    zzdzv() {
        this(new long[10], 0);
    }

    private zzdzv(long[] arr_v, int v) {
        this.zzhuu = arr_v;
        this.size = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void add(int v, Object object0) {
        long v1 = (long)(((Long)object0));
        this.zzbao();
        if(v >= 0) {
            int v2 = this.size;
            if(v <= v2) {
                long[] arr_v = this.zzhuu;
                if(v2 < arr_v.length) {
                    System.arraycopy(arr_v, v, arr_v, v + 1, v2 - v);
                }
                else {
                    long[] arr_v1 = new long[v2 * 3 / 2 + 1];
                    System.arraycopy(arr_v, 0, arr_v1, 0, v);
                    System.arraycopy(this.zzhuu, v, arr_v1, v + 1, this.size - v);
                    this.zzhuu = arr_v1;
                }
                this.zzhuu[v] = v1;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzfc(v));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean add(Object object0) {
        this.zzfs(((long)(((Long)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean addAll(Collection collection0) {
        this.zzbao();
        zzdzc.checkNotNull(collection0);
        if(!(collection0 instanceof zzdzv)) {
            return super.addAll(collection0);
        }
        int v = ((zzdzv)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        long[] arr_v = this.zzhuu;
        if(v2 > arr_v.length) {
            this.zzhuu = Arrays.copyOf(arr_v, v2);
        }
        System.arraycopy(((zzdzv)collection0).zzhuu, 0, this.zzhuu, this.size, ((zzdzv)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzdzv)) {
            return super.equals(object0);
        }
        if(this.size != ((zzdzv)object0).size) {
            return false;
        }
        long[] arr_v = ((zzdzv)object0).zzhuu;
        for(int v = 0; v < this.size; ++v) {
            if(this.zzhuu[v] != arr_v[v]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        return this.getLong(v);
    }

    public final long getLong(int v) {
        this.zzfb(v);
        return this.zzhuu[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + zzdzc.zzfr(this.zzhuu[v1]);
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object remove(int v) {
        this.zzbao();
        this.zzfb(v);
        long[] arr_v = this.zzhuu;
        long v1 = arr_v[v];
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
            if(object0.equals(((long)this.zzhuu[v]))) {
                System.arraycopy(this.zzhuu, v + 1, this.zzhuu, v, this.size - v - 1);
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
        System.arraycopy(this.zzhuu, v1, this.zzhuu, v, this.size - v1);
        this.size -= v1 - v;
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object set(int v, Object object0) {
        this.zzbao();
        this.zzfb(v);
        long[] arr_v = this.zzhuu;
        long v1 = arr_v[v];
        arr_v[v] = (long)(((Long)object0));
        return v1;
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
        return new zzdzv(Arrays.copyOf(this.zzhuu, v), this.size);
    }

    public final void zzfs(long v) {
        this.zzbao();
        int v1 = this.size;
        long[] arr_v = this.zzhuu;
        if(v1 == arr_v.length) {
            long[] arr_v1 = new long[v1 * 3 / 2 + 1];
            System.arraycopy(arr_v, 0, arr_v1, 0, v1);
            this.zzhuu = arr_v1;
        }
        int v2 = this.size;
        this.size = v2 + 1;
        this.zzhuu[v2] = v;
    }
}

