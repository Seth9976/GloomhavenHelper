package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdmh extends zzdly {
    private final transient int mask;
    private final transient int size;
    private final transient int zzagg;
    static final zzdmh zzhax;
    private final transient Object[] zzhay;
    private final transient Object[] zzhaz;

    static {
        zzdmh.zzhax = new zzdmh(new Object[0], 0, null, 0, 0);
    }

    zzdmh(Object[] arr_object, int v, Object[] arr_object1, int v1, int v2) {
        this.zzhay = arr_object;
        this.zzhaz = arr_object1;
        this.mask = v1;
        this.zzagg = v;
        this.size = v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final boolean contains(@NullableDecl Object object0) {
        Object[] arr_object = this.zzhaz;
        if(object0 != null && arr_object != null) {
            for(int v = zzdln.zzdv((object0 == null ? 0 : object0.hashCode())); true; v = v1 + 1) {
                int v1 = v & this.mask;
                Object object1 = arr_object[v1];
                if(object1 == null) {
                    return false;
                }
                if(object1.equals(object0)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    public final int hashCode() {
        return this.zzagg;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    public final Iterator iterator() {
        return this.zzatw();
    }

    @Override
    public final int size() {
        return this.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzhay, 0, arr_object, v, this.size);
        return v + this.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final zzdmm zzatw() {
        return (zzdmm)this.zzaua().iterator();
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final Object[] zzatx() {
        return this.zzhay;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final int zzaty() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final int zzatz() {
        return this.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final boolean zzaub() {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    final boolean zzauh() {
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    final zzdlr zzaui() {
        return zzdlr.zzb(this.zzhay, this.size);
    }
}

