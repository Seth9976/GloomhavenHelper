package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzape extends zzge implements zzapb {
    public zzape() {
        super("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.onCreate(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                this.onRestart();
                parcel1.writeNoException();
                return true;
            }
            case 3: {
                this.onStart();
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                this.onResume();
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                this.onPause();
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
                this.onSaveInstanceState(bundle0);
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 7: {
                this.onStop();
                parcel1.writeNoException();
                return true;
            }
            case 8: {
                this.onDestroy();
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                this.zzdk();
                parcel1.writeNoException();
                return true;
            }
            case 10: {
                this.onBackPressed();
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                boolean z = this.zztr();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 12: {
                this.onActivityResult(parcel0.readInt(), parcel0.readInt(), ((Intent)zzgd.zza(parcel0, Intent.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                this.zzad(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzapb zzag(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        return iInterface0 instanceof zzapb ? ((zzapb)iInterface0) : new zzapd(iBinder0);
    }
}

