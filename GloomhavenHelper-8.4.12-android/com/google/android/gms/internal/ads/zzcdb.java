package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzcdb implements zzdnu {
    private final String zzftp;
    private final Map zzftq;

    zzcdb(zzccv zzccv0, String s, Map map0) {
        this.zzftp = s;
        this.zzftq = map0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        ((zzbdv)object0).zza(this.zzftp, this.zzftq);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
    }
}

