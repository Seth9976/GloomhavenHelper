package com.google.android.gms.internal.ads;

import android.view.Surface;

final class zzpl implements Runnable {
    private final zzpf zzbjp;
    private final Surface zzbjs;

    zzpl(zzpf zzpf0, Surface surface0) {
        this.zzbjp = zzpf0;
        this.zzbjs = surface0;
        super();
    }

    @Override
    public final void run() {
        this.zzbjp.zzbjo.zzb(this.zzbjs);
    }
}

