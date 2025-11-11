package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public final class zzapo extends zzge implements zzapl {
    public static zzapl zzah(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseListener");
        return iInterface0 instanceof zzapl ? ((zzapl)iInterface0) : new zzapn(iBinder0);
    }
}

