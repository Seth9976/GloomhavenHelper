package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;

public final class zzaev extends RemoteCreator {
    @VisibleForTesting
    public zzaev() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    @Override  // com.google.android.gms.dynamic.RemoteCreator
    protected final Object getRemoteCreator(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
        return iInterface0 instanceof zzacw ? ((zzacw)iInterface0) : new zzacz(iBinder0);
    }

    public final zzacv zzb(Context context0, FrameLayout frameLayout0, FrameLayout frameLayout1) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            IObjectWrapper iObjectWrapper1 = ObjectWrapper.wrap(frameLayout0);
            IObjectWrapper iObjectWrapper2 = ObjectWrapper.wrap(frameLayout1);
            IBinder iBinder0 = ((zzacw)this.getRemoteCreatorInstance(context0)).zza(iObjectWrapper0, iObjectWrapper1, iObjectWrapper2, 20089000);
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
            return iInterface0 instanceof zzacv ? ((zzacv)iInterface0) : new zzacx(iBinder0);
        }
        catch(RemoteException | RemoteCreatorException remoteException0) {
            zzazh.zzd("Could not create remote NativeAdViewDelegate.", remoteException0);
            return null;
        }
    }
}

