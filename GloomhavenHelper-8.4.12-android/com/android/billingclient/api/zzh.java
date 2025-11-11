package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

final class zzh {
    private final Context zza;
    private final zzg zzb;

    zzh(Context context0, PurchasesUpdatedListener purchasesUpdatedListener0) {
        this.zza = context0;
        this.zzb = new zzg(this, purchasesUpdatedListener0, null);
    }

    final PurchasesUpdatedListener zzb() {
        return this.zzb.zzb;
    }

    final void zzc() {
        this.zzb.zzc(this.zza);
    }

    final void zzd() {
        IntentFilter intentFilter0 = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        this.zzb.zzb(this.zza, intentFilter0);
    }
}

