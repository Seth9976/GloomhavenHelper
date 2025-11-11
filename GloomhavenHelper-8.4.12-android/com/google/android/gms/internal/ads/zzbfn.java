package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zze;

final class zzbfn implements Runnable {
    private final zzbfo zzeja;

    zzbfn(zzbfo zzbfo0) {
        this.zzeja = zzbfo0;
    }

    @Override
    public final void run() {
        this.zzeja.zzefl.zzaao();
        zze zze0 = this.zzeja.zzefl.zzaab();
        if(zze0 != null) {
            zze0.zzts();
        }
    }
}

