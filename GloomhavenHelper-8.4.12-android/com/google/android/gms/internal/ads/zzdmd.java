package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzdmd extends zzdly {
    private final transient int size;
    private final transient zzdlv zzhar;
    private final transient Object[] zzhas;
    private final transient int zzhat;

    zzdmd(zzdlv zzdlv0, Object[] arr_object, int v, int v1) {
        this.zzhar = zzdlv0;
        this.zzhas = arr_object;
        this.zzhat = 0;
        this.size = v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final boolean contains(Object object0) {
        if(object0 instanceof Map.Entry) {
            Object object1 = ((Map.Entry)object0).getKey();
            Object object2 = ((Map.Entry)object0).getValue();
            return object2 != null && object2.equals(this.zzhar.get(object1));
        }
        return false;
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
        return this.zzaua().zza(arr_object, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final zzdmm zzatw() {
        return (zzdmm)this.zzaua().iterator();
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final boolean zzaub() {
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    final zzdlr zzaui() {
        return new zzdmg(this);
    }
}

