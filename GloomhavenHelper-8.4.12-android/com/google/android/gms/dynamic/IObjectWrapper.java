package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.common.zzb;

public interface IObjectWrapper extends IInterface {
    public static class Stub extends zzb implements IObjectWrapper {
        public static final class zza extends com.google.android.gms.internal.common.zza implements IObjectWrapper {
            zza(IBinder iBinder0) {
                super(iBinder0, "com.google.android.gms.dynamic.IObjectWrapper");
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static IObjectWrapper asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            return iInterface0 instanceof IObjectWrapper ? ((IObjectWrapper)iInterface0) : new zza(iBinder0);
        }
    }

}

