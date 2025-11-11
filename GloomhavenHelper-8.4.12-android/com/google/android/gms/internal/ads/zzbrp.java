package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;

final class zzbrp implements zzbtm {
    static final zzbtm zzfkj;

    static {
        zzbrp.zzfkj = new zzbrp();
    }

    @Override  // com.google.android.gms.internal.ads.zzbtm
    public final void zzp(Object object0) {
        ((AdMetadataListener)object0).onAdMetadataChanged();
    }
}

