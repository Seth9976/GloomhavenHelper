package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzaao extends zzge implements zzaap {
    public zzaao() {
        super("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                String s = this.zzqx();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 2: {
                String s1 = this.getContent();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            case 3: {
                this.zzn(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                this.recordClick();
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                this.recordImpression();
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

