package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class zzbfx extends WebViewClient {
    private static final String[] zzejg;
    private static final String[] zzejh;
    private zzbge zzeji;

    static {
        zzbfx.zzejg = new String[]{"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
        zzbfx.zzejh = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    }

    @Override  // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView0, String s) {
        if(s == null) {
            return;
        }
        String s1 = String.valueOf(s);
        zzawf.zzee((s1.length() == 0 ? new String("Loading resource: ") : "Loading resource: " + s1));
        this.zzb(new zzbga(s));
    }

    @Override  // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView0, String s) {
        if(s == null) {
            return;
        }
        zzbga zzbga0 = new zzbga(s);
        zzbge zzbge0 = this.zzeji;
        if(zzbge0 != null) {
            zzbge0.zza(zzbga0);
            return;
        }
        this.zza(zzbga0);
    }

    @Override  // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView0, int v, String s, String s1) {
    }

    @Override  // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView webView0, SslErrorHandler sslErrorHandler0, SslError sslError0) {
        if(sslError0 == null) {
            return;
        }
        boolean z = sslError0.getPrimaryError() < 0;
        sslError0.getUrl();
    }

    @Override  // android.webkit.WebViewClient
    @TargetApi(24)
    public final WebResourceResponse shouldInterceptRequest(WebView webView0, WebResourceRequest webResourceRequest0) {
        return webResourceRequest0 == null || webResourceRequest0.getUrl() == null ? null : this.zzd(new zzbga(webResourceRequest0));
    }

    @Override  // android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView0, String s) {
        return s == null ? null : this.zzd(new zzbga(s));
    }

    @Override  // android.webkit.WebViewClient
    public final boolean shouldOverrideKeyEvent(WebView webView0, KeyEvent keyEvent0) {
        int v = keyEvent0.getKeyCode();
        if(v != 0x4F && v != 0xDE) {
            switch(v) {
                case 85: 
                case 86: 
                case 87: 
                case 88: 
                case 89: 
                case 90: 
                case 91: {
                    break;
                }
                default: {
                    return v == 0x7E || v == 0x7F || v == 0x80 || v == 0x81 || v == 130;
                }
            }
        }
        return true;
    }

    @Override  // android.webkit.WebViewClient
    @TargetApi(24)
    public final boolean shouldOverrideUrlLoading(WebView webView0, WebResourceRequest webResourceRequest0) {
        return webResourceRequest0 == null || webResourceRequest0.getUrl() == null ? false : this.zzc(new zzbga(webResourceRequest0));
    }

    @Override  // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView0, String s) {
        return s == null ? false : this.zzc(new zzbga(s));
    }

    public void zza(zzbga zzbga0) {
    }

    final void zza(zzbge zzbge0) {
        this.zzeji = zzbge0;
    }

    public void zzb(zzbga zzbga0) {
    }

    public boolean zzc(zzbga zzbga0) {
        return false;
    }

    @Nullable
    public WebResourceResponse zzd(zzbga zzbga0) {
        return null;
    }
}

