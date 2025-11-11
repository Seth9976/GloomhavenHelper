package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.webkit.ValueCallback;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import jeb.synthetic.FIN;

@ParametersAreNonnullByDefault
public class zzbfz extends zzbfy implements zzbge {
    @GuardedBy("this")
    private boolean zzehn;
    @GuardedBy("this")
    private boolean zzeig;
    private final zzbfx zzejj;

    public zzbfz(Context context0, zzbfx zzbfx0) {
        super(context0);
        zzq.zzkz().zzvh();
        this.zzejj = zzbfx0;
        super.setWebViewClient(zzbfx0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfy
    public void addJavascriptInterface(Object object0, String s) {
        super.addJavascriptInterface(object0, s);
    }

    @Override  // android.webkit.WebView
    public void destroy() {
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        if(this.zzehn) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(this);
            FIN.finallyCodeEnd$NT(v);
            return;
        }
        this.zzehn = true;
        this.zzejj.zza(this);
        this.zzbe(false);
        zzawf.zzee("Initiating WebView self destruct sequence in 3...");
        zzawf.zzee("Loading blank page in WebView, 2...");
        try {
            super.loadUrl("about:blank");
            FIN.finallyExec$NT(v);
        }
        catch(UnsatisfiedLinkError unsatisfiedLinkError0) {
            zzq.zzkz().zza(unsatisfiedLinkError0, "AdWebViewImpl.loadUrlUnsafe");
            zzawf.zze("#007 Could not call remote method.", unsatisfiedLinkError0);
            FIN.finallyExec$NT(v);
        }
    }

    @Override  // android.webkit.WebView
    @TargetApi(19)
    public void evaluateJavascript(String s, ValueCallback valueCallback0) {
        synchronized(this) {
            if(this.isDestroyed()) {
                zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
                if(valueCallback0 != null) {
                    valueCallback0.onReceiveValue(null);
                }
                return;
            }
            super.evaluateJavascript(s, valueCallback0);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            synchronized(this) {
                if(!this.isDestroyed()) {
                    this.zzbe(true);
                }
                this.zzvi();
            }
        }
        finally {
            super.finalize();
        }
    }

    public final boolean isDestroyed() {
        synchronized(this) {
        }
        return this.zzehn;
    }

    @Override  // android.webkit.WebView
    public void loadData(String s, String s1, String s2) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                super.loadData(s, s1, s2);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override  // android.webkit.WebView
    public void loadDataWithBaseURL(String s, String s1, String s2, String s3, String s4) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, s1, s2, s3, s4);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbfy
    public void loadUrl(String s) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                super.loadUrl(s);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override  // android.webkit.WebView
    @TargetApi(21)
    protected void onDraw(Canvas canvas0) {
        if(this.isDestroyed()) {
            return;
        }
        super.onDraw(canvas0);
    }

    @Override  // android.webkit.WebView
    public void onPause() {
        if(this.isDestroyed()) {
            return;
        }
        super.onPause();
    }

    @Override  // android.webkit.WebView
    public void onResume() {
        if(this.isDestroyed()) {
            return;
        }
        super.onResume();
    }

    // 去混淆评级： 低(20)
    @Override  // android.webkit.WebView
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return !this.isDestroyed() && super.onTouchEvent(motionEvent0);
    }

    @Override  // android.webkit.WebView
    public void setWebViewClient(WebViewClient webViewClient0) {
    }

    @Override  // android.webkit.WebView
    public void stopLoading() {
        if(this.isDestroyed()) {
            return;
        }
        super.stopLoading();
    }

    @Override  // com.google.android.gms.internal.ads.zzbge
    public final void zza(zzbga zzbga0) {
        synchronized(this) {
            zzawf.zzee("Blank page loaded, 1...");
            this.zzaal();
        }
    }

    @VisibleForTesting
    protected void zzaal() {
        synchronized(this) {
            zzawf.zzee("Destroying WebView!");
            this.zzvi();
            zzbgc zzbgc0 = () -> super.destroy();
            zzazq.zzdxo.execute(zzbgc0);
        }
    }

    // 检测为 Lambda 实现
    final void zzacc() [...]

    @GuardedBy("this")
    protected void zzbe(boolean z) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbfy
    public void zzcz(String s) {
        super.zzcz(s);
    }

    private final void zzvi() {
        synchronized(this) {
            if(!this.zzeig) {
                this.zzeig = true;
                zzq.zzkz().zzvi();
            }
        }
    }
}

