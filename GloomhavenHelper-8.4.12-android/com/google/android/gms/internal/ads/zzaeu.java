package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;
import java.util.HashMap;

public final class zzaeu extends RemoteCreator {
    @VisibleForTesting
    public zzaeu() {
        super("com.google.android.gms.ads.NativeAdViewHolderDelegateCreatorImpl");
    }

    @Override  // com.google.android.gms.dynamic.RemoteCreator
    protected final Object getRemoteCreator(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegateCreator");
        return iInterface0 instanceof zzadd ? ((zzadd)iInterface0) : new zzadc(iBinder0);
    }

    public final zzacy zzb(View view0, HashMap hashMap0, HashMap hashMap1) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
            IObjectWrapper iObjectWrapper1 = ObjectWrapper.wrap(hashMap0);
            IObjectWrapper iObjectWrapper2 = ObjectWrapper.wrap(hashMap1);
            IBinder iBinder0 = ((zzadd)this.getRemoteCreatorInstance(view0.getContext())).zzb(iObjectWrapper0, iObjectWrapper1, iObjectWrapper2);
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
            return iInterface0 instanceof zzacy ? ((zzacy)iInterface0) : new zzada(iBinder0);
        }
        catch(RemoteException | RemoteCreatorException remoteException0) {
            zzazh.zzd("Could not create remote NativeAdViewHolderDelegate.", remoteException0);
            return null;
        }
    }
}

