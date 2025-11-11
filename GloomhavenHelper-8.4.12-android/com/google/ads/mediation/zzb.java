package com.google.ads.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.reward.AdMetadataListener;

final class zzb extends AdMetadataListener {
    private final AbstractAdViewAdapter zzlr;

    zzb(AbstractAdViewAdapter abstractAdViewAdapter0) {
        this.zzlr = abstractAdViewAdapter0;
        super();
    }

    @Override  // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        if(AbstractAdViewAdapter.zzb(this.zzlr) != null && AbstractAdViewAdapter.zza(this.zzlr) != null) {
            Bundle bundle0 = AbstractAdViewAdapter.zzb(this.zzlr).getAdMetadata();
            AbstractAdViewAdapter.zza(this.zzlr).zzb(bundle0);
        }
    }
}

