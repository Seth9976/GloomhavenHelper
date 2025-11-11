package com.android.billingclient.api;

public final class zzs implements Runnable {
    public final PriceChangeConfirmationListener zza;
    public final BillingResult zzb;

    public zzs(PriceChangeConfirmationListener priceChangeConfirmationListener0, BillingResult billingResult0) {
        this.zza = priceChangeConfirmationListener0;
        this.zzb = billingResult0;
    }

    @Override
    public final void run() {
        this.zza.onPriceChangeConfirmationResult(this.zzb);
    }
}

