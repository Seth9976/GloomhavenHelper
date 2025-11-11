package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks;

final class zzbvp implements zzbtm {
    static final zzbtm zzfkj;

    static {
        zzbvp.zzfkj = new zzbvp();
    }

    @Override  // com.google.android.gms.internal.ads.zzbtm
    public final void zzp(Object object0) {
        ((VideoLifecycleCallbacks)object0).onVideoStart();
    }
}

