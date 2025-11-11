package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public abstract class RemoteCreator {
    @KeepForSdk
    public static class RemoteCreatorException extends Exception {
        public RemoteCreatorException(String s) {
            super(s);
        }

        public RemoteCreatorException(String s, Throwable throwable0) {
            super(s, throwable0);
        }
    }

    private final String zzic;
    private Object zzid;

    @KeepForSdk
    protected RemoteCreator(String s) {
        this.zzic = s;
    }

    @KeepForSdk
    protected abstract Object getRemoteCreator(IBinder arg1);

    @KeepForSdk
    protected final Object getRemoteCreatorInstance(Context context0) throws RemoteCreatorException {
        if(this.zzid == null) {
            Preconditions.checkNotNull(context0);
            Context context1 = GooglePlayServicesUtilLight.getRemoteContext(context0);
            if(context1 != null) {
                ClassLoader classLoader0 = context1.getClassLoader();
                try {
                    this.zzid = this.getRemoteCreator(((IBinder)classLoader0.loadClass(this.zzic).newInstance()));
                    return this.zzid;
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    throw new RemoteCreatorException("Could not load creator class.", classNotFoundException0);
                }
                catch(InstantiationException instantiationException0) {
                    throw new RemoteCreatorException("Could not instantiate creator.", instantiationException0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    throw new RemoteCreatorException("Could not access creator.", illegalAccessException0);
                }
            }
            throw new RemoteCreatorException("Could not get remote context.");
        }
        return this.zzid;
    }
}

