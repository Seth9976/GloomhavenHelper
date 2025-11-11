package com.google.android.gms.internal.ads;

final class zzaic implements Runnable {
    private final zzaid zzczr;
    private final String zzczs;

    zzaic(zzaid zzaid0, String s) {
        this.zzczr = zzaid0;
        this.zzczs = s;
    }

    @Override
    public final void run() {
        this.zzczr.zzdc(this.zzczs);
    }
}

