package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzwj extends zzge implements zzwg {
    public zzwj() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                zzvx zzvx0 = this.zza(Stub.asInterface(parcel0.readStrongBinder()), ((zzuk)zzgd.zza(parcel0, zzuk.CREATOR)), parcel0.readString(), zzaln.zzac(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvx0);
                return true;
            }
            case 2: {
                zzvx zzvx1 = this.zzb(Stub.asInterface(parcel0.readStrongBinder()), ((zzuk)zzgd.zza(parcel0, zzuk.CREATOR)), parcel0.readString(), zzaln.zzac(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvx1);
                return true;
            }
            case 3: {
                zzvq zzvq0 = this.zza(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readString(), zzaln.zzac(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvq0);
                return true;
            }
            case 4: {
                zzwn zzwn0 = this.zzc(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzwn0);
                return true;
            }
            case 5: {
                zzacv zzacv0 = this.zza(Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacv0);
                return true;
            }
            case 6: {
                zzary zzary0 = this.zza(Stub.asInterface(parcel0.readStrongBinder()), zzaln.zzac(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzary0);
                return true;
            }
            case 7: {
                zzapq zzapq0 = this.zzd(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzapq0);
                return true;
            }
            case 8: {
                zzapb zzapb0 = this.zzb(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzapb0);
                return true;
            }
            case 9: {
                zzwn zzwn1 = this.zza(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzwn1);
                return true;
            }
            case 10: {
                zzvx zzvx2 = this.zza(Stub.asInterface(parcel0.readStrongBinder()), ((zzuk)zzgd.zza(parcel0, zzuk.CREATOR)), parcel0.readString(), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvx2);
                return true;
            }
            case 11: {
                zzacy zzacy0 = this.zza(Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacy0);
                return true;
            }
            case 12: {
                zzast zzast0 = this.zzb(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readString(), zzaln.zzac(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzast0);
                return true;
            }
            case 13: {
                zzvx zzvx3 = this.zzc(Stub.asInterface(parcel0.readStrongBinder()), ((zzuk)zzgd.zza(parcel0, zzuk.CREATOR)), parcel0.readString(), zzaln.zzac(parcel0.readStrongBinder()), parcel0.readInt());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvx3);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

