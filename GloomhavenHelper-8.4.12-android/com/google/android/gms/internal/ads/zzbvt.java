package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;

public final class zzbvt implements zzrp {
    private zzdkq zzflt;

    @Override  // com.google.android.gms.internal.ads.zzrp
    public final View getView() {
        return this.zzflt;
    }

    @Override  // com.google.android.gms.internal.ads.zzrp
    public final WebView getWebView() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzrp
    public final void onPageStarted(WebView webView0, String s, Bitmap bitmap0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzrp
    public final void zza(Activity activity0, WebView webView0) {
        try {
            this.zzflt = new zzdkq(activity0, webView0);
        }
        catch(RuntimeException runtimeException0) {
            zzawf.zzey((" Failed to initialize the internal ArWebView: " + runtimeException0));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzrp
    public final void zzc(String s, String s1) {
        if(this.zzflt != null) {
            throw new NullPointerException();
        }
        zzawf.zzey("ArWebView is not initialized.");
    }
}

