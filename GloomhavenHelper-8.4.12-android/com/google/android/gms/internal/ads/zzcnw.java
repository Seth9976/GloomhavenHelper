package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzcnw implements zzbxb {
    private final zzcmd zzgbo;

    zzcnw(zzcmd zzcmd0) {
        this.zzgbo = zzcmd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbxb
    public final void zza(boolean z, Context context0) {
        try {
            ((zzdfb)this.zzgbo.zzdel).setImmersiveMode(z);
            ((zzdfb)this.zzgbo.zzdel).showInterstitial();
        }
        catch(zzdfa unused_ex) {
            zzawf.zzez("Cannot show interstitial.");
        }
    }
}

