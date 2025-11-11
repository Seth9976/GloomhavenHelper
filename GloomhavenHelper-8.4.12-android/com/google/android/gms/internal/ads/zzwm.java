package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;

public abstract class zzwm extends zzge implements zzwn {
    public zzwm() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.initialize();
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                this.setAppVolume(parcel0.readFloat());
                parcel1.writeNoException();
                return true;
            }
            case 3: {
                this.zzce(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                this.setAppMuted(zzgd.zza(parcel0));
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                this.zzb(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                this.zza(parcel0.readString(), Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                float f = this.zzpj();
                parcel1.writeNoException();
                parcel1.writeFloat(f);
                return true;
            }
            case 8: {
                boolean z = this.zzpk();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 9: {
                String s = this.getVersionString();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 10: {
                this.zzcf(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                this.zza(zzaln.zzac(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 12: {
                this.zza(zzahf.zzaa(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                List list0 = this.zzpl();
                parcel1.writeNoException();
                parcel1.writeTypedList(list0);
                return true;
            }
            case 14: {
                this.zza(((zzyw)zzgd.zza(parcel0, zzyw.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

