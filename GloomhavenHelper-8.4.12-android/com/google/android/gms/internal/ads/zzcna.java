package com.google.android.gms.internal.ads;

import android.net.Uri;

final class zzcna implements zzdng {
    private final zzcnb zzgbr;
    private final Uri zzgbs;
    private final zzdeq zzgbt;
    private final zzdei zzgbu;

    zzcna(zzcnb zzcnb0, Uri uri0, zzdeq zzdeq0, zzdei zzdei0) {
        this.zzgbr = zzcnb0;
        this.zzgbs = uri0;
        this.zzgbt = zzdeq0;
        this.zzgbu = zzdei0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        return this.zzgbr.zza(this.zzgbs, this.zzgbt, this.zzgbu, object0);
    }
}

