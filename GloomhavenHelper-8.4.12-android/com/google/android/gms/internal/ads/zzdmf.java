package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdmf extends zzdly {
    private final transient zzdlr zzhaf;
    private final transient zzdlv zzhar;

    zzdmf(zzdlv zzdlv0, zzdlr zzdlr0) {
        this.zzhar = zzdlv0;
        this.zzhaf = zzdlr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final boolean contains(@NullableDecl Object object0) {
        return this.zzhar.get(object0) != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    public final Iterator iterator() {
        return this.zzatw();
    }

    @Override
    public final int size() {
        return this.zzhar.size();
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final int zza(Object[] arr_object, int v) {
        return this.zzaua().zza(arr_object, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final zzdmm zzatw() {
        return (zzdmm)this.zzaua().iterator();
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    public final zzdlr zzaua() {
        return this.zzhaf;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final boolean zzaub() {
        return true;
    }
}

