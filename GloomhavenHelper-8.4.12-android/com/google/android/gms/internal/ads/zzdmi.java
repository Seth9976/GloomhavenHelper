package com.google.android.gms.internal.ads;

final class zzdmi extends zzdlr {
    private final transient int offset;
    private final transient int size;
    private final transient Object[] zzhas;

    zzdmi(Object[] arr_object, int v, int v1) {
        this.zzhas = arr_object;
        this.offset = v;
        this.size = v1;
    }

    @Override
    public final Object get(int v) {
        zzdlg.zzs(v, this.size);
        return this.zzhas[v * 2 + this.offset];
    }

    @Override
    public final int size() {
        return this.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final boolean zzaub() {
        return true;
    }
}

