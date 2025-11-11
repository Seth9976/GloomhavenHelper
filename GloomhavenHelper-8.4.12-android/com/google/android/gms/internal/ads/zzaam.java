package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzaam implements CustomRenderedAd {
    private final zzaap zzctd;

    public zzaam(zzaap zzaap0) {
        this.zzctd = zzaap0;
    }

    @Override  // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final String getBaseUrl() {
        try {
            return this.zzctd.zzqx();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final String getContent() {
        try {
            return this.zzctd.getContent();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void onAdRendered(View view0) {
        try {
            IObjectWrapper iObjectWrapper0 = view0 == null ? null : ObjectWrapper.wrap(view0);
            this.zzctd.zzn(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void recordClick() {
        try {
            this.zzctd.recordClick();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void recordImpression() {
        try {
            this.zzctd.recordImpression();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

