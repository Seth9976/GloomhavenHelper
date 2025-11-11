package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzbep implements Runnable {
    private final Map zzdxc;
    private final zzbeq zzeik;

    zzbep(zzbeq zzbeq0, Map map0) {
        this.zzeik = zzbeq0;
        this.zzdxc = map0;
    }

    @Override
    public final void run() {
        this.zzeik.zzj(this.zzdxc);
    }
}

