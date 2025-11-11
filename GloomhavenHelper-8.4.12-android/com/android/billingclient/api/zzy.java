package com.android.billingclient.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.gms.internal.play_billing.zza;

final class zzy extends ResultReceiver {
    final PriceChangeConfirmationListener zza;

    zzy(BillingClientImpl billingClientImpl0, Handler handler0, PriceChangeConfirmationListener priceChangeConfirmationListener0) {
        this.zza = priceChangeConfirmationListener0;
        super(handler0);
    }

    @Override  // android.os.ResultReceiver
    public final void onReceiveResult(int v, Bundle bundle0) {
        Builder billingResult$Builder0 = BillingResult.newBuilder();
        billingResult$Builder0.setResponseCode(v);
        billingResult$Builder0.setDebugMessage(zza.zzh(bundle0, "BillingClient"));
        BillingResult billingResult0 = billingResult$Builder0.build();
        this.zza.onPriceChangeConfirmationResult(billingResult0);
    }
}

