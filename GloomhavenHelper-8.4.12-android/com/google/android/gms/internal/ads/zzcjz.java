package com.google.android.gms.internal.ads;

import java.io.InputStream;

final class zzcjz implements zzdng {
    private final zzaqx zzfks;

    zzcjz(zzaqx zzaqx0) {
        this.zzfks = zzaqx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        this.zzfks.zzdnd = new String(zzdmo.toByteArray(((InputStream)object0)), zzdks.UTF_8);
        return zzdnt.zzaj(this.zzfks);
    }
}

