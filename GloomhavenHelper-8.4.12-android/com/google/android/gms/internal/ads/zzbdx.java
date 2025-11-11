package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zze;

final class zzbdx implements Runnable {
    private final zzbdy zzefk;

    zzbdx(zzbdy zzbdy0) {
        this.zzefk = zzbdy0;
    }

    @Override
    public final void run() {
        this.zzefk.zzefl.zzaao();
        zze zze0 = this.zzefk.zzefl.zzaab();
        if(zze0 != null) {
            zze0.zzts();
        }
    }
}

