package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzp;

public final class zzu implements Runnable {
    public final PurchasesResponseListener zza;

    public zzu(PurchasesResponseListener purchasesResponseListener0) {
        this.zza = purchasesResponseListener0;
    }

    @Override
    public final void run() {
        this.zza.onQueryPurchasesResponse(zzak.zzr, zzp.zzg());
    }
}

