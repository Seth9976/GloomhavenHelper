package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzadm extends zzge implements zzadn {
    public zzadm() {
        super("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                String s = this.zzcu(parcel0.readString());
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 2: {
                zzacr zzacr0 = this.zzcv(parcel0.readString());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacr0);
                return true;
            }
            case 3: {
                List list0 = this.getAvailableAssetNames();
                parcel1.writeNoException();
                parcel1.writeStringList(list0);
                return true;
            }
            case 4: {
                String s1 = this.getCustomTemplateId();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            case 5: {
                this.performClick(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                this.recordImpression();
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 8: {
                this.destroy();
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                IObjectWrapper iObjectWrapper0 = this.zzro();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 10: {
                boolean z = this.zzp(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 11: {
                IObjectWrapper iObjectWrapper1 = this.zzrj();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper1);
                return true;
            }
            case 12: {
                boolean z1 = this.zzrp();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z1);
                return true;
            }
            case 13: {
                boolean z2 = this.zzrq();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z2);
                return true;
            }
            case 14: {
                this.zzq(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 15: {
                this.zzrr();
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzadn zzr(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        return iInterface0 instanceof zzadn ? ((zzadn)iInterface0) : new zzadp(iBinder0);
    }
}

