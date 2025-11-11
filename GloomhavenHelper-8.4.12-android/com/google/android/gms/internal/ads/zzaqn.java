package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzaqn extends zzge implements zzaqo {
    public zzaqn() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzaqp zzaqp0 = null;
        switch(v) {
            case 1: {
                zzaqk zzaqk0 = this.zza(((zzaqi)zzgd.zza(parcel0, zzaqi.CREATOR)));
                parcel1.writeNoException();
                zzgd.zzb(parcel1, zzaqk0);
                return true;
            }
            case 2: {
                zzaqi zzaqi0 = (zzaqi)zzgd.zza(parcel0, zzaqi.CREATOR);
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 != null) {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    zzaqp0 = iInterface0 instanceof zzaqp ? ((zzaqp)iInterface0) : new zzaqs(iBinder0);
                }
                this.zza(zzaqi0, zzaqp0);
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                zzaqx zzaqx0 = (zzaqx)zzgd.zza(parcel0, zzaqx.CREATOR);
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 != null) {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    zzaqp0 = iInterface1 instanceof zzaqr ? ((zzaqr)iInterface1) : new zzaqt(iBinder1);
                }
                this.zza(zzaqx0, ((zzaqr)zzaqp0));
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                zzaqx zzaqx1 = (zzaqx)zzgd.zza(parcel0, zzaqx.CREATOR);
                IBinder iBinder2 = parcel0.readStrongBinder();
                if(iBinder2 != null) {
                    IInterface iInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    zzaqp0 = iInterface2 instanceof zzaqr ? ((zzaqr)iInterface2) : new zzaqt(iBinder2);
                }
                this.zzb(zzaqx1, ((zzaqr)zzaqp0));
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                zzaqx zzaqx2 = (zzaqx)zzgd.zza(parcel0, zzaqx.CREATOR);
                IBinder iBinder3 = parcel0.readStrongBinder();
                if(iBinder3 != null) {
                    IInterface iInterface3 = iBinder3.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    zzaqp0 = iInterface3 instanceof zzaqr ? ((zzaqr)iInterface3) : new zzaqt(iBinder3);
                }
                this.zzc(zzaqx2, ((zzaqr)zzaqp0));
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                String s = parcel0.readString();
                IBinder iBinder4 = parcel0.readStrongBinder();
                if(iBinder4 != null) {
                    IInterface iInterface4 = iBinder4.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    zzaqp0 = iInterface4 instanceof zzaqr ? ((zzaqr)iInterface4) : new zzaqt(iBinder4);
                }
                this.zza(s, ((zzaqr)zzaqp0));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

