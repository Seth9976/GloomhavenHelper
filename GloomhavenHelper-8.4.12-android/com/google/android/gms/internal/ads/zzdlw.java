package com.google.android.gms.internal.ads;

import java.util.List;

final class zzdlw extends zzdlr {
    private final transient int length;
    private final transient int offset;
    private final zzdlr zzhak;

    zzdlw(zzdlr zzdlr0, int v, int v1) {
        this.zzhak = zzdlr0;
        super();
        this.offset = v;
        this.length = v1;
    }

    @Override
    public final Object get(int v) {
        zzdlg.zzs(v, this.length);
        return this.zzhak.get(v + this.offset);
    }

    @Override
    public final int size() {
        return this.length;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlr
    public final List subList(int v, int v1) {
        return this.zzu(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final Object[] zzatx() {
        return this.zzhak.zzatx();
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final int zzaty() {
        return this.zzhak.zzaty() + this.offset;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final int zzatz() {
        return this.zzhak.zzaty() + this.offset + this.length;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final boolean zzaub() {
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlr
    public final zzdlr zzu(int v, int v1) {
        zzdlg.zzf(v, v1, this.length);
        return (zzdlr)this.zzhak.subList(v + this.offset, v1 + this.offset);
    }
}

