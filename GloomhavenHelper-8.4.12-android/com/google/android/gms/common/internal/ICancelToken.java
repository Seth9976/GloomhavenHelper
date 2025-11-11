package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;

public interface ICancelToken extends IInterface {
    public static abstract class Stub extends zzb implements ICancelToken {
        public static final class zza extends com.google.android.gms.internal.common.zza implements ICancelToken {
            zza(IBinder iBinder0) {
                super(iBinder0, "com.google.android.gms.common.internal.ICancelToken");
            }

            @Override  // com.google.android.gms.common.internal.ICancelToken
            public final void cancel() throws RemoteException {
                this.zzc(2, this.zza());
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }

        public static ICancelToken asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            return iInterface0 instanceof ICancelToken ? ((ICancelToken)iInterface0) : new zza(iBinder0);
        }
    }

    void cancel() throws RemoteException;
}

