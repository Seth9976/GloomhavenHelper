package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@TargetApi(11)
public final class zzbex extends zzbfa {
    public zzbex(zzbdv zzbdv0, zzsn zzsn0, boolean z) {
        super(zzbdv0, zzsn0, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdy
    public final WebResourceResponse shouldInterceptRequest(WebView webView0, String s) {
        return this.zza(webView0, s, null);
    }
}

