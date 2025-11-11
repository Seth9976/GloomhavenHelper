package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IGmsCallbacks extends IInterface {
    public static abstract class zza extends zzb implements IGmsCallbacks {
        public zza() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }

        @Override  // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            switch(v) {
                case 1: {
                    this.onPostInitComplete(parcel0.readInt(), parcel0.readStrongBinder(), ((Bundle)zzc.zza(parcel0, Bundle.CREATOR)));
                    break;
                }
                case 2: {
                    this.zza(parcel0.readInt(), ((Bundle)zzc.zza(parcel0, Bundle.CREATOR)));
                    break;
                }
                case 3: {
                    this.zza(parcel0.readInt(), parcel0.readStrongBinder(), ((com.google.android.gms.common.internal.zzb)zzc.zza(parcel0, com.google.android.gms.common.internal.zzb.CREATOR)));
                    break;
                }
                default: {
                    return false;
                }
            }
            parcel1.writeNoException();
            return true;
        }
    }

    void onPostInitComplete(int arg1, IBinder arg2, Bundle arg3) throws RemoteException;

    void zza(int arg1, Bundle arg2) throws RemoteException;

    void zza(int arg1, IBinder arg2, com.google.android.gms.common.internal.zzb arg3) throws RemoteException;
}

