package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzxn extends zzge implements zzxk {
    public zzxn() {
        super("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.onVideoStart();
                break;
            }
            case 2: {
                this.onVideoPlay();
                break;
            }
            case 3: {
                this.onVideoPause();
                break;
            }
            case 4: {
                this.onVideoEnd();
                break;
            }
            case 5: {
                this.onVideoMute(zzgd.zza(parcel0));
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

