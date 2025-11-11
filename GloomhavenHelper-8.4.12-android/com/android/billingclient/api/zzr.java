package com.android.billingclient.api;

public final class zzr implements Runnable {
    public final ConsumeResponseListener zza;
    public final ConsumeParams zzb;

    public zzr(ConsumeResponseListener consumeResponseListener0, ConsumeParams consumeParams0) {
        this.zza = consumeResponseListener0;
        this.zzb = consumeParams0;
    }

    @Override
    public final void run() {
        String s = this.zzb.getPurchaseToken();
        this.zza.onConsumeResponse(zzak.zzr, s);
    }
}

