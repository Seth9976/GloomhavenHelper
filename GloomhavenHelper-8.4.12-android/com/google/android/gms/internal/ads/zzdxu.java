package com.google.android.gms.internal.ads;

final class zzdxu extends zzdxx {
    private final int zzhoi;
    private final int zzhoj;

    zzdxu(byte[] arr_b, int v, int v1) {
        super(arr_b);
        zzdxu.zzi(v, v + v1, arr_b.length);
        this.zzhoi = v;
        this.zzhoj = v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxx
    public final int size() {
        return this.zzhoj;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxx
    protected final void zzb(byte[] arr_b, int v, int v1, int v2) {
        System.arraycopy(this.zzhol, this.zzbay() + v, arr_b, v1, v2);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxx
    protected final int zzbay() {
        return this.zzhoi;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxx
    public final byte zzfe(int v) {
        zzdxu.zzaa(v, this.size());
        return this.zzhol[this.zzhoi + v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxx
    final byte zzff(int v) {
        return this.zzhol[this.zzhoi + v];
    }
}

