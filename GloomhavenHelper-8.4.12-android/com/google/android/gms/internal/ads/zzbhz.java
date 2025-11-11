package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzbhz implements zzddz {
    private final zzbhf zzene;
    private Context zzexp;
    private String zzexq;

    private zzbhz(zzbhf zzbhf0) {
        this.zzene = zzbhf0;
        super();
    }

    zzbhz(zzbhf zzbhf0, zzbhi zzbhi0) {
        this(zzbhf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzddz
    public final zzdea zzaew() {
        zzeep.zza(this.zzexp, Context.class);
        return new zzbic(this.zzene, this.zzexp, this.zzexq, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzddz
    public final zzddz zzbx(Context context0) {
        this.zzexp = (Context)zzeep.checkNotNull(context0);
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzddz
    public final zzddz zzfs(String s) {
        this.zzexq = s;
        return this;
    }
}

