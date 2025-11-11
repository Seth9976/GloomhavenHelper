package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzi;

final class zzcea implements zzi {
    private final zzceb zzfug;

    zzcea(zzceb zzceb0) {
        this.zzfug = zzceb0;
        super();
    }

    @Override  // com.google.android.gms.ads.internal.zzi
    public final void zzka() {
        this.zzfug.zzfuh.onPause();
    }

    @Override  // com.google.android.gms.ads.internal.zzi
    public final void zzkb() {
        this.zzfug.zzfuh.onResume();
    }
}

