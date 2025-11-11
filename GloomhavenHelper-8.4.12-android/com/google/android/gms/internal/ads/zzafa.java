package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class zzafa implements Runnable {
    private final PublisherAdView zzcxo;
    private final zzvx zzcxp;
    private final zzafb zzcxq;

    zzafa(zzafb zzafb0, PublisherAdView publisherAdView0, zzvx zzvx0) {
        this.zzcxq = zzafb0;
        this.zzcxo = publisherAdView0;
        this.zzcxp = zzvx0;
        super();
    }

    @Override
    public final void run() {
        if(this.zzcxo.zza(this.zzcxp)) {
            this.zzcxq.zzcxr.onPublisherAdViewLoaded(this.zzcxo);
            return;
        }
        zzazh.zzfa("Could not bind.");
    }
}

