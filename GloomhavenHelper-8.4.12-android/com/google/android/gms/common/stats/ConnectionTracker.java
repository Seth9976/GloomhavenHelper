package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

@KeepForSdk
public class ConnectionTracker {
    private static final Object zzdp;
    private static volatile ConnectionTracker zzfa;
    @VisibleForTesting
    private static boolean zzfb;
    private final List zzfc;
    private final List zzfd;
    private final List zzfe;
    private final List zzff;

    static {
        ConnectionTracker.zzdp = new Object();
        ConnectionTracker.zzfb = false;
    }

    private ConnectionTracker() {
        this.zzfc = Collections.EMPTY_LIST;
        this.zzfd = Collections.EMPTY_LIST;
        this.zzfe = Collections.EMPTY_LIST;
        this.zzff = Collections.EMPTY_LIST;
    }

    @KeepForSdk
    public boolean bindService(Context context0, Intent intent0, ServiceConnection serviceConnection0, int v) {
        return this.zza(context0, context0.getClass().getName(), intent0, serviceConnection0, v);
    }

    @KeepForSdk
    public static ConnectionTracker getInstance() {
        if(ConnectionTracker.zzfa == null) {
            Object object0 = ConnectionTracker.zzdp;
            synchronized(object0) {
                if(ConnectionTracker.zzfa == null) {
                    ConnectionTracker.zzfa = new ConnectionTracker();
                }
                return ConnectionTracker.zzfa;
            }
        }
        return ConnectionTracker.zzfa;
    }

    @SuppressLint({"UntrackedBindService"})
    @KeepForSdk
    public void unbindService(Context context0, ServiceConnection serviceConnection0) {
        context0.unbindService(serviceConnection0);
    }

    public final boolean zza(Context context0, String s, Intent intent0, ServiceConnection serviceConnection0, int v) {
        ComponentName componentName0 = intent0.getComponent();
        if((componentName0 == null ? false : ClientLibraryUtils.zzc(context0, componentName0.getPackageName()))) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        }
        return context0.bindService(intent0, serviceConnection0, v);
    }
}

