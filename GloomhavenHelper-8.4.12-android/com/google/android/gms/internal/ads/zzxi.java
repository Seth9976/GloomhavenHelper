package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzxi extends zzge implements zzxj {
    public zzxi() {
        super("com.google.android.gms.ads.internal.client.IVideoController");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzxk zzxk0;
        switch(v) {
            case 1: {
                this.play();
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                this.pause();
                parcel1.writeNoException();
                return true;
            }
            case 3: {
                this.mute(zzgd.zza(parcel0));
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                boolean z = this.isMuted();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 5: {
                int v2 = this.getPlaybackState();
                parcel1.writeNoException();
                parcel1.writeInt(v2);
                return true;
            }
            case 6: {
                float f = this.getDuration();
                parcel1.writeNoException();
                parcel1.writeFloat(f);
                return true;
            }
            case 7: {
                float f1 = this.getCurrentTime();
                parcel1.writeNoException();
                parcel1.writeFloat(f1);
                return true;
            }
            case 8: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzxk0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    zzxk0 = iInterface0 instanceof zzxk ? ((zzxk)iInterface0) : new zzxm(iBinder0);
                }
                this.zza(zzxk0);
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                float f2 = this.getAspectRatio();
                parcel1.writeNoException();
                parcel1.writeFloat(f2);
                return true;
            }
            case 10: {
                boolean z1 = this.isCustomControlsEnabled();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z1);
                return true;
            }
            case 11: {
                zzxk zzxk1 = this.zzpo();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxk1);
                return true;
            }
            case 12: {
                boolean z2 = this.isClickToExpandEnabled();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z2);
                return true;
            }
            case 13: {
                this.stop();
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzxj zzk(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        return iInterface0 instanceof zzxj ? ((zzxj)iInterface0) : new zzxl(iBinder0);
    }
}

