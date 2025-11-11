package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzadi extends zzge implements zzadj {
    public zzadi() {
        super("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 2: {
                IObjectWrapper iObjectWrapper0 = this.zzrj();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 3: {
                String s = this.getHeadline();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 4: {
                List list0 = this.getImages();
                parcel1.writeNoException();
                parcel1.writeList(list0);
                return true;
            }
            case 5: {
                String s1 = this.getBody();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            case 6: {
                zzacr zzacr0 = this.zzrn();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacr0);
                return true;
            }
            case 7: {
                String s2 = this.getCallToAction();
                parcel1.writeNoException();
                parcel1.writeString(s2);
                return true;
            }
            case 8: {
                String s3 = this.getAdvertiser();
                parcel1.writeNoException();
                parcel1.writeString(s3);
                return true;
            }
            case 9: {
                Bundle bundle0 = this.getExtras();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 10: {
                this.destroy();
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 12: {
                this.performClick(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                boolean z = this.recordImpression(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 14: {
                this.reportTouchEvent(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 15: {
                zzacj zzacj0 = this.zzrl();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacj0);
                return true;
            }
            case 16: {
                IObjectWrapper iObjectWrapper1 = this.zzrm();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper1);
                return true;
            }
            case 17: {
                String s4 = this.getMediationAdapterClassName();
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

