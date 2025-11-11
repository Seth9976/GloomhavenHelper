package com.google.android.gms.internal.ads;

final class zzaiw implements Runnable {
    private final String zzdah;
    private final zzait zzdai;

    zzaiw(zzait zzait0, String s) {
        this.zzdai = zzait0;
        this.zzdah = s;
        super();
    }

    @Override
    public final void run() {
        this.zzdai.zzdae.loadData(this.zzdah, "text/html", "UTF-8");
    }
}

