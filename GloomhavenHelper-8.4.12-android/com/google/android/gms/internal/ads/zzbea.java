package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzbea implements Runnable {
    private final String zzczz;

    zzbea(String s) {
        this.zzczz = s;
    }

    @Override
    public final void run() {
        zzq.zzkz().zzve().zzcs(this.zzczz.substring(1));
    }
}

