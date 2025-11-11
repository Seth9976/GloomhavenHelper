package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzchd implements zzeej {
    private final zzeew zzfex;

    public zzchd(zzeew zzeew0) {
        this.zzfex = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcha(((Clock)this.zzfex.get()));
    }
}

