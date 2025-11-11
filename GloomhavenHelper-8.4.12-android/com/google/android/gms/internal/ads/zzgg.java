package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzgg extends zzge implements zzgh {
    public static zzgh zza(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.clearcut.IClearcut");
        return iInterface0 instanceof zzgh ? ((zzgh)iInterface0) : new zzgi(iBinder0);
    }
}

