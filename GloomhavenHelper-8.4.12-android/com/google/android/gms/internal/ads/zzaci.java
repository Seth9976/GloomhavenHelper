package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class zzaci extends zzge implements zzacj {
    public zzaci() {
        super("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 2: {
                String s = this.getText();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 3: {
                List list0 = this.zzrb();
                parcel1.writeNoException();
                parcel1.writeList(list0);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzacj zzm(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
        return iInterface0 instanceof zzacj ? ((zzacj)iInterface0) : new zzacl(iBinder0);
    }
}

