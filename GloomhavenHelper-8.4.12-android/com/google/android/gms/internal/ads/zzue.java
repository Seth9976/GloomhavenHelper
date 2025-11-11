package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;

public final class zzue extends zzwd {
    private final AdMetadataListener zzccl;

    public zzue(AdMetadataListener adMetadataListener0) {
        this.zzccl = adMetadataListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwa
    public final void onAdMetadataChanged() {
        AdMetadataListener adMetadataListener0 = this.zzccl;
        if(adMetadataListener0 != null) {
            adMetadataListener0.onAdMetadataChanged();
        }
    }
}

