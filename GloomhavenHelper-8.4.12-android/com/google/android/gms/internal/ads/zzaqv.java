package com.google.android.gms.internal.ads;

import java.io.OutputStream;

final class zzaqv implements Runnable {
    private final OutputStream zzdmu;
    private final byte[] zzdmv;

    zzaqv(OutputStream outputStream0, byte[] arr_b) {
        this.zzdmu = outputStream0;
        this.zzdmv = arr_b;
    }

    @Override
    public final void run() {
        zzaqw.zza(this.zzdmu, this.zzdmv);
    }
}

