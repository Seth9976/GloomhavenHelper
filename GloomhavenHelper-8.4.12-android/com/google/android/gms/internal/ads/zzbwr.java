package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

public final class zzbwr implements zzp {
    private final zzbrv zzfmd;
    private final zzbur zzfme;

    public zzbwr(zzbrv zzbrv0, zzbur zzbur0) {
        this.zzfmd = zzbrv0;
        this.zzfme = zzbur0;
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
        this.zzfmd.onPause();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
        this.zzfmd.onResume();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
        this.zzfmd.zztj();
        this.zzfme.onHide();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
        this.zzfmd.zztk();
        this.zzfme.zzaiv();
    }
}

