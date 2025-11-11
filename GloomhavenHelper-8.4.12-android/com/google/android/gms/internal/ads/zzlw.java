package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzlw implements Runnable {
    private final zzls zzbab;
    private final IOException zzbbh;

    zzlw(zzls zzls0, IOException iOException0) {
        this.zzbab = zzls0;
        this.zzbbh = iOException0;
        super();
    }

    @Override
    public final void run() {
        zzls.zze(this.zzbab).zzb(this.zzbbh);
    }
}

