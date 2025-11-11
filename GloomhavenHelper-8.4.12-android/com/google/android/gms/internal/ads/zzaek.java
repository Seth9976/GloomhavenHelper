package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzaek extends zzge implements zzael {
    public zzaek() {
        super("com.google.android.gms.ads.internal.formats.client.IShouldDelayBannerRenderingListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 2) {
            boolean z = this.zzm(Stub.asInterface(parcel0.readStrongBinder()));
            parcel1.writeNoException();
            zzgd.writeBoolean(parcel1, z);
            return true;
        }
        return false;
    }

    public static zzael zzy(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IShouldDelayBannerRenderingListener");
        return iInterface0 instanceof zzael ? ((zzael)iInterface0) : new zzaen(iBinder0);
    }
}

