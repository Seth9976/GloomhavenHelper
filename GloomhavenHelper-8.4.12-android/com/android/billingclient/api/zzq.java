package com.android.billingclient.api;

public final class zzq implements Runnable {
    public final BillingClientImpl zza;
    public final BillingResult zzb;

    public zzq(BillingClientImpl billingClientImpl0, BillingResult billingResult0) {
        this.zza = billingClientImpl0;
        this.zzb = billingResult0;
    }

    @Override
    public final void run() {
        this.zza.zzs(this.zzb);
    }
}

