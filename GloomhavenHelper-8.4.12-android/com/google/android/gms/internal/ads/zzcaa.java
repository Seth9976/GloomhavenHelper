package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzcaa implements zzeej {
    private final zzeew zzepo;
    private final zzeew zzfex;

    public zzcaa(zzeew zzeew0, zzeew zzeew1) {
        this.zzepo = zzeew0;
        this.zzfex = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbzy(((zzccv)this.zzepo.get()), ((Clock)this.zzfex.get()));
    }
}

