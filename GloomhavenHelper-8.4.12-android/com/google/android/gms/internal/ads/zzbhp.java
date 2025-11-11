package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzbhp implements zzdbi {
    private final zzbhf zzene;
    private Context zzexp;
    private String zzexq;

    private zzbhp(zzbhf zzbhf0) {
        this.zzene = zzbhf0;
        super();
    }

    zzbhp(zzbhf zzbhf0, zzbhi zzbhi0) {
        this(zzbhf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdbi
    public final zzdbf zzaei() {
        zzeep.zza(this.zzexp, Context.class);
        zzeep.zza(this.zzexq, String.class);
        return new zzbhs(this.zzene, this.zzexp, this.zzexq, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdbi
    public final zzdbi zzbw(Context context0) {
        this.zzexp = (Context)zzeep.checkNotNull(context0);
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzdbi
    public final zzdbi zzfr(String s) {
        this.zzexq = (String)zzeep.checkNotNull(s);
        return this;
    }
}

