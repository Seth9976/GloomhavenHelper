package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

public final class zzcmb implements zzcly {
    @VisibleForTesting
    private final zzcly zzgbb;
    private final zzdku zzgbc;

    public zzcmb(zzcly zzcly0, zzdku zzdku0) {
        this.zzgbb = zzcly0;
        this.zzgbc = zzdku0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return this.zzgbb.zza(zzdeq0, zzdei0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdnt.zzb(this.zzgbb.zzb(zzdeq0, zzdei0), this.zzgbc, zzazq.zzdxk);
    }
}

