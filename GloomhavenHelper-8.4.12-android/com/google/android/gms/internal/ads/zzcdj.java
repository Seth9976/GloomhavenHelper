package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks;

public final class zzcdj extends VideoLifecycleCallbacks {
    private final zzbyz zzfne;

    public zzcdj(zzbyz zzbyz0) {
        this.zzfne = zzbyz0;
    }

    @Override  // com.google.android.gms.ads.VideoController$VideoLifecycleCallbacks
    public final void onVideoEnd() {
        zzxk zzxk0 = zzcdj.zza(this.zzfne);
        if(zzxk0 == null) {
            return;
        }
        try {
            zzxk0.onVideoEnd();
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Unable to call onVideoEnd()", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.VideoController$VideoLifecycleCallbacks
    public final void onVideoPause() {
        zzxk zzxk0 = zzcdj.zza(this.zzfne);
        if(zzxk0 == null) {
            return;
        }
        try {
            zzxk0.onVideoPause();
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Unable to call onVideoEnd()", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.VideoController$VideoLifecycleCallbacks
    public final void onVideoStart() {
        zzxk zzxk0 = zzcdj.zza(this.zzfne);
        if(zzxk0 == null) {
            return;
        }
        try {
            zzxk0.onVideoStart();
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Unable to call onVideoEnd()", remoteException0);
        }
    }

    private static zzxk zza(zzbyz zzbyz0) {
        zzxj zzxj0 = zzbyz0.getVideoController();
        if(zzxj0 == null) {
            return null;
        }
        try {
            return zzxj0.zzpo();
        }
        catch(RemoteException unused_ex) {
            return null;
        }
    }
}

