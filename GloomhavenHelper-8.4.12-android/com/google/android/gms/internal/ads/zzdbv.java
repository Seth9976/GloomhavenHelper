package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

final class zzdbv implements zzdgy {
    public final Executor executor;
    public final String zzbri;
    public final zzuh zzdjt;
    public final zzur zzgif;
    public final zzdcq zzgnu;
    public final zzdcp zzgnv;
    @Nullable
    private final zzdgj zzgnw;

    public zzdbv(zzdcq zzdcq0, zzdcp zzdcp0, zzuh zzuh0, String s, Executor executor0, zzur zzur0, @Nullable zzdgj zzdgj0) {
        this.zzgnu = zzdcq0;
        this.zzgnv = zzdcp0;
        this.zzdjt = zzuh0;
        this.zzbri = s;
        this.executor = executor0;
        this.zzgif = zzur0;
        this.zzgnw = zzdgj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdgy
    public final Executor getExecutor() {
        return this.executor;
    }

    @Override  // com.google.android.gms.internal.ads.zzdgy
    @Nullable
    public final zzdgj zzaql() {
        return this.zzgnw;
    }

    @Override  // com.google.android.gms.internal.ads.zzdgy
    public final zzdgy zzaqm() {
        return new zzdbv(this.zzgnu, this.zzgnv, this.zzdjt, this.zzbri, this.executor, this.zzgif, this.zzgnw);
    }
}

