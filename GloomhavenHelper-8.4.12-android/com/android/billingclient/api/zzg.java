package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.internal.play_billing.zza;
import java.util.List;

final class zzg extends BroadcastReceiver {
    final zzh zza;
    private final PurchasesUpdatedListener zzb;
    private boolean zzc;

    zzg(zzh zzh0, PurchasesUpdatedListener purchasesUpdatedListener0, zzf zzf0) {
        this.zza = zzh0;
        super();
        this.zzb = purchasesUpdatedListener0;
    }

    @Override  // android.content.BroadcastReceiver
    public final void onReceive(Context context0, Intent intent0) {
        BillingResult billingResult0 = zza.zzg(intent0, "BillingBroadcastManager");
        List list0 = zza.zzi(intent0.getExtras());
        this.zzb.onPurchasesUpdated(billingResult0, list0);
    }

    public final void zzb(Context context0, IntentFilter intentFilter0) {
        if(!this.zzc) {
            context0.registerReceiver(this.zza.zzb, intentFilter0);
            this.zzc = true;
        }
    }

    public final void zzc(Context context0) {
        if(this.zzc) {
            context0.unregisterReceiver(this.zza.zzb);
            this.zzc = false;
            return;
        }
        zza.zzk("BillingBroadcastManager", "Receiver is not registered.");
    }
}

