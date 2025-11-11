package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

final class zzaoj implements zzp {
    private final zzaog zzdgc;

    zzaoj(zzaog zzaog0) {
        this.zzdgc = zzaog0;
        super();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
        zzazh.zzeb("AdMobCustomTabsAdapter overlay is paused.");
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
        zzazh.zzeb("AdMobCustomTabsAdapter overlay is resumed.");
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
        zzazh.zzeb("AdMobCustomTabsAdapter overlay is closed.");
        zzaog.zza(this.zzdgc).onAdClosed(this.zzdgc);
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
        zzazh.zzeb("Opening AdMobCustomTabsAdapter overlay.");
        zzaog.zza(this.zzdgc).onAdOpened(this.zzdgc);
    }
}

