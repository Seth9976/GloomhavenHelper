package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzalw extends zzge implements zzalx {
    public zzalw() {
        super("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 2: {
                String s = this.getHeadline();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 3: {
                List list0 = this.getImages();
                parcel1.writeNoException();
                parcel1.writeList(list0);
                return true;
            }
            case 4: {
                String s1 = this.getBody();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            case 5: {
                zzacr zzacr0 = this.zzrk();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacr0);
                return true;
            }
            case 6: {
                String s2 = this.getCallToAction();
                parcel1.writeNoException();
                parcel1.writeString(s2);
                return true;
            }
            case 7: {
                double f = this.getStarRating();
                parcel1.writeNoException();
                parcel1.writeDouble(f);
                return true;
            }
            case 8: {
                String s3 = this.getStore();
                parcel1.writeNoException();
                parcel1.writeString(s3);
                return true;
            }
            case 9: {
                String s4 = this.getPrice();
                parcel1.writeNoException();
                parcel1.writeString(s4);
                return true;
            }
            case 10: {
                this.recordImpression();
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                this.zzu(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 12: {
                this.zzv(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                boolean z = this.getOverrideImpressionRecording();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 14: {
                boolean z1 = this.getOverrideClickHandling();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z1);
                return true;
            }
            case 15: {
                Bundle bundle0 = this.getExtras();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 16: {
                this.zzw(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 17: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 18: {
                IObjectWrapper iObjectWrapper0 = this.zzsz();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 19: {
                zzacj zzacj0 = this.zzrl();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacj0);
                return true;
            }
            case 20: {
                IObjectWrapper iObjectWrapper1 = this.zzta();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper1);
                return true;
            }
            case 21: {
                IObjectWrapper iObjectWrapper2 = this.zzrm();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper2);
                return true;
            }
            case 22: {
                this.zzc(Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

