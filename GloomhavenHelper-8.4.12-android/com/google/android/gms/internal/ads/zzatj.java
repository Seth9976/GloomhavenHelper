package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzatj {
    public static zzast zzd(Context context0, String s, zzalk zzalk0) {
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
        try {
            IBinder iBinder0 = ((zzasz)zzazk.zza(context0, "com.google.android.gms.ads.rewarded.ChimeraRewardedAdCreatorImpl", zzatm.zzbun)).zzd(iObjectWrapper0, s, zzalk0, 20089000);
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
            return iInterface0 instanceof zzast ? ((zzast)iInterface0) : new zzasv(iBinder0);
        }
        catch(zzazm | RemoteException zzazm0) {
            zzazh.zze("#007 Could not call remote method.", zzazm0);
            return null;
        }
    }
}

