package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzaeq extends zzge implements zzaer {
    public zzaeq() {
        super("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzaem zzaem0;
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
                String s3 = this.getAdvertiser();
                parcel1.writeNoException();
                parcel1.writeString(s3);
                return true;
            }
            case 8: {
                double f = this.getStarRating();
                parcel1.writeNoException();
                parcel1.writeDouble(f);
                return true;
            }
            case 9: {
                String s4 = this.getStore();
                parcel1.writeNoException();
                parcel1.writeString(s4);
                return true;
            }
            case 10: {
                String s5 = this.getPrice();
                parcel1.writeNoException();
                parcel1.writeString(s5);
                return true;
            }
            case 11: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 12: {
                String s6 = this.getMediationAdapterClassName();
                parcel1.writeNoException();
                parcel1.writeString(s6);
                return true;
            }
            case 13: {
                this.destroy();
                parcel1.writeNoException();
                return true;
            }
            case 14: {
                zzacj zzacj0 = this.zzrl();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacj0);
                return true;
            }
            case 15: {
                this.performClick(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 16: {
                boolean z = this.recordImpression(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 17: {
                this.reportTouchEvent(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 18: {
                IObjectWrapper iObjectWrapper0 = this.zzrj();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 19: {
                IObjectWrapper iObjectWrapper1 = this.zzrm();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper1);
                return true;
            }
            case 20: {
                Bundle bundle0 = this.getExtras();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 21: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzaem0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
                    zzaem0 = iInterface0 instanceof zzaem ? ((zzaem)iInterface0) : new zzaeo(iBinder0);
                }
                this.zza(zzaem0);
                parcel1.writeNoException();
                return true;
            }
            case 22: {
                this.cancelUnconfirmedClick();
                parcel1.writeNoException();
                return true;
            }
            case 23: {
                List list1 = this.getMuteThisAdReasons();
                parcel1.writeNoException();
                parcel1.writeList(list1);
                return true;
            }
            case 24: {
                boolean z1 = this.isCustomMuteThisAdEnabled();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z1);
                return true;
            }
            case 25: {
                this.zza(zzwx.zzg(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 26: {
                this.zza(zzwt.zzf(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 27: {
                this.zzru();
                parcel1.writeNoException();
                return true;
            }
            case 28: {
                this.recordCustomClickGesture();
                parcel1.writeNoException();
                return true;
            }
            case 29: {
                zzacm zzacm0 = this.zzrv();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacm0);
                return true;
            }
            case 30: {
                boolean z2 = this.isCustomClickGestureEnabled();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z2);
                return true;
            }
            case 0x1F: {
                zzxe zzxe0 = this.zzkg();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxe0);
                return true;
            }
            case 0x20: {
                this.zza(zzxc.zzi(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

