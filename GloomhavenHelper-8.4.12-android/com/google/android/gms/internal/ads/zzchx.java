package com.google.android.gms.internal.ads;

import java.util.List;

final class zzchx implements Runnable {
    private final String zzegh;
    private final zzcho zzfwv;
    private final zzdfb zzfxa;
    private final zzahb zzfxb;
    private final List zzfxc;

    zzchx(zzcho zzcho0, zzdfb zzdfb0, zzahb zzahb0, List list0, String s) {
        this.zzfwv = zzcho0;
        this.zzfxa = zzdfb0;
        this.zzfxb = zzahb0;
        this.zzfxc = list0;
        this.zzegh = s;
    }

    @Override
    public final void run() {
        this.zzfwv.zza(this.zzfxa, this.zzfxb, this.zzfxc, this.zzegh);
    }
}

