package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;

final class zzql implements Runnable {
    private ValueCallback zzbqe;
    final zzqd zzbqf;
    final WebView zzbqg;
    final boolean zzbqh;
    final zzqj zzbqi;

    zzql(zzqj zzqj0, zzqd zzqd0, WebView webView0, boolean z) {
        this.zzbqi = zzqj0;
        this.zzbqf = zzqd0;
        this.zzbqg = webView0;
        this.zzbqh = z;
        super();
        this.zzbqe = new zzqo(this);
    }

    @Override
    public final void run() {
        if(this.zzbqg.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzbqg.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzbqe);
            }
            catch(Throwable unused_ex) {
                this.zzbqe.onReceiveValue("");
            }
        }
    }
}

