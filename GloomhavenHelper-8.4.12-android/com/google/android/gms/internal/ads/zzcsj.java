package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import javax.annotation.concurrent.GuardedBy;

public final class zzcsj implements AppEventListener {
    @GuardedBy("this")
    private zzwf zzggi;

    @Override  // com.google.android.gms.ads.doubleclick.AppEventListener
    public final void onAppEvent(String s, String s1) {
        synchronized(this) {
            if(this.zzggi != null) {
                try {
                    this.zzggi.onAppEvent(s, s1);
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAppEvent.", remoteException0);
                }
            }
        }
    }

    public final zzwf zzaop() {
        synchronized(this) {
        }
        return this.zzggi;
    }

    public final void zzb(zzwf zzwf0) {
        synchronized(this) {
            this.zzggi = zzwf0;
        }
    }
}

