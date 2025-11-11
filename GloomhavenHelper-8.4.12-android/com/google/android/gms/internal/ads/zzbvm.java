package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks;

final class zzbvm implements zzbtm {
    static final zzbtm zzfkj;

    static {
        zzbvm.zzfkj = new zzbvm();
    }

    @Override  // com.google.android.gms.internal.ads.zzbtm
    public final void zzp(Object object0) {
        ((VideoLifecycleCallbacks)object0).onVideoEnd();
    }
}

