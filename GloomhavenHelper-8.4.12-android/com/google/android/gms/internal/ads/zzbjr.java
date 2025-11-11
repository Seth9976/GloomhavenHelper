package com.google.android.gms.internal.ads;

public final class zzbjr implements zzbqm {
    private final zzdeq zzfdp;
    private final zzdis zzfdr;
    private final zzdek zzfdv;

    public zzbjr(zzdeq zzdeq0, zzdis zzdis0) {
        this.zzfdp = zzdeq0;
        this.zzfdr = zzdis0;
        this.zzfdv = zzdeq0.zzgqm.zzgqj;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        this.zzfdr.zza(this.zzfdp, null, this.zzfdv.zzdds);
    }
}

