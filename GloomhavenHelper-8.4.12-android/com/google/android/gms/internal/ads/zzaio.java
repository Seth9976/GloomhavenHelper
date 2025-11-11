package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzaio implements Runnable {
    private final String zzczz;

    zzaio(String s) {
        this.zzczz = s;
    }

    @Override
    public final void run() {
        zzq.zzkz().zzve().zzcs(this.zzczz.substring(1));
    }
}

