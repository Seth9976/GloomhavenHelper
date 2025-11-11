package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;

public final class zzuc extends RemoteCreator {
    public zzuc() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    @Override  // com.google.android.gms.dynamic.RemoteCreator
    protected final Object getRemoteCreator(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        return iInterface0 instanceof zzvv ? ((zzvv)iInterface0) : new zzvu(iBinder0);
    }

    public final zzvq zza(Context context0, String s, zzalk zzalk0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            IBinder iBinder0 = ((zzvv)this.getRemoteCreatorInstance(context0)).zzc(iObjectWrapper0, s, zzalk0, 20089000);
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return iInterface0 instanceof zzvq ? ((zzvq)iInterface0) : new zzvs(iBinder0);
        }
        catch(RemoteException | RemoteCreatorException remoteException0) {
            zzazh.zzd("Could not create remote builder for AdLoader.", remoteException0);
            return null;
        }
    }
}

