package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd.UnconfirmedClickListener;

public final class zzafc extends zzaep {
    private final UnconfirmedClickListener zzcxs;

    public zzafc(UnconfirmedClickListener unifiedNativeAd$UnconfirmedClickListener0) {
        this.zzcxs = unifiedNativeAd$UnconfirmedClickListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaem
    public final void onUnconfirmedClickCancelled() {
        this.zzcxs.onUnconfirmedClickCancelled();
    }

    @Override  // com.google.android.gms.internal.ads.zzaem
    public final void onUnconfirmedClickReceived(String s) {
        this.zzcxs.onUnconfirmedClickReceived(s);
    }
}

