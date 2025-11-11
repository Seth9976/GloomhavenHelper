package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

public final class zzdce {
    public static void zza(AtomicReference atomicReference0, zzdcd zzdcd0) {
        Object object0 = atomicReference0.get();
        if(object0 == null) {
            return;
        }
        try {
            zzdcd0.zzr(object0);
        }
        catch(RemoteException remoteException0) {
            zzawf.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

