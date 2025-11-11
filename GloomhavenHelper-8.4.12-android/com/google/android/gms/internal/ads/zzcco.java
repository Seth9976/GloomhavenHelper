package com.google.android.gms.internal.ads;

import android.view.MotionEvent;

final class zzcco implements zzacf {
    private final zzccp zzftf;

    zzcco(zzccp zzccp0) {
        this.zzftf = zzccp0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzacf
    public final void zzc(MotionEvent motionEvent0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzacf
    public final void zzrh() {
        if(zzccp.zza(this.zzftf) != null) {
            zzccp.zza(this.zzftf).zzfv("_videoMediaView");
        }
    }
}

