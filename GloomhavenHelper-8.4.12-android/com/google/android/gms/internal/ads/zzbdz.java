package com.google.android.gms.internal.ads;

import android.view.View;

final class zzbdz implements Runnable {
    private final View val$view;
    private final zzaub zzegb;
    private final int zzegc;
    private final zzbdy zzegd;

    zzbdz(zzbdy zzbdy0, View view0, zzaub zzaub0, int v) {
        this.zzegd = zzbdy0;
        this.val$view = view0;
        this.zzegb = zzaub0;
        this.zzegc = v;
        super();
    }

    @Override
    public final void run() {
        this.zzegd.zza(this.val$view, this.zzegb, this.zzegc - 1);
    }
}

