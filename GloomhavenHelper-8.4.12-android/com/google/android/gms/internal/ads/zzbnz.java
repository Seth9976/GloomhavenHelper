package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzbnz implements zzp {
    private final zzbrc zzfil;
    private AtomicBoolean zzfim;

    public zzbnz(zzbrc zzbrc0) {
        this.zzfim = new AtomicBoolean(false);
        this.zzfil = zzbrc0;
    }

    public final boolean isClosed() {
        return this.zzfim.get();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
        this.zzfim.set(true);
        this.zzfil.onAdClosed();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
        this.zzfil.onAdOpened();
    }
}

