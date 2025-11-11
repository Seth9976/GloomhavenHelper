package com.android.billingclient.api;

public final class zzv implements Runnable {
    public final SkuDetailsResponseListener zza;

    public zzv(SkuDetailsResponseListener skuDetailsResponseListener0) {
        this.zza = skuDetailsResponseListener0;
    }

    @Override
    public final void run() {
        this.zza.onSkuDetailsResponse(zzak.zzr, null);
    }
}

