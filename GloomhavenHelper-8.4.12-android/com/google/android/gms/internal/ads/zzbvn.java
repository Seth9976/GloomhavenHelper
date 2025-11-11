package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks;

final class zzbvn implements zzbtm {
    static final zzbtm zzfkj;

    static {
        zzbvn.zzfkj = new zzbvn();
    }

    @Override  // com.google.android.gms.internal.ads.zzbtm
    public final void zzp(Object object0) {
        ((VideoLifecycleCallbacks)object0).onVideoPause();
    }
}

