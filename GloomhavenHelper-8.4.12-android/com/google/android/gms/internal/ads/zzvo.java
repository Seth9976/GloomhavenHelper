package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzvo extends zzge implements zzvp {
    public zzvo() {
        super("com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.zzb(((zzuh)zzgd.zza(parcel0, zzuh.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                String s = this.getMediationAdapterClassName();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 3: {
                boolean z = this.isLoading();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 4: {
                String s1 = this.zzkf();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            case 5: {
                this.zza(((zzuh)zzgd.zza(parcel0, zzuh.CREATOR)), parcel0.readInt());
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

