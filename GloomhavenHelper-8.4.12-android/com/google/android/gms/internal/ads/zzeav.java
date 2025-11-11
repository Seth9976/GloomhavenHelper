package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.RandomAccess;

final class zzeav extends zzdxh implements RandomAccess {
    private int size;
    private Object[] zzhaq;
    private static final zzeav zzhvy;

    static {
        zzeav zzeav0 = new zzeav(new Object[0], 0);
        zzeav.zzhvy = zzeav0;
        zzeav0.zzban();
    }

    zzeav() {
        this(new Object[10], 0);
    }

    private zzeav(Object[] arr_object, int v) {
        this.zzhaq = arr_object;
        this.size = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void add(int v, Object object0) {
        this.zzbao();
        if(v >= 0) {
            int v1 = this.size;
            if(v <= v1) {
                Object[] arr_object = this.zzhaq;
                if(v1 < arr_object.length) {
                    System.arraycopy(arr_object, v, arr_object, v + 1, v1 - v);
                }
                else {
                    Object[] arr_object1 = new Object[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_object, 0, arr_object1, 0, v);
                    System.arraycopy(this.zzhaq, v, arr_object1, v + 1, this.size - v);
                    this.zzhaq = arr_object1;
                }
                this.zzhaq[v] = object0;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzfc(v));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean add(Object object0) {
        this.zzbao();
        int v = this.size;
        Object[] arr_object = this.zzhaq;
        if(v == arr_object.length) {
            this.zzhaq = Arrays.copyOf(arr_object, v * 3 / 2 + 1);
        }
        int v1 = this.size;
        this.size = v1 + 1;
        this.zzhaq[v1] = object0;
        ++this.modCount;
        return true;
    }

    @Override
    public final Object get(int v) {
        this.zzfb(v);
        return this.zzhaq[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object remove(int v) {
        this.zzbao();
        this.zzfb(v);
        Object[] arr_object = this.zzhaq;
        Object object0 = arr_object[v];
        int v1 = this.size;
        if(v < v1 - 1) {
            System.arraycopy(arr_object, v + 1, arr_object, v, v1 - v - 1);
        }
        --this.size;
        ++this.modCount;
        return object0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object set(int v, Object object0) {
        this.zzbao();
        this.zzfb(v);
        Object[] arr_object = this.zzhaq;
        Object object1 = arr_object[v];
        arr_object[v] = object0;
        ++this.modCount;
        return object1;
    }

    @Override
    public final int size() {
        return this.size;
    }

    public static zzeav zzbel() {
        return zzeav.zzhvy;
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
        return new zzeav(Arrays.copyOf(this.zzhaq, v), this.size);
    }
}

