package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

final class zzatm implements zzazj {
    static final zzazj zzbun;

    static {
        zzatm.zzbun = new zzatm();
    }

    @Override  // com.google.android.gms.internal.ads.zzazj
    public final Object apply(Object object0) {
        if(((IBinder)object0) == null) {
            return null;
        }
        IInterface iInterface0 = ((IBinder)object0).queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCreator");
        return iInterface0 instanceof zzasz ? ((zzasz)iInterface0) : new zzatc(((IBinder)object0));
    }
}

