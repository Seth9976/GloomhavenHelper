package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzacu extends zzge implements zzacv {
    public zzacu() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzacm zzacm0;
        switch(v) {
            case 1: {
                this.zzb(parcel0.readString(), Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                IObjectWrapper iObjectWrapper0 = this.zzcp(parcel0.readString());
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 3: {
                this.zza(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                this.destroy();
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                this.zzc(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                this.zze(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                this.zzf(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 8: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzacm0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IMediaContent");
                    zzacm0 = iInterface0 instanceof zzacm ? ((zzacm)iInterface0) : new zzaco(iBinder0);
                }
                this.zza(zzacm0);
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                this.zzg(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzacv zzp(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
        return iInterface0 instanceof zzacv ? ((zzacv)iInterface0) : new zzacx(iBinder0);
    }
}

