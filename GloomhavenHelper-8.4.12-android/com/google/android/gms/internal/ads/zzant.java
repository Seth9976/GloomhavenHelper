package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzant extends zzge implements zzanq {
    public zzant() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzank zzank0;
        zzanp zzanp0;
        zzanj zzanj0;
        zzane zzane0;
        zzanv zzanv0;
        switch(v) {
            case 1: {
                IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                String s = parcel0.readString();
                Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
                Bundle bundle1 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
                zzuk zzuk0 = (zzuk)zzgd.zza(parcel0, zzuk.CREATOR);
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzanv0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                    zzanv0 = iInterface0 instanceof zzanv ? ((zzanv)iInterface0) : new zzanx(iBinder0);
                }
                this.zza(iObjectWrapper0, s, bundle0, bundle1, zzuk0, zzanv0);
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                zzaoe zzaoe0 = this.zzth();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, zzaoe0);
                return true;
            }
            case 3: {
                zzaoe zzaoe1 = this.zzti();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, zzaoe1);
                return true;
            }
            case 5: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 10: {
                this.zzy(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                this.zza(parcel0.createStringArray(), ((Bundle[])parcel0.createTypedArray(Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                String s1 = parcel0.readString();
                String s2 = parcel0.readString();
                zzuh zzuh0 = (zzuh)zzgd.zza(parcel0, zzuh.CREATOR);
                IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel0.readStrongBinder());
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 == null) {
                    zzane0 = null;
                }
                else {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                    zzane0 = iInterface1 instanceof zzane ? ((zzane)iInterface1) : new zzang(iBinder1);
                }
                this.zza(s1, s2, zzuh0, iObjectWrapper1, zzane0, zzalt.zzad(parcel0.readStrongBinder()), ((zzuk)zzgd.zza(parcel0, zzuk.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 14: {
                String s3 = parcel0.readString();
                String s4 = parcel0.readString();
                zzuh zzuh1 = (zzuh)zzgd.zza(parcel0, zzuh.CREATOR);
                IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel0.readStrongBinder());
                IBinder iBinder2 = parcel0.readStrongBinder();
                if(iBinder2 == null) {
                    zzanj0 = null;
                }
                else {
                    IInterface iInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                    zzanj0 = iInterface2 instanceof zzanj ? ((zzanj)iInterface2) : new zzanl(iBinder2);
                }
                this.zza(s3, s4, zzuh1, iObjectWrapper2, zzanj0, zzalt.zzad(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 15: {
                boolean z = this.zzz(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 16: {
                String s5 = parcel0.readString();
                String s6 = parcel0.readString();
                zzuh zzuh2 = (zzuh)zzgd.zza(parcel0, zzuh.CREATOR);
                IObjectWrapper iObjectWrapper3 = Stub.asInterface(parcel0.readStrongBinder());
                IBinder iBinder3 = parcel0.readStrongBinder();
                if(iBinder3 == null) {
                    zzanp0 = null;
                }
                else {
                    IInterface iInterface3 = iBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                    zzanp0 = iInterface3 instanceof zzanp ? ((zzanp)iInterface3) : new zzanr(iBinder3);
                }
                this.zza(s5, s6, zzuh2, iObjectWrapper3, zzanp0, zzalt.zzad(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 17: {
                boolean z1 = this.zzaa(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z1);
                return true;
            }
            case 18: {
                String s7 = parcel0.readString();
                String s8 = parcel0.readString();
                zzuh zzuh3 = (zzuh)zzgd.zza(parcel0, zzuh.CREATOR);
                IObjectWrapper iObjectWrapper4 = Stub.asInterface(parcel0.readStrongBinder());
                IBinder iBinder4 = parcel0.readStrongBinder();
                if(iBinder4 == null) {
                    zzank0 = null;
                }
                else {
                    IInterface iInterface4 = iBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                    zzank0 = iInterface4 instanceof zzank ? ((zzank)iInterface4) : new zzanm(iBinder4);
                }
                this.zza(s7, s8, zzuh3, iObjectWrapper4, zzank0, zzalt.zzad(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 19: {
                this.zzdn(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzanq zzaf(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return iInterface0 instanceof zzanq ? ((zzanq)iInterface0) : new zzans(iBinder0);
    }
}

