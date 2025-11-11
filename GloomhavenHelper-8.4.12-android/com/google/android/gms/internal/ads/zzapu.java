package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public final class zzapu extends zzge implements zzapr {
    public static zzapr zzaj(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        return iInterface0 instanceof zzapr ? ((zzapr)iInterface0) : new zzapt(iBinder0);
    }
}

