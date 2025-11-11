package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzcgg implements zzbqm, zzbrn, zzbsn {
    private final zzcgq zzfvp;
    private final zzcgx zzfvq;

    public zzcgg(zzcgq zzcgq0, zzcgx zzcgx0) {
        this.zzfvp = zzcgq0;
        this.zzfvq = zzcgx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        Map map0 = this.zzfvp.zzqv();
        this.zzfvq.zzm(map0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        Map map0 = this.zzfvp.zzqv();
        this.zzfvq.zzm(map0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzb(zzdeq zzdeq0) {
        this.zzfvp.zzc(zzdeq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzd(zzaqx zzaqx0) {
        this.zzfvp.zzi(zzaqx0.zzdmz);
    }
}

