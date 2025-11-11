package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;

final class zzcth implements zzdng {
    private final zzaqx zzfks;

    zzcth(zzaqx zzaqx0) {
        this.zzfks = zzaqx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        return zzdnt.zzaj(new zzctj(new JsonReader(new InputStreamReader(((InputStream)object0)))).zzn(this.zzfks.zzdmz));
    }
}

