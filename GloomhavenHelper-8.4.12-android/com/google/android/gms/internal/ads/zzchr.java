package com.google.android.gms.internal.ads;

final class zzchr implements Runnable {
    private final zzcho zzfwv;
    private final zzahc zzfww;

    zzchr(zzcho zzcho0, zzahc zzahc0) {
        this.zzfwv = zzcho0;
        this.zzfww = zzahc0;
    }

    @Override
    public final void run() {
        this.zzfwv.zzc(this.zzfww);
    }
}

