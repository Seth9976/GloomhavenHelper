package com.google.android.gms.internal.ads;

final class zzawe implements Runnable {
    private final zzawb zzdta;

    zzawe(zzawb zzawb0) {
        this.zzdta = zzawb0;
        super();
    }

    @Override
    public final void run() {
        this.zzdta.thread = Thread.currentThread();
        this.zzdta.zztz();
    }
}

