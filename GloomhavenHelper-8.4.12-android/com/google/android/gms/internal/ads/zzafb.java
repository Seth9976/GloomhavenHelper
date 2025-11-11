package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzafb extends zzaee {
    private final OnPublisherAdViewLoadedListener zzcxr;

    public zzafb(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener0) {
        this.zzcxr = onPublisherAdViewLoadedListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaef
    public final void zza(zzvx zzvx0, IObjectWrapper iObjectWrapper0) {
        if(zzvx0 != null && iObjectWrapper0 != null) {
            PublisherAdView publisherAdView0 = new PublisherAdView(((Context)ObjectWrapper.unwrap(iObjectWrapper0)));
            AppEventListener appEventListener0 = null;
            try {
                if(zzvx0.zzki() instanceof zzud) {
                    zzud zzud0 = (zzud)zzvx0.zzki();
                    publisherAdView0.setAdListener((zzud0 == null ? null : zzud0.getAdListener()));
                }
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("", remoteException0);
            }
            try {
                if(zzvx0.zzkh() instanceof zzuo) {
                    zzuo zzuo0 = (zzuo)zzvx0.zzkh();
                    if(zzuo0 != null) {
                        appEventListener0 = zzuo0.getAppEventListener();
                    }
                    publisherAdView0.setAppEventListener(appEventListener0);
                }
            }
            catch(RemoteException remoteException1) {
                zzazh.zzc("", remoteException1);
            }
            zzafa zzafa0 = new zzafa(this, publisherAdView0, zzvx0);
            zzayx.zzyy.post(zzafa0);
        }
    }
}

