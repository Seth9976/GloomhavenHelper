package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzbgi extends zzge implements zzbgf {
    public static zzbgf zzaq(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.measurement.IMeasurementManager");
        return iInterface0 instanceof zzbgf ? ((zzbgf)iInterface0) : new zzbgh(iBinder0);
    }
}

