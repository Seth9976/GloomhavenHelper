package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.Nullable;

@TargetApi(21)
public final class zzbez extends zzbfa {
    public zzbez(zzbdv zzbdv0, zzsn zzsn0, boolean z) {
        super(zzbdv0, zzsn0, z);
    }

    @Override  // android.webkit.WebViewClient
    @Nullable
    public final WebResourceResponse shouldInterceptRequest(WebView webView0, WebResourceRequest webResourceRequest0) {
        return webResourceRequest0 == null || webResourceRequest0.getUrl() == null ? null : this.zza(webView0, webResourceRequest0.getUrl().toString(), webResourceRequest0.getRequestHeaders());
    }
}

