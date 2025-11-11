package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzq;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
class zzbfy extends WebView {
    public zzbfy(Context context0) {
        super(context0);
        this.setBackgroundColor(0);
        WebSettings webSettings0 = this.getSettings();
        webSettings0.setAllowFileAccess(false);
        webSettings0.setSavePassword(false);
        webSettings0.setSupportMultipleWindows(true);
        webSettings0.setJavaScriptCanOpenWindowsAutomatically(true);
        if(Build.VERSION.SDK_INT >= 21) {
            webSettings0.setMixedContentMode(2);
        }
        zzq.zzkx().zza(this.getContext(), webSettings0);
        this.removeJavascriptInterface("accessibility");
        this.removeJavascriptInterface("accessibilityTraversal");
        try {
            this.getSettings().setJavaScriptEnabled(true);
        }
        catch(NullPointerException nullPointerException0) {
            zzawf.zzc("Unable to enable Javascript.", nullPointerException0);
        }
    }

    @Override  // android.webkit.WebView
    public void addJavascriptInterface(Object object0, String s) {
        if(Build.VERSION.SDK_INT >= 17) {
            super.addJavascriptInterface(object0, s);
            return;
        }
        zzawf.zzee("Ignore addJavascriptInterface due to low Android version.");
    }

    @Override  // android.webkit.WebView
    public void loadUrl(String s) {
        try {
            super.loadUrl(s);
        }
        catch(Exception | NoClassDefFoundError | IncompatibleClassChangeError exception0) {
            zzq.zzkz().zza(exception0, "CoreWebView.loadUrl");
            zzawf.zze("#007 Could not call remote method.", exception0);
        }
    }

    public void zzcz(String s) {
        zzbgb.zza(this, s);
    }
}

