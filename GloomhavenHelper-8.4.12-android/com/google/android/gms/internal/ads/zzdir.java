package com.google.android.gms.internal.ads;

final class zzdir implements Runnable {
    private final String zzczs;
    private final zzdis zzgwf;

    zzdir(zzdis zzdis0, String s) {
        this.zzgwf = zzdis0;
        this.zzczs = s;
    }

    @Override
    public final void run() {
        this.zzgwf.zzgs(this.zzczs);
    }
}

