package com.google.android.gms.internal.ads;

final class zzdmb extends zzdlr {
    private final transient int size;
    static final zzdlr zzhap;
    private final transient Object[] zzhaq;

    static {
        zzdmb.zzhap = new zzdmb(new Object[0], 0);
    }

    zzdmb(Object[] arr_object, int v) {
        this.zzhaq = arr_object;
        this.size = v;
    }

    @Override
    public final Object get(int v) {
        zzdlg.zzs(v, this.size);
        return this.zzhaq[v];
    }

    @Override
    public final int size() {
        return this.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlr
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzhaq, 0, arr_object, v, this.size);
        return v + this.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    final Object[] zzatx() {
        return this.zzhaq;
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
}

