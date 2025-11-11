package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;

public final class zzuf extends RemoteCreator {
    @VisibleForTesting
    public zzuf() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    @Override  // com.google.android.gms.dynamic.RemoteCreator
    protected final Object getRemoteCreator(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return iInterface0 instanceof zzvy ? ((zzvy)iInterface0) : new zzwb(iBinder0);
    }

    public final zzvx zza(Context context0, zzuk zzuk0, String s, zzalk zzalk0, int v) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            IBinder iBinder0 = ((zzvy)this.getRemoteCreatorInstance(context0)).zza(iObjectWrapper0, zzuk0, s, zzalk0, 20089000, v);
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            return iInterface0 instanceof zzvx ? ((zzvx)iInterface0) : new zzvz(iBinder0);
        }
        catch(RemoteException | RemoteCreatorException remoteException0) {
            zzazh.zzb("Could not create remote AdManager.", remoteException0);
            return null;
        }
    }
}

