package com.google.android.gms.internal.ads;

final class zzaif implements Runnable {
    private final zzaid zzczr;
    private final String zzczs;

    zzaif(zzaid zzaid0, String s) {
        this.zzczr = zzaid0;
        this.zzczs = s;
    }

    @Override
    public final void run() {
        this.zzczr.zzdb(this.zzczs);
    }
}

