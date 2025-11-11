package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzacp extends zzge implements zzacm {
    public zzacp() {
        super("com.google.android.gms.ads.internal.formats.client.IMediaContent");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzaed zzaed0;
        switch(v) {
            case 2: {
                float f = this.getAspectRatio();
                parcel1.writeNoException();
                parcel1.writeFloat(f);
                return true;
            }
            case 3: {
                this.zzo(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                IObjectWrapper iObjectWrapper0 = this.zzri();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 5: {
                float f1 = this.getDuration();
                parcel1.writeNoException();
                parcel1.writeFloat(f1);
                return true;
            }
            case 6: {
                float f2 = this.getCurrentTime();
                parcel1.writeNoException();
                parcel1.writeFloat(f2);
                return true;
            }
            case 7: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 8: {
                boolean z = this.hasVideoContent();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 9: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzaed0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnMediaContentChangedListener");
                    zzaed0 = iInterface0 instanceof zzaed ? ((zzaed)iInterface0) : new zzaec(iBinder0);
                }
                this.zza(zzaed0);
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzacm zzn(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IMediaContent");
        return iInterface0 instanceof zzacm ? ((zzacm)iInterface0) : new zzaco(iBinder0);
    }
}

