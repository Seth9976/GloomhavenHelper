package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

final class zzm implements Runnable {
    private final zzj zzdiq;
    private final Drawable zzdir;

    zzm(zzj zzj0, Drawable drawable0) {
        this.zzdiq = zzj0;
        this.zzdir = drawable0;
    }

    @Override
    public final void run() {
        this.zzdiq.zzdio.zzzo.getWindow().setBackgroundDrawable(this.zzdir);
    }
}

