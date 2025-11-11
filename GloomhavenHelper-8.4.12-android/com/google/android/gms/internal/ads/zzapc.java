package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;

public final class zzapc extends RemoteCreator {
    public zzapc() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    @Override  // com.google.android.gms.dynamic.RemoteCreator
    protected final Object getRemoteCreator(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        return iInterface0 instanceof zzapg ? ((zzapg)iInterface0) : new zzapf(iBinder0);
    }

    public final zzapb zzc(Activity activity0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(activity0);
            IBinder iBinder0 = ((zzapg)this.getRemoteCreatorInstance(activity0)).zzae(iObjectWrapper0);
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            return iInterface0 instanceof zzapb ? ((zzapb)iInterface0) : new zzapd(iBinder0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Could not create remote AdOverlay.", remoteException0);
            return null;
        }
        catch(RemoteCreatorException remoteCreator$RemoteCreatorException0) {
            zzazh.zzd("Could not create remote AdOverlay.", remoteCreator$RemoteCreatorException0);
            return null;
        }
    }
}

