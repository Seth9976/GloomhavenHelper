package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzbym extends zzacp {
    private final zzbyz zzfne;
    private IObjectWrapper zzfob;

    public zzbym(zzbyz zzbyz0) {
        this.zzfne = zzbyz0;
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final float getAspectRatio() throws RemoteException {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcrh)).booleanValue()) {
            return 0.0f;
        }
        if(this.zzfne.getMediaContentAspectRatio() != 0.0f) {
            return this.zzfne.getMediaContentAspectRatio();
        }
        if(this.zzfne.getVideoController() != null) {
            return this.zzajy();
        }
        IObjectWrapper iObjectWrapper0 = this.zzfob;
        if(iObjectWrapper0 != null) {
            return zzbym.zzar(iObjectWrapper0);
        }
        zzacr zzacr0 = this.zzfne.zzakg();
        if(zzacr0 == null) {
            return 0.0f;
        }
        float f = zzacr0 == null || zzacr0.getWidth() == -1 || zzacr0.getHeight() == -1 ? 0.0f : ((float)zzacr0.getWidth()) / ((float)zzacr0.getHeight());
        return f == 0.0f ? zzbym.zzar(zzacr0.zzrg()) : f;
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final float getCurrentTime() throws RemoteException {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcri)).booleanValue()) {
            return 0.0f;
        }
        return this.zzfne.getVideoController() == null ? 0.0f : this.zzfne.getVideoController().getCurrentTime();
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final float getDuration() throws RemoteException {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcri)).booleanValue()) {
            return 0.0f;
        }
        return this.zzfne.getVideoController() == null ? 0.0f : this.zzfne.getVideoController().getDuration();
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final zzxj getVideoController() throws RemoteException {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcri)).booleanValue() ? this.zzfne.getVideoController() : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final boolean hasVideoContent() throws RemoteException {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcri)).booleanValue() ? this.zzfne.getVideoController() != null : false;
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final void zza(zzaed zzaed0) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcri)).booleanValue()) {
            return;
        }
        if(this.zzfne.getVideoController() instanceof zzbeq) {
            ((zzbeq)this.zzfne.getVideoController()).zza(zzaed0);
        }
    }

    private final float zzajy() {
        try {
            return this.zzfne.getVideoController().getAspectRatio();
        }
        catch(RemoteException remoteException0) {
            zzawf.zzc("Remote exception getting video controller aspect ratio.", remoteException0);
            return 0.0f;
        }
    }

    private static float zzar(IObjectWrapper iObjectWrapper0) {
        if(iObjectWrapper0 == null) {
            return 0.0f;
        }
        Drawable drawable0 = (Drawable)ObjectWrapper.unwrap(iObjectWrapper0);
        return drawable0 == null || drawable0.getIntrinsicWidth() == -1 || drawable0.getIntrinsicHeight() == -1 ? 0.0f : ((float)drawable0.getIntrinsicWidth()) / ((float)drawable0.getIntrinsicHeight());
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final void zzo(IObjectWrapper iObjectWrapper0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmz)).booleanValue()) {
            this.zzfob = iObjectWrapper0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacm
    public final IObjectWrapper zzri() throws RemoteException {
        IObjectWrapper iObjectWrapper0 = this.zzfob;
        if(iObjectWrapper0 != null) {
            return iObjectWrapper0;
        }
        zzacr zzacr0 = this.zzfne.zzakg();
        return zzacr0 == null ? null : zzacr0.zzrg();
    }
}

