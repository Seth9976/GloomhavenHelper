package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzbpj implements zzdku {
    private final Context zzcgz;
    private final zzazo zzfgo;
    private final zzdeu zzfjl;

    zzbpj(Context context0, zzazo zzazo0, zzdeu zzdeu0) {
        this.zzcgz = context0;
        this.zzfgo = zzazo0;
        this.zzfjl = zzdeu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    public final Object apply(Object object0) {
        zzaxg zzaxg0 = new zzaxg(this.zzcgz);
        zzaxg0.zzep(((zzdei)object0).zzdlu);
        zzaxg0.zzeq(((zzdei)object0).zzgpv.toString());
        zzaxg0.zzx(this.zzfgo.zzbmj);
        zzaxg0.setAdUnitId(this.zzfjl.zzgqr);
        return zzaxg0;
    }
}

