package com.google.android.gms.internal.ads;

public final class zzcwb implements zzcye {
    private final zzdeg zzfdn;
    private final zzdoe zzfrv;

    public zzcwb(zzdoe zzdoe0, zzdeg zzdeg0) {
        this.zzfrv = zzdoe0;
        this.zzfdn = zzdeg0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcwe zzcwe0 = () -> new zzcwc(this.zzfdn);
        return this.zzfrv.zzd(zzcwe0);
    }

    // 检测为 Lambda 实现
    final zzcwc zzaph() throws Exception [...]
}

