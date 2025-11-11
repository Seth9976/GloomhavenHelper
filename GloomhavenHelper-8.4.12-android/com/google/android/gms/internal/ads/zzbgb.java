package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebView;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

final class zzbgb {
    @VisibleForTesting
    @GuardedBy("InvokeJavascriptWorkaround.class")
    private static Boolean zzejk;

    @TargetApi(19)
    static void zza(WebView webView0, String s) {
        if(zzbgb.zzb(webView0)) {
            webView0.evaluateJavascript(s, null);
            return;
        }
        String s1 = String.valueOf(s);
        webView0.loadUrl((s1.length() == 0 ? new String("javascript:") : "javascript:" + s1));
    }

    @TargetApi(19)
    private static boolean zzb(WebView webView0) {
        synchronized(zzbgb.class) {
            if(zzbgb.zzejk == null) {
                try {
                    webView0.evaluateJavascript("(function(){})()", null);
                    zzbgb.zzejk = Boolean.TRUE;
                }
                catch(IllegalStateException unused_ex) {
                    zzbgb.zzejk = Boolean.FALSE;
                }
            }
        }
        return zzbgb.zzejk.booleanValue();
    }
}

