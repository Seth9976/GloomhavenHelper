package com.google.android.gms.internal.ads;

final class zzagu implements Runnable {
    private final zzagt zzcze;

    zzagu(zzagt zzagt0) {
        this.zzcze = zzagt0;
        super();
    }

    @Override
    public final void run() {
        this.zzcze.disconnect();
    }
}

