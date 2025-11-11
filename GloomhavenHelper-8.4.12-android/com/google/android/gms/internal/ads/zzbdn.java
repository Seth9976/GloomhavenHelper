package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.WindowManager.BadTokenException;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzc;

@TargetApi(11)
public final class zzbdn extends WebChromeClient {
    private final zzbdv zzdae;

    public zzbdn(zzbdv zzbdv0) {
        this.zzdae = zzbdv0;
    }

    @Override  // android.webkit.WebChromeClient
    public final void onCloseWindow(WebView webView0) {
        if(!(webView0 instanceof zzbdv)) {
            zzawf.zzfa("Tried to close a WebView that wasn\'t an AdWebView.");
            return;
        }
        zze zze0 = ((zzbdv)webView0).zzaab();
        if(zze0 == null) {
            zzawf.zzfa("Tried to close an AdWebView not associated with an overlay.");
            return;
        }
        zze0.close();
    }

    @Override  // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage0) {
        String s = "JS: " + consoleMessage0.message() + " (" + consoleMessage0.sourceId() + ":" + consoleMessage0.lineNumber() + ")";
        if(s.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage0);
        }
        switch(zzbdw.zzefj[consoleMessage0.messageLevel().ordinal()]) {
            case 1: {
                zzawf.zzey(s);
                return super.onConsoleMessage(consoleMessage0);
            }
            case 2: {
                zzawf.zzfa(s);
                return super.onConsoleMessage(consoleMessage0);
            }
            case 3: 
            case 4: {
                zzawf.zzez(s);
                return super.onConsoleMessage(consoleMessage0);
            }
            case 5: {
                zzawf.zzeb(s);
                return super.onConsoleMessage(consoleMessage0);
            }
            default: {
                zzawf.zzez(s);
                return super.onConsoleMessage(consoleMessage0);
            }
        }
    }

    @Override  // android.webkit.WebChromeClient
    public final boolean onCreateWindow(WebView webView0, boolean z, boolean z1, Message message0) {
        WebView.WebViewTransport webView$WebViewTransport0 = (WebView.WebViewTransport)message0.obj;
        WebView webView1 = new WebView(webView0.getContext());
        if(this.zzdae.zzaag() != null) {
            webView1.setWebViewClient(this.zzdae.zzaag());
        }
        webView$WebViewTransport0.setWebView(webView1);
        message0.sendToTarget();
        return true;
    }

    @Override  // android.webkit.WebChromeClient
    public final void onExceededDatabaseQuota(String s, String s1, long v, long v1, long v2, WebStorage.QuotaUpdater webStorage$QuotaUpdater0) {
        if(0x500000L - v2 <= 0L) {
            webStorage$QuotaUpdater0.updateQuota(v);
            return;
        }
        if(v != 0L) {
            if(v1 == 0L) {
                v1 = Math.min(v + Math.min(0x20000L, 0x500000L - v2), 0x100000L);
            }
            else {
                if(v1 <= Math.min(0x100000L - v, 0x500000L - v2)) {
                    v += v1;
                }
                v1 = v;
            }
        }
        else if(v1 > 0x500000L - v2 || v1 > 0x100000L) {
            v1 = 0L;
        }
        webStorage$QuotaUpdater0.updateQuota(v1);
    }

    @Override  // android.webkit.WebChromeClient
    public final void onGeolocationPermissionsShowPrompt(String s, GeolocationPermissions.Callback geolocationPermissions$Callback0) {
        if(geolocationPermissions$Callback0 != null) {
            geolocationPermissions$Callback0.invoke(s, zzawo.zzq(this.zzdae.getContext(), "android.permission.ACCESS_FINE_LOCATION") || zzawo.zzq(this.zzdae.getContext(), "android.permission.ACCESS_COARSE_LOCATION"), true);
        }
    }

    @Override  // android.webkit.WebChromeClient
    public final void onHideCustomView() {
        zze zze0 = this.zzdae.zzaab();
        if(zze0 == null) {
            zzawf.zzfa("Could not get ad overlay when hiding custom view.");
            return;
        }
        zze0.zztp();
    }

    @Override  // android.webkit.WebChromeClient
    public final boolean onJsAlert(WebView webView0, String s, String s1, JsResult jsResult0) {
        return this.zza(zzbdn.zza(webView0), "alert", s, s1, null, jsResult0, null, false);
    }

    @Override  // android.webkit.WebChromeClient
    public final boolean onJsBeforeUnload(WebView webView0, String s, String s1, JsResult jsResult0) {
        return this.zza(zzbdn.zza(webView0), "onBeforeUnload", s, s1, null, jsResult0, null, false);
    }

    @Override  // android.webkit.WebChromeClient
    public final boolean onJsConfirm(WebView webView0, String s, String s1, JsResult jsResult0) {
        return this.zza(zzbdn.zza(webView0), "confirm", s, s1, null, jsResult0, null, false);
    }

    @Override  // android.webkit.WebChromeClient
    public final boolean onJsPrompt(WebView webView0, String s, String s1, String s2, JsPromptResult jsPromptResult0) {
        return this.zza(zzbdn.zza(webView0), "prompt", s, s1, s2, null, jsPromptResult0, true);
    }

    @Override  // android.webkit.WebChromeClient
    @TargetApi(21)
    public final void onPermissionRequest(PermissionRequest permissionRequest0) {
        if(!((Boolean)zzabu.zzcvy.get()).booleanValue()) {
            if(this.zzdae != null && this.zzdae.zzaaf() != null && this.zzdae.zzaaf().zzabk() != null) {
                String[] arr_s = this.zzdae.zzaaf().zzabk().zza(permissionRequest0.getResources());
                if(arr_s.length > 0) {
                    permissionRequest0.grant(arr_s);
                    return;
                }
                permissionRequest0.deny();
                return;
            }
            super.onPermissionRequest(permissionRequest0);
            return;
        }
        super.onPermissionRequest(permissionRequest0);
    }

    public final void onReachedMaxAppCacheSize(long v, long v1, WebStorage.QuotaUpdater webStorage$QuotaUpdater0) {
        if(0x500000L - v1 < v + 0x20000L) {
            webStorage$QuotaUpdater0.updateQuota(0L);
            return;
        }
        webStorage$QuotaUpdater0.updateQuota(v + 0x20000L);
    }

    @Override  // android.webkit.WebChromeClient
    @Deprecated
    public final void onShowCustomView(View view0, int v, WebChromeClient.CustomViewCallback webChromeClient$CustomViewCallback0) {
        zze zze0 = this.zzdae.zzaab();
        if(zze0 == null) {
            zzawf.zzfa("Could not get ad overlay when showing custom view.");
            webChromeClient$CustomViewCallback0.onCustomViewHidden();
            return;
        }
        zze0.zza(view0, webChromeClient$CustomViewCallback0);
        zze0.setRequestedOrientation(v);
    }

    @Override  // android.webkit.WebChromeClient
    public final void onShowCustomView(View view0, WebChromeClient.CustomViewCallback webChromeClient$CustomViewCallback0) {
        this.onShowCustomView(view0, -1, webChromeClient$CustomViewCallback0);
    }

    private static Context zza(WebView webView0) {
        if(!(webView0 instanceof zzbdv)) {
            return webView0.getContext();
        }
        Context context0 = ((zzbdv)webView0).zzys();
        return context0 == null ? ((zzbdv)webView0).getContext() : context0;
    }

    private final boolean zza(Context context0, String s, String s1, String s2, String s3, JsResult jsResult0, JsPromptResult jsPromptResult0, boolean z) {
        try {
            if(this.zzdae != null && this.zzdae.zzaaf() != null && this.zzdae.zzaaf().zzaax() != null) {
                zzc zzc0 = this.zzdae.zzaaf().zzaax();
                if(zzc0 != null && !zzc0.zzjv()) {
                    zzc0.zzbr("window." + s + "(\'" + s2 + "\')");
                    return false;
                }
            }
            AlertDialog.Builder alertDialog$Builder0 = new AlertDialog.Builder(context0);
            alertDialog$Builder0.setTitle(s1);
            if(z) {
                LinearLayout linearLayout0 = new LinearLayout(context0);
                linearLayout0.setOrientation(1);
                TextView textView0 = new TextView(context0);
                textView0.setText(s2);
                EditText editText0 = new EditText(context0);
                editText0.setText(s3);
                linearLayout0.addView(textView0);
                linearLayout0.addView(editText0);
                alertDialog$Builder0.setView(linearLayout0).setPositiveButton(0x104000A, new zzbdt(jsPromptResult0, editText0)).setNegativeButton(0x1040000, new zzbdu(jsPromptResult0)).setOnCancelListener(new zzbdr(jsPromptResult0)).create().show();
                return true;
            }
            alertDialog$Builder0.setMessage(s2).setPositiveButton(0x104000A, new zzbds(jsResult0)).setNegativeButton(0x1040000, new zzbdp(jsResult0)).setOnCancelListener(new zzbdq(jsResult0)).create().show();
        }
        catch(WindowManager.BadTokenException windowManager$BadTokenException0) {
            zzawf.zzd("Fail to display Dialog.", windowManager$BadTokenException0);
        }
        return true;
    }
}

