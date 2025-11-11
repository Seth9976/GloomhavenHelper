package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzye implements MediaContent {
    private final VideoController zzcfe;
    private final zzacm zzcfx;

    public zzye(zzacm zzacm0) {
        this.zzcfe = new VideoController();
        this.zzcfx = zzacm0;
    }

    @Override  // com.google.android.gms.ads.MediaContent
    public final float getAspectRatio() {
        try {
            return this.zzcfx.getAspectRatio();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return 0.0f;
        }
    }

    @Override  // com.google.android.gms.ads.MediaContent
    public final float getCurrentTime() {
        try {
            return this.zzcfx.getCurrentTime();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return 0.0f;
        }
    }

    @Override  // com.google.android.gms.ads.MediaContent
    public final float getDuration() {
        try {
            return this.zzcfx.getDuration();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return 0.0f;
        }
    }

    @Override  // com.google.android.gms.ads.MediaContent
    public final Drawable getMainImage() {
        try {
            IObjectWrapper iObjectWrapper0 = this.zzcfx.zzri();
            if(iObjectWrapper0 != null) {
                return (Drawable)ObjectWrapper.unwrap(iObjectWrapper0);
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        return null;
    }

    @Override  // com.google.android.gms.ads.MediaContent
    public final VideoController getVideoController() {
        try {
            if(this.zzcfx.getVideoController() != null) {
                zzxj zzxj0 = this.zzcfx.getVideoController();
                this.zzcfe.zza(zzxj0);
                return this.zzcfe;
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Exception occurred while getting video controller", remoteException0);
        }
        return this.zzcfe;
    }

    @Override  // com.google.android.gms.ads.MediaContent
    public final boolean hasVideoContent() {
        try {
            return this.zzcfx.hasVideoContent();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return false;
        }
    }

    @Override  // com.google.android.gms.ads.MediaContent
    public final void setMainImage(Drawable drawable0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(drawable0);
            this.zzcfx.zzo(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    public final zzacm zzqa() {
        return this.zzcfx;
    }
}

