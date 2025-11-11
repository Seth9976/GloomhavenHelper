package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.instream.InstreamAd;
import com.google.android.gms.ads.instream.InstreamAdView;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzahu extends InstreamAd {
    private MediaContent zzbjx;
    private VideoController zzcfe;
    private final zzahn zzczn;

    public zzahu(zzahn zzahn0) {
        this.zzczn = zzahn0;
        this.zzcfe = this.zzsa();
        this.zzbjx = this.zzsb();
    }

    @Override  // com.google.android.gms.ads.instream.InstreamAd
    public final void destroy() {
        try {
            this.zzczn.destroy();
            this.zzcfe = null;
            this.zzbjx = null;
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.instream.InstreamAd
    public final float getAspectRatio() {
        return this.zzcfe == null ? 0.0f : this.zzcfe.getAspectRatio();
    }

    @Override  // com.google.android.gms.ads.instream.InstreamAd
    public final MediaContent getMediaContent() {
        return this.zzbjx;
    }

    @Override  // com.google.android.gms.ads.instream.InstreamAd
    public final VideoController getVideoController() {
        return this.zzcfe;
    }

    @Override  // com.google.android.gms.ads.instream.InstreamAd
    public final float getVideoCurrentTime() {
        return this.zzcfe == null ? 0.0f : this.zzcfe.getVideoCurrentTime();
    }

    @Override  // com.google.android.gms.ads.instream.InstreamAd
    public final float getVideoDuration() {
        return this.zzcfe == null ? 0.0f : this.zzcfe.getVideoDuration();
    }

    @Override  // com.google.android.gms.ads.instream.InstreamAd
    public final void zza(InstreamAdView instreamAdView0) {
        if(instreamAdView0 == null) {
            zzazh.zzey("showInView: parameter view must not be null.");
            return;
        }
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(instreamAdView0);
            this.zzczn.zzr(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    private final VideoController zzsa() {
        VideoController videoController0 = new VideoController();
        try {
            videoController0.zza(this.zzczn.getVideoController());
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
        return videoController0;
    }

    private final MediaContent zzsb() {
        try {
            if(this.zzczn.zzrv() != null) {
                return new zzye(this.zzczn.zzrv());
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
        return null;
    }
}

