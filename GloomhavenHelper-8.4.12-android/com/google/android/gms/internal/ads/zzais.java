package com.google.android.gms.internal.ads;

final class zzais implements Runnable {
    private final String zzczs;
    private final zzait zzdad;

    zzais(zzait zzait0, String s) {
        this.zzdad = zzait0;
        this.zzczs = s;
    }

    @Override
    public final void run() {
        this.zzdad.zzde(this.zzczs);
    }
}

