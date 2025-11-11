package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

final class zzbeb implements zzp {
    private zzp zzdit;
    private zzbdv zzege;

    public zzbeb(zzbdv zzbdv0, zzp zzp0) {
        this.zzege = zzbdv0;
        this.zzdit = zzp0;
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
        this.zzdit.zztj();
        this.zzege.zzzy();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
        this.zzdit.zztk();
        this.zzege.zztw();
    }
}

