package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzdmj extends zzdly {
    private final transient Object zzhba;
    private transient int zzhbb;

    zzdmj(Object object0) {
        this.zzhba = zzdlg.checkNotNull(object0);
    }

    zzdmj(Object object0, int v) {
        this.zzhba = object0;
        this.zzhbb = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final boolean contains(Object object0) {
        return this.zzhba.equals(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    public final int hashCode() {
        int v = this.zzhbb;
        if(v == 0) {
            v = this.zzhba.hashCode();
            this.zzhbb = v;
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    public final Iterator iterator() {
        return this.zzatw();
    }

    @Override
    public final int size() {
        return 1;
    }

    @Override
    public final String toString() {
        return '[' + this.zzhba.toString() + ']';
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final int zza(Object[] arr_object, int v) {
        arr_object[v] = this.zzhba;
        return v + 1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final zzdmm zzatw() {
        return new zzdma(this.zzhba);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final boolean zzaub() {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    final boolean zzauh() {
        return this.zzhbb != 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdly
    final zzdlr zzaui() {
        return zzdlr.zzag(this.zzhba);
    }
}

