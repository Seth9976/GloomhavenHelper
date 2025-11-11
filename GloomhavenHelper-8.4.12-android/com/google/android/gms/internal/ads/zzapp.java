package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public final class zzapp extends zzge implements zzapq {
    public static zzapq zzai(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
        return iInterface0 instanceof zzapq ? ((zzapq)iInterface0) : new zzaps(iBinder0);
    }
}

