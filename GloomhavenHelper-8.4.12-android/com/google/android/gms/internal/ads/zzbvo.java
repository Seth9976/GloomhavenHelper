package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks;

final class zzbvo implements zzbtm {
    static final zzbtm zzfkj;

    static {
        zzbvo.zzfkj = new zzbvo();
    }

    @Override  // com.google.android.gms.internal.ads.zzbtm
    public final void zzp(Object object0) {
        ((VideoLifecycleCallbacks)object0).onVideoStart();
    }
}

