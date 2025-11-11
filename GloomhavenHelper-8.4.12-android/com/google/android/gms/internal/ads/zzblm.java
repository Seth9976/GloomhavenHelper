package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;

final class zzblm implements zzbrn {
    private final Context zzcgz;
    private final zzazo zzfgo;
    private final zzdei zzfgp;
    private final zzdeu zzfgq;

    zzblm(Context context0, zzazo zzazo0, zzdei zzdei0, zzdeu zzdeu0) {
        this.zzcgz = context0;
        this.zzfgo = zzazo0;
        this.zzfgp = zzdei0;
        this.zzfgq = zzdeu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        zzq.zzlf().zzb(this.zzcgz, this.zzfgo.zzbmj, this.zzfgp.zzgpv.toString(), this.zzfgq.zzgqr);
    }
}

