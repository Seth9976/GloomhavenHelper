package com.google.android.gms.internal.ads;

final class zzdxv {
    private final byte[] buffer;
    private final zzdyi zzhok;

    private zzdxv(int v) {
        this.buffer = new byte[v];
        this.zzhok = zzdyi.zzv(this.buffer);
    }

    zzdxv(int v, zzdxq zzdxq0) {
        this(v);
    }

    public final zzdxn zzbaz() {
        this.zzhok.zzbcc();
        return new zzdxx(this.buffer);
    }

    public final zzdyi zzbba() {
        return this.zzhok;
    }
}

