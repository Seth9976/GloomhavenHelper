package com.google.android.gms.internal.ads;

final class zzcgw implements Runnable {
    private final String zzczs;
    private final zzcgx zzfwc;

    zzcgw(zzcgx zzcgx0, String s) {
        this.zzfwc = zzcgx0;
        this.zzczs = s;
    }

    @Override
    public final void run() {
        this.zzfwc.zzgd(this.zzczs);
    }
}

