package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public abstract class zzvt extends zzge implements zzvq {
    public zzvt() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzvk zzvk0 = null;
        switch(v) {
            case 1: {
                zzvp zzvp0 = this.zzpi();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvp0);
                return true;
            }
            case 2: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 != null) {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzvk0 = iInterface0 instanceof zzvk ? ((zzvk)iInterface0) : new zzvm(iBinder0);
                }
                this.zzb(zzvk0);
                parcel1.writeNoException();
                return true;
            }
            case 3: {
                this.zza(zzadq.zzs(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                this.zza(zzadv.zzt(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                this.zza(parcel0.readString(), zzaeb.zzv(parcel0.readStrongBinder()), zzadw.zzu(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                this.zza(((zzach)zzgd.zza(parcel0, zzach.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 != null) {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzvk0 = iInterface1 instanceof zzwl ? ((zzwl)iInterface1) : new zzwk(iBinder1);
                }
                this.zzb(((zzwl)zzvk0));
                parcel1.writeNoException();
                return true;
            }
            case 8: {
                this.zza(zzaee.zzw(parcel0.readStrongBinder()), ((zzuk)zzgd.zza(parcel0, zzuk.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                this.zza(((PublisherAdViewOptions)zzgd.zza(parcel0, PublisherAdViewOptions.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 10: {
                this.zza(zzaej.zzx(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                this.zza(((zzahl)zzgd.zza(parcel0, zzahl.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 14: {
                this.zza(zzahs.zzab(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

