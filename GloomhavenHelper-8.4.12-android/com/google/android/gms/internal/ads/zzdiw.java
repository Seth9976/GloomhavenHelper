package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzdiw extends zzge implements zzdit {
    public static zzdit zzar(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.omid.IOmid");
        return iInterface0 instanceof zzdit ? ((zzdit)iInterface0) : new zzdiv(iBinder0);
    }
}

