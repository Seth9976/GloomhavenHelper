package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;
import java.util.Map;

public abstract class zzbgg extends zzge implements zzbgd {
    public zzbgg() {
        super("com.google.android.gms.ads.measurement.IAppMeasurementProxy");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.performAction(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                Bundle bundle0 = this.performActionWithResponse(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 3: {
                this.logEvent(parcel0.readString(), parcel0.readString(), ((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                this.zza(parcel0.readString(), parcel0.readString(), Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                Map map0 = this.getUserProperties(parcel0.readString(), parcel0.readString(), zzgd.zza(parcel0));
                parcel1.writeNoException();
                parcel1.writeMap(map0);
                return true;
            }
            case 6: {
                int v2 = this.getMaxUserProperties(parcel0.readString());
                parcel1.writeNoException();
                parcel1.writeInt(v2);
                return true;
            }
            case 7: {
                this.setConditionalUserProperty(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 8: {
                this.clearConditionalUserProperty(parcel0.readString(), parcel0.readString(), ((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                List list0 = this.getConditionalUserProperties(parcel0.readString(), parcel0.readString());
                parcel1.writeNoException();
                parcel1.writeList(list0);
                return true;
            }
            case 10: {
                String s = this.getAppInstanceId();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 11: {
                String s1 = this.getGmpAppId();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            case 12: {
                long v3 = this.generateEventId();
                parcel1.writeNoException();
                parcel1.writeLong(v3);
                return true;
            }
            case 13: {
                this.beginAdUnitExposure(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 14: {
                this.endAdUnitExposure(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 15: {
                this.zzb(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readString(), parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 16: {
                String s2 = this.getCurrentScreenName();
                parcel1.writeNoException();
                parcel1.writeString(s2);
                return true;
            }
            case 17: {
                String s3 = this.getCurrentScreenClass();
                parcel1.writeNoException();
                parcel1.writeString(s3);
                return true;
            }
            case 18: {
                String s4 = this.getAppIdOrigin();
                parcel1.writeNoException();
                parcel1.writeString(s4);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

