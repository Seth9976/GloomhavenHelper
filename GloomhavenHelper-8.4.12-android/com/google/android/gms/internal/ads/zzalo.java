package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzalo extends zzge implements zzalp {
    public zzalo() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzalq zzalq4;
        zzalq zzalq3;
        zzalq zzalq2;
        zzalq zzalq1;
        zzalq zzalq0 = null;
        switch(v) {
            case 1: {
                IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                Parcelable parcelable0 = zzgd.zza(parcel0, zzuk.CREATOR);
                Parcelable parcelable1 = zzgd.zza(parcel0, zzuh.CREATOR);
                String s = parcel0.readString();
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzalq1 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzalq1 = iInterface0 instanceof zzalq ? ((zzalq)iInterface0) : new zzals(iBinder0);
                }
                this.zza(iObjectWrapper0, ((zzuk)parcelable0), ((zzuh)parcelable1), s, zzalq1);
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                IObjectWrapper iObjectWrapper1 = this.zzsp();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper1);
                return true;
            }
            case 3: {
                IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel0.readStrongBinder());
                zzuh zzuh0 = (zzuh)zzgd.zza(parcel0, zzuh.CREATOR);
                String s1 = parcel0.readString();
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 != null) {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzalq0 = iInterface1 instanceof zzalq ? ((zzalq)iInterface1) : new zzals(iBinder1);
                }
                this.zza(iObjectWrapper2, zzuh0, s1, zzalq0);
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                this.showInterstitial();
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                this.destroy();
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                IObjectWrapper iObjectWrapper3 = Stub.asInterface(parcel0.readStrongBinder());
                Parcelable parcelable2 = zzgd.zza(parcel0, zzuk.CREATOR);
                Parcelable parcelable3 = zzgd.zza(parcel0, zzuh.CREATOR);
                String s2 = parcel0.readString();
                String s3 = parcel0.readString();
                IBinder iBinder2 = parcel0.readStrongBinder();
                if(iBinder2 == null) {
                    zzalq2 = null;
                }
                else {
                    IInterface iInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzalq2 = iInterface2 instanceof zzalq ? ((zzalq)iInterface2) : new zzals(iBinder2);
                }
                this.zza(iObjectWrapper3, ((zzuk)parcelable2), ((zzuh)parcelable3), s2, s3, zzalq2);
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                IObjectWrapper iObjectWrapper4 = Stub.asInterface(parcel0.readStrongBinder());
                Parcelable parcelable4 = zzgd.zza(parcel0, zzuh.CREATOR);
                String s4 = parcel0.readString();
                String s5 = parcel0.readString();
                IBinder iBinder3 = parcel0.readStrongBinder();
                if(iBinder3 == null) {
                    zzalq3 = null;
                }
                else {
                    IInterface iInterface3 = iBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzalq3 = iInterface3 instanceof zzalq ? ((zzalq)iInterface3) : new zzals(iBinder3);
                }
                this.zza(iObjectWrapper4, ((zzuh)parcelable4), s4, s5, zzalq3);
                parcel1.writeNoException();
                return true;
            }
            case 8: {
                this.pause();
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                this.resume();
                parcel1.writeNoException();
                return true;
            }
            case 10: {
                this.zza(Stub.asInterface(parcel0.readStrongBinder()), ((zzuh)zzgd.zza(parcel0, zzuh.CREATOR)), parcel0.readString(), zzasl.zzam(parcel0.readStrongBinder()), parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                this.zza(((zzuh)zzgd.zza(parcel0, zzuh.CREATOR)), parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 12: {
                this.showVideo();
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                boolean z = this.isInitialized();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 14: {
                IObjectWrapper iObjectWrapper5 = Stub.asInterface(parcel0.readStrongBinder());
                Parcelable parcelable5 = zzgd.zza(parcel0, zzuh.CREATOR);
                String s6 = parcel0.readString();
                String s7 = parcel0.readString();
                IBinder iBinder4 = parcel0.readStrongBinder();
                if(iBinder4 == null) {
                    zzalq4 = null;
                }
                else {
                    IInterface iInterface4 = iBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzalq4 = iInterface4 instanceof zzalq ? ((zzalq)iInterface4) : new zzals(iBinder4);
                }
                this.zza(iObjectWrapper5, ((zzuh)parcelable5), s6, s7, zzalq4, ((zzach)zzgd.zza(parcel0, zzach.CREATOR)), parcel0.createStringArrayList());
                parcel1.writeNoException();
                return true;
            }
            case 15: {
                zzalx zzalx0 = this.zzsq();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzalx0);
                return true;
            }
            case 16: {
                zzaly zzaly0 = this.zzsr();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzaly0);
                return true;
            }
            case 17: {
                Bundle bundle0 = this.zzss();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 18: {
                Bundle bundle1 = this.getInterstitialAdapterInfo();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle1);
                return true;
            }
            case 19: {
                Bundle bundle2 = this.zzst();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle2);
                return true;
            }
            case 20: {
                this.zza(((zzuh)zzgd.zza(parcel0, zzuh.CREATOR)), parcel0.readString(), parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 21: {
                this.zzs(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 22: {
                boolean z1 = this.zzsu();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z1);
                return true;
            }
            case 23: {
                this.zza(Stub.asInterface(parcel0.readStrongBinder()), zzasl.zzam(parcel0.readStrongBinder()), parcel0.createStringArrayList());
                parcel1.writeNoException();
                return true;
            }
            case 24: {
                zzadn zzadn0 = this.zzsv();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzadn0);
                return true;
            }
            case 25: {
                this.setImmersiveMode(zzgd.zza(parcel0));
                parcel1.writeNoException();
                return true;
            }
            case 26: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 27: {
                zzamd zzamd0 = this.zzsw();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzamd0);
                return true;
            }
            case 28: {
                IObjectWrapper iObjectWrapper6 = Stub.asInterface(parcel0.readStrongBinder());
                zzuh zzuh1 = (zzuh)zzgd.zza(parcel0, zzuh.CREATOR);
                String s8 = parcel0.readString();
                IBinder iBinder5 = parcel0.readStrongBinder();
                if(iBinder5 != null) {
                    IInterface iInterface5 = iBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzalq0 = iInterface5 instanceof zzalq ? ((zzalq)iInterface5) : new zzals(iBinder5);
                }
                this.zzb(iObjectWrapper6, zzuh1, s8, zzalq0);
                parcel1.writeNoException();
                return true;
            }
            case 30: {
                this.zzt(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 0x1F: {
                this.zza(Stub.asInterface(parcel0.readStrongBinder()), zzaha.zzz(parcel0.readStrongBinder()), parcel0.createTypedArrayList(zzahj.CREATOR));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

