package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzc extends zzf implements zzd {
    public static zzd zzn(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
        return iInterface0 instanceof zzd ? ((zzd)iInterface0) : new zzb(iBinder0);
    }
}

