package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzaee extends zzge implements zzaef {
    public zzaee() {
        super("com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.zza(zzvw.zzc(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()));
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzaef zzw(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener");
        return iInterface0 instanceof zzaef ? ((zzaef)iInterface0) : new zzaeh(iBinder0);
    }
}

