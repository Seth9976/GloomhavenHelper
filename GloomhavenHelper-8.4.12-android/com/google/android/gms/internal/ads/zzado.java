package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.DisplayOpenMeasurement;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import java.util.WeakHashMap;

public final class zzado implements NativeCustomTemplateAd {
    private final VideoController zzcfe;
    private static WeakHashMap zzcxe;
    private final zzadn zzcxf;
    private final MediaView zzcxg;
    private DisplayOpenMeasurement zzcxh;

    static {
        zzado.zzcxe = new WeakHashMap();
    }

    @VisibleForTesting
    private zzado(zzadn zzadn0) {
        Context context0;
        this.zzcfe = new VideoController();
        this.zzcxf = zzadn0;
        MediaView mediaView0 = null;
        try {
            context0 = (Context)ObjectWrapper.unwrap(zzadn0.zzro());
        }
        catch(NullPointerException | RemoteException nullPointerException0) {
            zzazh.zzc("", nullPointerException0);
            context0 = null;
        }
        if(context0 != null) {
            MediaView mediaView1 = new MediaView(context0);
            try {
                IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediaView1);
                if(this.zzcxf.zzp(iObjectWrapper0)) {
                    mediaView0 = mediaView1;
                }
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("", remoteException0);
            }
        }
        this.zzcxg = mediaView0;
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void destroy() {
        try {
            this.zzcxf.destroy();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final List getAvailableAssetNames() {
        try {
            return this.zzcxf.getAvailableAssetNames();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final String getCustomTemplateId() {
        try {
            return this.zzcxf.getCustomTemplateId();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final DisplayOpenMeasurement getDisplayOpenMeasurement() {
        try {
            if(this.zzcxh == null && this.zzcxf.zzrp()) {
                this.zzcxh = new zzacn(this.zzcxf);
                return this.zzcxh;
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        return this.zzcxh;
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final Image getImage(String s) {
        try {
            zzacr zzacr0 = this.zzcxf.zzcv(s);
            if(zzacr0 != null) {
                return new zzacs(zzacr0);
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        return null;
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final CharSequence getText(String s) {
        try {
            return this.zzcxf.zzcu(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final VideoController getVideoController() {
        try {
            zzxj zzxj0 = this.zzcxf.getVideoController();
            if(zzxj0 != null) {
                this.zzcfe.zza(zzxj0);
                return this.zzcfe;
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Exception occurred while getting video controller", remoteException0);
        }
        return this.zzcfe;
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final MediaView getVideoMediaView() {
        return this.zzcxg;
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void performClick(String s) {
        try {
            this.zzcxf.performClick(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void recordImpression() {
        try {
            this.zzcxf.recordImpression();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    public static zzado zza(zzadn zzadn0) {
        synchronized(zzado.zzcxe) {
            zzado zzado0 = (zzado)zzado.zzcxe.get(zzadn0.asBinder());
            if(zzado0 != null) {
                return zzado0;
            }
            zzado zzado1 = new zzado(zzadn0);
            zzado.zzcxe.put(zzadn0.asBinder(), zzado1);
            return zzado1;
        }
    }

    public final zzadn zzrs() {
        return this.zzcxf;
    }
}

