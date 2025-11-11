package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

public final class zzadk extends NativeContentAd {
    private final VideoController zzcfe;
    private final List zzcwz;
    private final AdChoicesInfo zzcxb;
    private final zzadj zzcxc;
    private final zzacs zzcxd;

    public zzadk(zzadj zzadj0) {
        zzacs zzacs1;
        zzacr zzacr0;
        this.zzcwz = new ArrayList();
        this.zzcfe = new VideoController();
        this.zzcxc = zzadj0;
        AdChoicesInfo nativeAd$AdChoicesInfo0 = null;
        try {
            List list0 = this.zzcxc.getImages();
            if(list0 != null) {
                for(Object object0: list0) {
                    if(!(object0 instanceof IBinder) || ((IBinder)object0) == null) {
                        zzacr0 = null;
                    }
                    else {
                        IInterface iInterface0 = ((IBinder)object0).queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                        zzacr0 = iInterface0 instanceof zzacr ? ((zzacr)iInterface0) : new zzact(((IBinder)object0));
                    }
                    if(zzacr0 != null) {
                        zzacs zzacs0 = new zzacs(zzacr0);
                        this.zzcwz.add(zzacs0);
                    }
                }
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        try {
            zzacr zzacr1 = this.zzcxc.zzrn();
            zzacs1 = zzacr1 == null ? null : new zzacs(zzacr1);
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
            zzacs1 = null;
        }
        try {
            this.zzcxd = zzacs1;
            if(this.zzcxc.zzrl() != null) {
                nativeAd$AdChoicesInfo0 = new zzack(this.zzcxc.zzrl());
            }
        }
        catch(RemoteException remoteException2) {
            zzazh.zzc("", remoteException2);
        }
        this.zzcxb = nativeAd$AdChoicesInfo0;
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final void destroy() {
        try {
            this.zzcxc.destroy();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final AdChoicesInfo getAdChoicesInfo() {
        return this.zzcxb;
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getAdvertiser() {
        try {
            return this.zzcxc.getAdvertiser();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getBody() {
        try {
            return this.zzcxc.getBody();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getCallToAction() {
        try {
            return this.zzcxc.getCallToAction();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final Bundle getExtras() {
        try {
            return this.zzcxc.getExtras();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getHeadline() {
        try {
            return this.zzcxc.getHeadline();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final List getImages() {
        return this.zzcwz;
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final Image getLogo() {
        return this.zzcxd;
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzcxc.getMediationAdapterClassName();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeContentAd
    public final VideoController getVideoController() {
        try {
            if(this.zzcxc.getVideoController() != null) {
                zzxj zzxj0 = this.zzcxc.getVideoController();
                this.zzcfe.zza(zzxj0);
                return this.zzcfe;
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Exception occurred while getting video controller", remoteException0);
        }
        return this.zzcfe;
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd
    public final void performClick(Bundle bundle0) {
        try {
            this.zzcxc.performClick(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd
    public final boolean recordImpression(Bundle bundle0) {
        try {
            return this.zzcxc.recordImpression(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return false;
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd
    public final void reportTouchEvent(Bundle bundle0) {
        try {
            this.zzcxc.reportTouchEvent(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd
    protected final Object zzjo() {
        return this.zzrj();
    }

    private final IObjectWrapper zzrj() {
        try {
            return this.zzcxc.zzrj();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }
}

