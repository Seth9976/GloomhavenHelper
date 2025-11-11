package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.common.zzb;

public abstract class zzn extends zzb implements zzm {
    public static zzm zzc(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        return iInterface0 instanceof zzm ? ((zzm)iInterface0) : new zzo(iBinder0);
    }
}

