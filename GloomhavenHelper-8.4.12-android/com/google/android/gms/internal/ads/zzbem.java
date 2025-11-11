package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@VisibleForTesting
@ParametersAreNonnullByDefault
final class zzbem extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzbdv {
    private int maxHeight;
    private int maxWidth;
    @GuardedBy("this")
    private String zzabk;
    private final zzazo zzblu;
    private final WindowManager zzbnu;
    private int zzdhj;
    private int zzdhk;
    @GuardedBy("this")
    private boolean zzdiv;
    @GuardedBy("this")
    private String zzdkd;
    @GuardedBy("this")
    private Boolean zzdrq;
    private zzaai zzebu;
    private final zzsn zzefm;
    private final zzbfm zzeha;
    @Nullable
    private final zzdq zzehb;
    private final zzi zzehc;
    private final zza zzehd;
    private final float zzehe;
    @Nullable
    private final zzrp zzehf;
    private final boolean zzehg;
    private boolean zzehh;
    private boolean zzehi;
    private zzbdy zzehj;
    @GuardedBy("this")
    private zze zzehk;
    @GuardedBy("this")
    private IObjectWrapper zzehl;
    @GuardedBy("this")
    private zzbfl zzehm;
    @GuardedBy("this")
    private boolean zzehn;
    @GuardedBy("this")
    private boolean zzeho;
    @GuardedBy("this")
    private boolean zzehp;
    @GuardedBy("this")
    private int zzehq;
    @GuardedBy("this")
    private boolean zzehr;
    @GuardedBy("this")
    private boolean zzehs;
    @GuardedBy("this")
    private zzbeq zzeht;
    @GuardedBy("this")
    private boolean zzehu;
    @GuardedBy("this")
    private boolean zzehv;
    @GuardedBy("this")
    private zzacf zzehw;
    @GuardedBy("this")
    private zzaca zzehx;
    @GuardedBy("this")
    private zzrb zzehy;
    @GuardedBy("this")
    private int zzehz;
    @GuardedBy("this")
    private int zzeia;
    private zzaai zzeib;
    private zzaai zzeic;
    private zzaal zzeid;
    private WeakReference zzeie;
    @GuardedBy("this")
    private zze zzeif;
    @GuardedBy("this")
    private boolean zzeig;
    private zzayy zzeih;
    private Map zzeii;
    private final DisplayMetrics zzwi;

    @VisibleForTesting
    private zzbem(zzbfm zzbfm0, zzbfl zzbfl0, String s, boolean z, boolean z1, @Nullable zzdq zzdq0, zzazo zzazo0, zzaak zzaak0, zzi zzi0, zza zza0, zzsn zzsn0, zzrp zzrp0, boolean z2) {
        super(zzbfm0);
        this.zzehh = false;
        this.zzehi = false;
        this.zzehr = true;
        this.zzehs = false;
        this.zzdkd = "";
        this.zzdhk = -1;
        this.zzdhj = -1;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.zzeha = zzbfm0;
        this.zzehm = zzbfl0;
        this.zzabk = s;
        this.zzeho = z;
        this.zzehq = -1;
        this.zzehb = zzdq0;
        this.zzblu = zzazo0;
        this.zzehc = zzi0;
        this.zzehd = zza0;
        this.zzbnu = (WindowManager)this.getContext().getSystemService("window");
        this.zzwi = zzawo.zza(this.zzbnu);
        this.zzehe = this.zzwi.density;
        this.zzefm = zzsn0;
        this.zzehf = zzrp0;
        this.zzehg = z2;
        this.setBackgroundColor(0);
        WebSettings webSettings0 = this.getSettings();
        webSettings0.setAllowFileAccess(false);
        try {
            webSettings0.setJavaScriptEnabled(true);
        }
        catch(NullPointerException nullPointerException0) {
            zzawf.zzc("Unable to enable Javascript.", nullPointerException0);
        }
        webSettings0.setSavePassword(false);
        webSettings0.setSupportMultipleWindows(true);
        webSettings0.setJavaScriptCanOpenWindowsAutomatically(true);
        if(Build.VERSION.SDK_INT >= 21) {
            webSettings0.setMixedContentMode(2);
        }
        zzq.zzkv().zza(zzbfm0, zzazo0.zzbmj, webSettings0);
        zzq.zzkx().zza(this.getContext(), webSettings0);
        this.setDownloadListener(this);
        this.zzabo();
        this.addJavascriptInterface(zzber.zzc(this), "googleAdsJsInterface");
        this.removeJavascriptInterface("accessibility");
        this.removeJavascriptInterface("accessibilityTraversal");
        this.zzeih = new zzayy(this.zzeha.zzys(), this, this, null);
        this.zzabs();
        this.zzeid = new zzaal(new zzaak(true, "make_wv", this.zzabk));
        this.zzeid.zzqw().zzc(zzaak0);
        this.zzebu = zzaaf.zzb(this.zzeid.zzqw());
        this.zzeid.zza("native:view_create", this.zzebu);
        this.zzeic = null;
        this.zzeib = null;
        zzq.zzkx().zzbf(zzbfm0);
        zzq.zzkz().zzvh();
    }

    @Override  // android.webkit.WebView, com.google.android.gms.internal.ads.zzbdv
    public final void destroy() {
        synchronized(this) {
            this.zzabs();
            this.zzeih.zzxm();
            if(this.zzehk != null) {
                this.zzehk.close();
                this.zzehk.onDestroy();
                this.zzehk = null;
            }
            this.zzehl = null;
            this.zzehj.reset();
            if(this.zzehn) {
                return;
            }
            zzbcx.zzc(this);
            this.zzabr();
            this.zzehn = true;
            zzawf.zzee("Initiating WebView self destruct sequence in 3...");
            zzawf.zzee("Loading blank page in WebView, 2...");
            this.zzfo("about:blank");
        }
    }

    @Override  // android.webkit.WebView
    @TargetApi(19)
    public final void evaluateJavascript(String s, ValueCallback valueCallback0) {
        synchronized(this) {
            if(this.isDestroyed()) {
                zzawf.zzfc("#004 The webview is destroyed. Ignoring action.");
                if(valueCallback0 != null) {
                    valueCallback0.onReceiveValue(null);
                }
                return;
            }
            super.evaluateJavascript(s, valueCallback0);
        }
    }

    @Override
    protected final void finalize() throws Throwable {
        try {
            synchronized(this) {
                if(!this.zzehn) {
                    this.zzehj.reset();
                    zzbcx.zzc(this);
                    this.zzabr();
                    this.zzvi();
                }
            }
        }
        finally {
            super.finalize();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final View getView() {
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final WebView getWebView() {
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean isDestroyed() {
        synchronized(this) {
        }
        return this.zzehn;
    }

    @Override  // android.webkit.WebView, com.google.android.gms.internal.ads.zzbdv
    public final void loadData(String s, String s1, String s2) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                super.loadData(s, s1, s2);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override  // android.webkit.WebView, com.google.android.gms.internal.ads.zzbdv
    public final void loadDataWithBaseURL(String s, String s1, String s2, String s3, String s4) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, s1, s2, s3, s4);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override  // android.webkit.WebView, com.google.android.gms.internal.ads.zzbdv
    public final void loadUrl(String s) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                try {
                    super.loadUrl(s);
                }
                catch(Exception | NoClassDefFoundError | IncompatibleClassChangeError exception0) {
                    zzq.zzkz().zza(exception0, "AdWebViewImpl.loadUrl");
                    zzawf.zzd("Could not call loadUrl. ", exception0);
                    return;
                }
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override  // android.webkit.WebView
    protected final void onAttachedToWindow() {
        synchronized(this) {
            super.onAttachedToWindow();
            if(!this.isDestroyed()) {
                this.zzeih.onAttachedToWindow();
            }
            boolean z = this.zzehu;
            if(this.zzehj != null && this.zzehj.zzaaz()) {
                if(!this.zzehv) {
                    this.zzehv = true;
                }
                this.zzabl();
                z = true;
            }
            this.zzbd(z);
        }
    }

    @Override  // android.view.ViewGroup
    protected final void onDetachedFromWindow() {
        synchronized(this) {
            if(!this.isDestroyed()) {
                this.zzeih.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if(this.zzehv && this.zzehj != null && this.zzehj.zzaaz() && this.getViewTreeObserver() != null && this.getViewTreeObserver().isAlive()) {
                this.zzehv = false;
            }
        }
        this.zzbd(false);
    }

    @Override  // android.webkit.DownloadListener
    public final void onDownloadStart(String s, String s1, String s2, String s3, long v) {
        try {
            Intent intent0 = new Intent("android.intent.action.VIEW");
            intent0.setDataAndType(Uri.parse(s), s3);
            zzawo.zza(this.getContext(), intent0);
        }
        catch(ActivityNotFoundException unused_ex) {
            zzawf.zzeb(("Couldn\'t find an Activity to view url/mimetype: " + s + " / " + s3));
        }
    }

    @Override  // android.webkit.WebView
    @TargetApi(21)
    protected final void onDraw(Canvas canvas0) {
        if(this.isDestroyed()) {
            return;
        }
        if(Build.VERSION.SDK_INT == 21 && canvas0.isHardwareAccelerated() && !this.isAttachedToWindow()) {
            return;
        }
        super.onDraw(canvas0);
    }

    @Override  // android.webkit.WebView
    public final boolean onGenericMotionEvent(MotionEvent motionEvent0) {
        float f = motionEvent0.getAxisValue(9);
        float f1 = motionEvent0.getAxisValue(10);
        return motionEvent0.getActionMasked() != 8 || (f <= 0.0f || this.canScrollVertically(-1)) && (f >= 0.0f || this.canScrollVertically(1)) && (f1 <= 0.0f || this.canScrollHorizontally(-1)) && (f1 >= 0.0f || this.canScrollHorizontally(1)) ? super.onGenericMotionEvent(motionEvent0) : false;
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public final void onGlobalLayout() {
        boolean z = this.zzabl();
        zze zze0 = this.zzaab();
        if(zze0 != null && z) {
            zze0.zztv();
        }
    }

    @Override  // android.webkit.WebView
    @SuppressLint({"DrawAllocation"})
    protected final void onMeasure(int v, int v1) {
        int v14;
        synchronized(this) {
            if(this.isDestroyed()) {
                this.setMeasuredDimension(0, 0);
                return;
            }
            if(!this.isInEditMode() && !this.zzeho && !this.zzehm.zzabz()) {
                if(this.zzehm.zzacb()) {
                    super.onMeasure(v, v1);
                    return;
                }
                if(this.zzehm.zzaca()) {
                    if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcnd)).booleanValue()) {
                        super.onMeasure(v, v1);
                        return;
                    }
                    zzbeq zzbeq0 = this.zzyq();
                    float f = zzbeq0 == null ? 0.0f : zzbeq0.getAspectRatio();
                    if(f == 0.0f) {
                        super.onMeasure(v, v1);
                        return;
                    }
                    int v3 = View.MeasureSpec.getSize(v);
                    int v4 = View.MeasureSpec.getSize(v1);
                    int v5 = (int)(((float)v4) * f);
                    int v6 = (int)(((float)v3) / f);
                    if(v4 == 0 && v6 != 0) {
                        v5 = (int)(((float)v6) * f);
                        v4 = v6;
                    }
                    else if(v3 == 0 && v5 != 0) {
                        v6 = (int)(((float)v5) / f);
                        v3 = v5;
                    }
                    this.setMeasuredDimension(Math.min(v5, v3), Math.min(v6, v4));
                    return;
                }
                if(this.zzehm.isFluid()) {
                    if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcng)).booleanValue()) {
                        this.zza("/contentHeight", new zzbeo(this));
                        this.zzfp("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = \'gmsg://mobileads.google.com/contentHeight?\';  url += \'height=\' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById(\'afma-notify-fluid\');    if (!frame) {      frame = document.createElement(\'IFRAME\');      frame.id = \'afma-notify-fluid\';      frame.style.display = \'none\';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                        float f1 = this.zzwi.density;
                        this.setMeasuredDimension(View.MeasureSpec.getSize(v), (this.zzeia == -1 ? View.MeasureSpec.getSize(v1) : ((int)(((float)this.zzeia) * f1))));
                        return;
                    }
                    super.onMeasure(v, v1);
                    return;
                }
                if(this.zzehm.zzaby()) {
                    this.setMeasuredDimension(this.zzwi.widthPixels, this.zzwi.heightPixels);
                    return;
                }
                int v7 = View.MeasureSpec.getMode(v);
                int v8 = View.MeasureSpec.getSize(v);
                int v9 = View.MeasureSpec.getMode(v1);
                int v10 = View.MeasureSpec.getSize(v1);
                int v11 = 0x7FFFFFFF;
                int v12 = v7 == 0x80000000 || v7 == 0x40000000 ? v8 : 0x7FFFFFFF;
                if(v9 == 0x80000000 || v9 == 0x40000000) {
                    v11 = v10;
                }
                int v13 = this.zzehm.widthPixels > v12 || this.zzehm.heightPixels > v11 ? 1 : 0;
                if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcpn)).booleanValue()) {
                    v14 = ((float)this.zzehm.widthPixels) / this.zzehe > ((float)v12) / this.zzehe || ((float)this.zzehm.heightPixels) / this.zzehe > ((float)v11) / this.zzehe ? 0 : 1;
                    if(v13 == 0) {
                        v14 = 0;
                    }
                }
                else {
                    v14 = v13;
                }
                if(v14 == 0) {
                    if(this.getVisibility() != 8) {
                        this.setVisibility(0);
                    }
                    if(!this.zzehi) {
                        this.zzefm.zza(com.google.android.gms.internal.ads.zzsp.zza.zza.zzbug);
                        this.zzehi = true;
                    }
                    this.setMeasuredDimension(this.zzehm.widthPixels, this.zzehm.heightPixels);
                }
                else {
                    zzawf.zzfa(("Not enough space to show ad. Needs " + ((int)(((float)this.zzehm.widthPixels) / this.zzehe)) + "x" + ((int)(((float)this.zzehm.heightPixels) / this.zzehe)) + " dp, but only has " + ((int)(((float)v8) / this.zzehe)) + "x" + ((int)(((float)v10) / this.zzehe)) + " dp."));
                    if(this.getVisibility() != 8) {
                        this.setVisibility(4);
                    }
                    this.setMeasuredDimension(0, 0);
                    if(!this.zzehh) {
                        this.zzefm.zza(com.google.android.gms.internal.ads.zzsp.zza.zza.zzbuf);
                        this.zzehh = true;
                        return;
                    }
                }
                return;
            }
            super.onMeasure(v, v1);
        }
    }

    @Override  // android.webkit.WebView, com.google.android.gms.internal.ads.zzbdv
    public final void onPause() {
        if(this.isDestroyed()) {
            return;
        }
        try {
            super.onPause();
        }
        catch(Exception exception0) {
            zzawf.zzc("Could not pause webview.", exception0);
        }
    }

    @Override  // android.webkit.WebView, com.google.android.gms.internal.ads.zzbdv
    public final void onResume() {
        if(this.isDestroyed()) {
            return;
        }
        try {
            super.onResume();
        }
        catch(Exception exception0) {
            zzawf.zzc("Could not resume webview.", exception0);
        }
    }

    @Override  // android.webkit.WebView
    public final boolean onTouchEvent(MotionEvent motionEvent0) {
        if(this.zzehj.zzaaz() && !this.zzehj.zzaba()) {
            synchronized(this) {
                if(this.zzehw != null) {
                    this.zzehw.zzc(motionEvent0);
                }
            }
            return this.isDestroyed() ? false : super.onTouchEvent(motionEvent0);
        }
        zzdq zzdq0 = this.zzehb;
        if(zzdq0 != null) {
            zzdq0.zza(motionEvent0);
        }
        return this.isDestroyed() ? false : super.onTouchEvent(motionEvent0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv, android.view.View
    public final void setOnClickListener(View.OnClickListener view$OnClickListener0) {
        this.zzeie = new WeakReference(view$OnClickListener0);
        super.setOnClickListener(view$OnClickListener0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void setRequestedOrientation(int v) {
        synchronized(this) {
            this.zzehq = v;
            if(this.zzehk != null) {
                this.zzehk.setRequestedOrientation(this.zzehq);
            }
        }
    }

    @Override  // android.webkit.WebView, com.google.android.gms.internal.ads.zzbdv
    public final void setWebViewClient(WebViewClient webViewClient0) {
        super.setWebViewClient(webViewClient0);
        if(webViewClient0 instanceof zzbdy) {
            this.zzehj = (zzbdy)webViewClient0;
        }
    }

    @Override  // android.webkit.WebView
    public final void stopLoading() {
        if(this.isDestroyed()) {
            return;
        }
        try {
            super.stopLoading();
        }
        catch(Exception exception0) {
            zzawf.zzc("Could not stop loading webview.", exception0);
        }
    }

    static int zza(zzbem zzbem0) {
        return zzbem0.zzeia;
    }

    static int zza(zzbem zzbem0, int v) {
        zzbem0.zzeia = v;
        return v;
    }

    @VisibleForTesting
    private final void zza(Boolean boolean0) {
        synchronized(this) {
            this.zzdrq = boolean0;
        }
        zzq.zzkz().zza(boolean0);
    }

    @TargetApi(19)
    private final void zza(String s, ValueCallback valueCallback0) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                this.evaluateJavascript(s, null);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    // 检测为 Lambda 实现
    static final void zza(boolean z, int v, zztv zztv0) [...]

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(ViewGroup viewGroup0, Activity activity0, String s, String s1) {
        if(!this.zzaaw()) {
            zzawf.zzee("AR ad is not enabled or the ad from the server is not an AR ad.");
            return;
        }
        if(this.getParent() instanceof ViewGroup) {
            ((ViewGroup)this.getParent()).removeView(this);
        }
        zzawf.zzee("Initializing ArWebView object.");
        this.zzehf.zza(activity0, this);
        this.zzehf.zzc(s, s1);
        if(viewGroup0 != null) {
            viewGroup0.addView(this.zzehf.getView());
            return;
        }
        zzawf.zzey("The FrameLayout object cannot be null.");
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zza(zzb zzb0) {
        this.zzehj.zza(zzb0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zze zze0) {
        synchronized(this) {
            this.zzehk = zze0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzaca zzaca0) {
        synchronized(this) {
            this.zzehx = zzaca0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzacf zzacf0) {
        synchronized(this) {
            this.zzehw = zzacf0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzbeq zzbeq0) {
        synchronized(this) {
            if(this.zzeht != null) {
                zzawf.zzey("Attempt to create multiple AdWebViewVideoControllers.");
                return;
            }
            this.zzeht = zzbeq0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzbfl zzbfl0) {
        synchronized(this) {
            this.zzehm = zzbfl0;
            this.requestLayout();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        synchronized(this) {
            this.zzehu = zzpu0.zzbnz;
        }
        this.zzbd(zzpu0.zzbnz);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzrb zzrb0) {
        synchronized(this) {
            this.zzehy = zzrb0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(String s, Predicate predicate0) {
        zzbdy zzbdy0 = this.zzehj;
        if(zzbdy0 != null) {
            zzbdy0.zza(s, predicate0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(String s, zzafz zzafz0) {
        zzbdy zzbdy0 = this.zzehj;
        if(zzbdy0 != null) {
            zzbdy0.zza(s, zzafz0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(String s, zzbda zzbda0) {
        synchronized(this) {
            if(this.zzeii == null) {
                this.zzeii = new HashMap();
            }
            this.zzeii.put(s, zzbda0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, Map map0) {
        JSONObject jSONObject0;
        try {
            jSONObject0 = zzq.zzkv().zzi(map0);
        }
        catch(JSONException unused_ex) {
            zzawf.zzfa("Could not convert parameters to JSON.");
            return;
        }
        this.zza(s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, JSONObject jSONObject0) {
        if(jSONObject0 == null) {
            jSONObject0 = new JSONObject();
        }
        String s1 = "(window.AFMA_ReceiveMessage || function() {})(\'" + s + "\'" + "," + jSONObject0.toString() + ");";
        zzawf.zzeb((s1.length() == 0 ? new String("Dispatching AFMA event: ") : "Dispatching AFMA event: " + s1));
        this.zzfp(stringBuilder0.toString());
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zza(boolean z, int v, String s) {
        this.zzehj.zza(z, v, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zza(boolean z, int v, String s, String s1) {
        this.zzehj.zza(z, v, s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final void zza(boolean z, long v) {
        HashMap hashMap0 = new HashMap(2);
        hashMap0.put("success", (z ? "1" : "0"));
        hashMap0.put("duration", Long.toString(v));
        this.zza("onCacheAccessComplete", hashMap0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final Context zzaaa() {
        return this.zzeha.zzaaa();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zze zzaab() {
        synchronized(this) {
        }
        return this.zzehk;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zze zzaac() {
        synchronized(this) {
        }
        return this.zzeif;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzbfl zzaad() {
        synchronized(this) {
        }
        return this.zzehm;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final String zzaae() {
        synchronized(this) {
        }
        return this.zzabk;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzbfi zzaaf() {
        return this.zzehj;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final WebViewClient zzaag() {
        return this.zzehj;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaah() {
        synchronized(this) {
        }
        return this.zzdiv;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzdq zzaai() {
        return this.zzehb;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final IObjectWrapper zzaaj() {
        synchronized(this) {
        }
        return this.zzehl;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaak() {
        synchronized(this) {
        }
        return this.zzeho;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaal() {
        synchronized(this) {
            zzawf.zzee("Destroying WebView!");
            this.zzvi();
            zzben zzben0 = new zzben(this);
            zzawo.zzdtx.post(zzben0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaam() {
        synchronized(this) {
        }
        return this.zzehr;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaan() {
        synchronized(this) {
        }
        return this.zzehz > 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaao() {
        this.zzeih.zzxl();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaap() {
        if(this.zzeic == null) {
            this.zzeic = zzaaf.zzb(this.zzeid.zzqw());
            this.zzeid.zza("native:view_load", this.zzeic);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzacf zzaaq() {
        synchronized(this) {
        }
        return this.zzehw;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaar() {
        this.setBackgroundColor(0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaas() {
        zzawf.zzee("Cannot add text view to inner AdWebView");
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzrb zzaat() {
        synchronized(this) {
        }
        return this.zzehy;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaau() {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzrp zzaav() {
        return this.zzehf;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaaw() {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcrj)).booleanValue() && this.zzehf != null && this.zzehg;
    }

    private final boolean zzabl() {
        int v4;
        int v3;
        boolean z = false;
        if(!this.zzehj.zzaay() && !this.zzehj.zzaaz()) {
            return false;
        }
        int v = zzayx.zzb(this.zzwi, this.zzwi.widthPixels);
        int v1 = zzayx.zzb(this.zzwi, this.zzwi.heightPixels);
        Activity activity0 = this.zzeha.zzys();
        if(activity0 == null || activity0.getWindow() == null) {
            v4 = v;
            v3 = v1;
        }
        else {
            int[] arr_v = zzawo.zzd(activity0);
            int v2 = zzayx.zzb(this.zzwi, arr_v[0]);
            v3 = zzayx.zzb(this.zzwi, arr_v[1]);
            v4 = v2;
        }
        if(this.zzdhj == v && this.zzdhk == v1 && this.maxWidth == v4 && this.maxHeight == v3) {
            return false;
        }
        if(this.zzdhj != v || this.zzdhk != v1) {
            z = true;
        }
        this.zzdhj = v;
        this.zzdhk = v1;
        this.maxWidth = v4;
        this.maxHeight = v3;
        new zzaow(this).zza(v, v1, v4, v3, this.zzwi.density, this.zzbnu.getDefaultDisplay().getRotation());
        return z;
    }

    private final void zzabm() {
        synchronized(this) {
            this.zzdrq = zzq.zzkz().zzvf();
            if(this.zzdrq == null) {
                try {
                    this.evaluateJavascript("(function(){})()", null);
                    this.zza(Boolean.TRUE);
                }
                catch(IllegalStateException unused_ex) {
                    this.zza(Boolean.FALSE);
                }
            }
        }
    }

    private final void zzabn() {
        zzaaf.zza(this.zzeid.zzqw(), this.zzebu, new String[]{"aeh2"});
    }

    private final void zzabo() {
        synchronized(this) {
            if(!this.zzeho && !this.zzehm.zzaby()) {
                if(Build.VERSION.SDK_INT < 18) {
                    zzawf.zzeb("Disabling hardware acceleration on an AdView.");
                    this.zzabp();
                    return;
                }
                zzawf.zzeb("Enabling hardware acceleration on an AdView.");
                this.zzabq();
                return;
            }
            zzawf.zzeb("Enabling hardware acceleration on an overlay.");
            this.zzabq();
        }
    }

    private final void zzabp() {
        synchronized(this) {
            if(!this.zzehp) {
                this.setLayerType(1, null);
            }
            this.zzehp = true;
        }
    }

    private final void zzabq() {
        synchronized(this) {
            if(this.zzehp) {
                this.setLayerType(0, null);
            }
            this.zzehp = false;
        }
    }

    private final void zzabr() {
        synchronized(this) {
            if(this.zzeii != null) {
                for(Object object0: this.zzeii.values()) {
                    ((zzbda)object0).release();
                }
            }
            this.zzeii = null;
        }
    }

    private final void zzabs() {
        zzaal zzaal0 = this.zzeid;
        if(zzaal0 == null) {
            return;
        }
        zzaak zzaak0 = zzaal0.zzqw();
        if(zzaak0 != null && zzq.zzkz().zzve() != null) {
            zzq.zzkz().zzve().zza(zzaak0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzal(boolean z) {
        synchronized(this) {
            if(this.zzehk != null) {
                this.zzehk.zza(this.zzehj.zzaay(), z);
                return;
            }
            this.zzdiv = z;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzap(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            this.zzehl = iObjectWrapper0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final void zzav(boolean z) {
        this.zzehj.zzav(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzax(boolean z) {
        synchronized(this) {
            boolean z1 = z != this.zzeho;
            this.zzeho = z;
            this.zzabo();
            if(z1 && (!((Boolean)zzvh.zzpd().zzd(zzzx.zzcii)).booleanValue() || !this.zzehm.zzaby())) {
                new zzaow(this).zzdv((z ? "expanded" : "default"));
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzay(boolean z) {
        synchronized(this) {
            this.zzehr = z;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaz(boolean z) {
        synchronized(this) {
            this.zzehz += (z ? 1 : -1);
            if(this.zzehz <= 0 && this.zzehk != null) {
                this.zzehk.zzty();
            }
        }
    }

    static zzbem zzb(Context context0, zzbfl zzbfl0, String s, boolean z, boolean z1, @Nullable zzdq zzdq0, zzazo zzazo0, zzaak zzaak0, zzi zzi0, zza zza0, zzsn zzsn0, zzrp zzrp0, boolean z2) {
        return new zzbem(new zzbfm(context0), zzbfl0, s, z, z1, zzdq0, zzazo0, zzaak0, zzi0, zza0, zzsn0, zzrp0, z2);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzb(zze zze0) {
        synchronized(this) {
            this.zzeif = zze0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzb(String s, zzafz zzafz0) {
        zzbdy zzbdy0 = this.zzehj;
        if(zzbdy0 != null) {
            zzbdy0.zzb(s, zzafz0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzb(String s, String s1, @Nullable String s2) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, zzbfb.zzf(s1, new String[]{zzbfb.zzabu()}), "text/html", "UTF-8", s2);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzajb
    public final void zzb(String s, JSONObject jSONObject0) {
        if(jSONObject0 == null) {
            jSONObject0 = new JSONObject();
        }
        this.zzfp(s + "(" + jSONObject0.toString() + ");");
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzb(boolean z, int v) {
        this.destroy();
        zzbel zzbel0 = (zztv zztv0) -> {
            com.google.android.gms.internal.ads.zzsz.zzw.zza zzsz$zzw$zza0 = zzw.zzol();
            if(zzsz$zzw$zza0.zzok() != z) {
                zzsz$zzw$zza0.zzw(z);
            }
            zztv0.zzcbo = (zzw)(((zzdyz)zzsz$zzw$zza0.zzci(v).zzbcx()));
        };
        this.zzefm.zza(zzbel0);
        this.zzefm.zza(com.google.android.gms.internal.ads.zzsp.zza.zza.zzbuh);
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzba(boolean z) {
        this.zzehj.zzba(z);
    }

    private final void zzbd(boolean z) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("isVisible", (z ? "1" : "0"));
        this.zza("onAdVisibilityChanged", hashMap0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzbu(Context context0) {
        this.zzeha.setBaseContext(context0);
        this.zzeih.zzh(this.zzeha.zzys());
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zzc(boolean z, int v) {
        this.zzehj.zzc(z, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzajb
    public final void zzcz(String s) {
        this.zzfp(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzde(int v) {
        if(v == 0) {
            zzaaf.zza(this.zzeid.zzqw(), this.zzebu, new String[]{"aebb2"});
        }
        this.zzabn();
        if(this.zzeid.zzqw() != null) {
            this.zzeid.zzqw().zzh("close_type", String.valueOf(v));
        }
        HashMap hashMap0 = new HashMap(2);
        hashMap0.put("closetype", String.valueOf(v));
        hashMap0.put("version", this.zzblu.zzbmj);
        this.zza("onhide", hashMap0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final zzbda zzff(String s) {
        synchronized(this) {
            return this.zzeii == null ? null : ((zzbda)this.zzeii.get(s));
        }
    }

    private final void zzfn(String s) {
        synchronized(this) {
            if(!this.isDestroyed()) {
                this.loadUrl(s);
                return;
            }
            zzawf.zzfa("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final void zzfo(String s) {
        synchronized(this) {
            try {
                super.loadUrl(s);
            }
            catch(Exception | NoClassDefFoundError | IncompatibleClassChangeError | UnsatisfiedLinkError exception0) {
                zzq.zzkz().zza(exception0, "AdWebViewImpl.loadUrlUnsafe");
                zzawf.zzd("Could not call loadUrl. ", exception0);
            }
        }
    }

    private final void zzfp(String s) {
        if(this.zzvf() == null) {
            this.zzabm();
        }
        if(this.zzvf().booleanValue()) {
            this.zza(s, null);
            return;
        }
        String s1 = String.valueOf(s);
        this.zzfn((s1.length() == 0 ? new String("javascript:") : "javascript:" + s1));
    }

    @Override  // com.google.android.gms.ads.internal.zzi
    public final void zzka() {
        synchronized(this) {
            this.zzehs = true;
            if(this.zzehc != null) {
                this.zzehc.zzka();
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.zzi
    public final void zzkb() {
        synchronized(this) {
            this.zzehs = false;
            if(this.zzehc != null) {
                this.zzehc.zzkb();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zztw() {
        if(this.zzeib == null) {
            zzaaf.zza(this.zzeid.zzqw(), this.zzebu, new String[]{"aes2"});
            this.zzeib = zzaaf.zzb(this.zzeid.zzqw());
            this.zzeid.zza("native:view_show", this.zzeib);
        }
        HashMap hashMap0 = new HashMap(1);
        hashMap0.put("version", this.zzblu.zzbmj);
        this.zza("onshow", hashMap0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final void zztx() {
        zze zze0 = this.zzaab();
        if(zze0 != null) {
            zze0.zztx();
        }
    }

    @VisibleForTesting
    private final Boolean zzvf() {
        synchronized(this) {
        }
        return this.zzdrq;
    }

    private final void zzvi() {
        synchronized(this) {
            if(!this.zzeig) {
                this.zzeig = true;
                zzq.zzkz().zzvi();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final zzbbb zzyp() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzbeq zzyq() {
        synchronized(this) {
        }
        return this.zzeht;
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final zzaai zzyr() {
        return this.zzebu;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final Activity zzys() {
        return this.zzeha.zzys();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zza zzyt() {
        return this.zzehd;
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final String zzyu() {
        synchronized(this) {
        }
        return this.zzdkd;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzaal zzyv() {
        return this.zzeid;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzazo zzyw() {
        return this.zzblu;
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final int zzyx() {
        return this.getMeasuredHeight();
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final int zzyy() {
        return this.getMeasuredWidth();
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final void zzyz() {
        synchronized(this) {
            if(this.zzehx != null) {
                this.zzehx.zzrf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzzy() {
        this.zzabn();
        HashMap hashMap0 = new HashMap(1);
        hashMap0.put("version", this.zzblu.zzbmj);
        this.zza("onhide", hashMap0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzzz() {
        HashMap hashMap0 = new HashMap(3);
        hashMap0.put("app_muted", String.valueOf(zzq.zzla().zzpk()));
        hashMap0.put("app_volume", String.valueOf(zzq.zzla().zzpj()));
        hashMap0.put("device_volume", String.valueOf(zzaxd.zzbh(this.getContext())));
        this.zza("volume", hashMap0);
    }
}

