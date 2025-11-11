package com.android.billingclient.api;

public final class zzj implements Runnable {
    public final AcknowledgePurchaseResponseListener zza;

    public zzj(AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener0) {
        this.zza = acknowledgePurchaseResponseListener0;
    }

    @Override
    public final void run() {
        this.zza.onAcknowledgePurchaseResponse(zzak.zzr);
    }
}

