package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzawt implements Callable {
    private final Context zzcgz;
    private final WebSettings zzdud;

    zzawt(Context context0, WebSettings webSettings0) {
        this.zzcgz = context0;
        this.zzdud = webSettings0;
    }

    @Override
    public final Object call() {
        Context context0 = this.zzcgz;
        WebSettings webSettings0 = this.zzdud;
        if(context0.getCacheDir() != null) {
            webSettings0.setAppCachePath(context0.getCacheDir().getAbsolutePath());
            webSettings0.setAppCacheMaxSize(0L);
            webSettings0.setAppCacheEnabled(true);
        }
        webSettings0.setDatabasePath(context0.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
        webSettings0.setDatabaseEnabled(true);
        webSettings0.setDomStorageEnabled(true);
        webSettings0.setDisplayZoomControls(false);
        webSettings0.setBuiltInZoomControls(true);
        webSettings0.setSupportZoom(true);
        webSettings0.setAllowContentAccess(false);
        return true;
    }
}

