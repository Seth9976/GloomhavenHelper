package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.zzc;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
@ParametersAreNonnullByDefault
public final class zzbfo extends zzbfx implements zzbfi {
    private final Object lock;
    private volatile boolean zzbmk;
    private zztz zzcch;
    private zzafe zzcxu;
    private zzafg zzcxv;
    private zzc zzcyp;
    private zzaom zzcyq;
    private zzp zzdit;
    private zzv zzdix;
    private boolean zzdmq;
    protected zzbdv zzefl;
    private zzbfh zzefo;
    private zzbfk zzefp;
    private zzbfj zzefq;
    private boolean zzefr;
    @GuardedBy("lock")
    private boolean zzefs;
    @GuardedBy("lock")
    private boolean zzeft;
    @GuardedBy("lock")
    private boolean zzefu;
    private zzaox zzefv;
    @Nullable
    private zzaub zzefw;
    private boolean zzefx;
    private boolean zzefy;
    private int zzefz;
    private View.OnAttachStateChangeListener zzega;
    private final zzaim zzejb;

    public zzbfo() {
        this.lock = new Object();
        this.zzefr = false;
        this.zzejb = new zzaim();
    }

    public final void destroy() {
        zzaub zzaub0 = this.zzefw;
        if(zzaub0 != null) {
            zzaub0.zzus();
            this.zzefw = null;
        }
        this.zzabd();
        this.zzejb.reset();
        this.zzejb.zzg(null);
        synchronized(this.lock) {
            this.zzcch = null;
            this.zzdit = null;
            this.zzefo = null;
            this.zzefp = null;
            this.zzcxu = null;
            this.zzcxv = null;
            this.zzdix = null;
            this.zzefq = null;
            if(this.zzcyq != null) {
                this.zzcyq.zzac(true);
                this.zzcyq = null;
            }
        }
    }

    @Override  // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView0, String s, Bitmap bitmap0) {
        zzrp zzrp0 = this.zzefl.zzaav();
        if(zzrp0 != null && webView0 == zzrp0.getWebView()) {
            zzrp0.onPageStarted(webView0, s, bitmap0);
        }
        super.onPageStarted(webView0, s, bitmap0);
    }

    @Override  // android.webkit.WebViewClient
    @TargetApi(26)
    public final boolean onRenderProcessGone(WebView webView0, RenderProcessGoneDetail renderProcessGoneDetail0) {
        return this.zzefl.zzb(renderProcessGoneDetail0.didCrash(), renderProcessGoneDetail0.rendererPriorityAtExit());
    }

    private final void zza(View view0, zzaub zzaub0, int v) {
        if(zzaub0.zzuq() && v > 0) {
            zzaub0.zzj(view0);
            if(zzaub0.zzuq()) {
                zzbfq zzbfq0 = new zzbfq(this, view0, zzaub0, v);
                zzawo.zzdtx.postDelayed(zzbfq0, 100L);
            }
        }
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel0) {
        boolean z = false;
        boolean z1 = this.zzcyq == null ? false : this.zzcyq.zztl();
        Context context0 = this.zzefl.getContext();
        if(!z1) {
            z = true;
        }
        zzl.zza(context0, adOverlayInfoParcel0, z);
        if(this.zzefw != null) {
            this.zzefw.zzdw((adOverlayInfoParcel0.url != null || adOverlayInfoParcel0.zzdis == null ? adOverlayInfoParcel0.url : adOverlayInfoParcel0.zzdis.url));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zza(int v, int v1, boolean z) {
        this.zzefv.zzj(v, v1);
        zzaom zzaom0 = this.zzcyq;
        if(zzaom0 != null) {
            zzaom0.zza(v, v1, false);
        }
    }

    public final void zza(zzb zzb0) {
        boolean z = this.zzefl.zzaak();
        this.zza(new AdOverlayInfoParcel(zzb0, (!z || this.zzefl.zzaad().zzaby() ? this.zzcch : null), (z ? null : this.zzdit), this.zzdix, this.zzefl.zzyw()));
    }

    final void zza(zzbdv zzbdv0, boolean z) {
        zzaox zzaox0 = new zzaox(zzbdv0, zzbdv0.zzaaa(), new zzze(zzbdv0.getContext()));
        this.zzefl = zzbdv0;
        this.zzbmk = z;
        this.zzefv = zzaox0;
        this.zzcyq = null;
        this.zzejb.zzg(zzbdv0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfh zzbfh0) {
        this.zzefo = zzbfh0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfk zzbfk0) {
        this.zzefp = zzbfk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfx
    public final void zza(zzbga zzbga0) {
        this.zzefx = true;
        zzbfk zzbfk0 = this.zzefp;
        if(zzbfk0 != null) {
            zzbfk0.zzsg();
            this.zzefp = null;
        }
        this.zzabi();
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zza(zztz zztz0, zzafe zzafe0, zzp zzp0, zzafg zzafg0, zzv zzv0, boolean z, @Nullable zzafy zzafy0, zzc zzc0, zzaoz zzaoz0, @Nullable zzaub zzaub0) {
        if(zzc0 == null) {
            zzc0 = new zzc(this.zzefl.getContext(), zzaub0, null);
        }
        this.zzcyq = new zzaom(this.zzefl, zzaoz0);
        this.zzefw = zzaub0;
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjs)).booleanValue()) {
            this.zza("/adMetadata", new zzaff(zzafe0));
        }
        this.zza("/appEvent", new zzafh(zzafg0));
        this.zza("/backButton", zzafi.zzcyf);
        this.zza("/refresh", zzafi.zzcyg);
        this.zza("/canOpenURLs", zzafi.zzcxw);
        this.zza("/canOpenIntents", zzafi.zzcxx);
        this.zza("/click", zzafi.zzcxy);
        this.zza("/close", zzafi.zzcxz);
        this.zza("/customClose", zzafi.zzcya);
        this.zza("/instrument", zzafi.zzcyj);
        this.zza("/delayPageLoaded", zzafi.zzcyl);
        this.zza("/delayPageClosed", zzafi.zzcym);
        this.zza("/getLocationInfo", zzafi.zzcyn);
        this.zza("/httpTrack", zzafi.zzcyb);
        this.zza("/log", zzafi.zzcyc);
        this.zza("/mraid", new zzaga(zzc0, this.zzcyq, zzaoz0));
        this.zza("/mraidLoaded", this.zzefv);
        this.zza("/open", new zzagd(zzc0, this.zzcyq));
        this.zza("/precache", new zzbdf());
        this.zza("/touch", zzafi.zzcye);
        this.zza("/video", zzafi.zzcyh);
        this.zza("/videoMeta", zzafi.zzcyi);
        if(zzq.zzlt().zzad(this.zzefl.getContext())) {
            this.zza("/logScionEvent", new zzagb(this.zzefl.getContext()));
        }
        this.zzcch = zztz0;
        this.zzdit = zzp0;
        this.zzcxu = zzafe0;
        this.zzcxv = zzafg0;
        this.zzdix = zzv0;
        this.zzcyp = zzc0;
        this.zzefr = z;
    }

    public final void zza(String s, Predicate predicate0) {
        this.zzejb.zza(s, predicate0);
    }

    public final void zza(String s, zzafz zzafz0) {
        this.zzejb.zza(s, zzafz0);
    }

    public final void zza(boolean z, int v, String s) {
        boolean z1 = this.zzefl.zzaak();
        this.zza(new AdOverlayInfoParcel((!z1 || this.zzefl.zzaad().zzaby() ? this.zzcch : null), (z1 ? null : new zzbfs(this.zzefl, this.zzdit)), this.zzcxu, this.zzcxv, this.zzdix, this.zzefl, z, v, s, this.zzefl.zzyw()));
    }

    public final void zza(boolean z, int v, String s, String s1) {
        boolean z1 = this.zzefl.zzaak();
        this.zza(new AdOverlayInfoParcel((!z1 || this.zzefl.zzaad().zzaby() ? this.zzcch : null), (z1 ? null : new zzbfs(this.zzefl, this.zzdit)), this.zzcxu, this.zzcxv, this.zzdix, this.zzefl, z, v, s, s1, this.zzefl.zzyw()));
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final zzc zzaax() {
        return this.zzcyp;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final boolean zzaay() {
        return this.zzbmk;
    }

    public final boolean zzaaz() {
        synchronized(this.lock) {
        }
        return this.zzefs;
    }

    public final boolean zzaba() {
        synchronized(this.lock) {
        }
        return this.zzeft;
    }

    public final ViewTreeObserver.OnGlobalLayoutListener zzabb() {
        synchronized(this.lock) {
        }
        return null;
    }

    public final ViewTreeObserver.OnScrollChangedListener zzabc() {
        synchronized(this.lock) {
        }
        return null;
    }

    private final void zzabd() {
        if(this.zzega == null) {
            return;
        }
        this.zzefl.getView().removeOnAttachStateChangeListener(this.zzega);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzabe() {
        zzaub zzaub0 = this.zzefw;
        if(zzaub0 != null) {
            WebView webView0 = this.zzefl.getWebView();
            if(ViewCompat.isAttachedToWindow(webView0)) {
                this.zza(webView0, zzaub0, 10);
                return;
            }
            this.zzabd();
            this.zzega = new zzbfp(this, zzaub0);
            this.zzefl.getView().addOnAttachStateChangeListener(this.zzega);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzabf() {
        synchronized(this.lock) {
            this.zzefu = true;
        }
        ++this.zzefz;
        this.zzabi();
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzabg() {
        --this.zzefz;
        this.zzabi();
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzabh() {
        this.zzefy = true;
        this.zzabi();
    }

    private final void zzabi() {
        if(this.zzefo != null && (this.zzefx && this.zzefz <= 0 || this.zzefy)) {
            this.zzefo.zzai(!this.zzefy);
            this.zzefo = null;
        }
        this.zzefl.zzaap();
    }

    // 去混淆评级： 低(20)
    private static WebResourceResponse zzabj() {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcji)).booleanValue() ? new WebResourceResponse("", "", new ByteArrayInputStream(new byte[0])) : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final zzaub zzabk() {
        return this.zzefw;
    }

    public final void zzav(boolean z) {
        this.zzefr = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfx
    public final void zzb(zzbga zzbga0) {
        this.zzejb.zzg(zzbga0.uri);
    }

    public final void zzb(String s, zzafz zzafz0) {
        this.zzejb.zzb(s, zzafz0);
    }

    public final void zzba(boolean z) {
        this.zzdmq = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzbb(boolean z) {
        synchronized(this.lock) {
            this.zzefs = true;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzbc(boolean z) {
        synchronized(this.lock) {
            this.zzeft = z;
        }
    }

    public final void zzc(boolean z, int v) {
        this.zza(new AdOverlayInfoParcel((!this.zzefl.zzaak() || this.zzefl.zzaad().zzaby() ? this.zzcch : null), this.zzdit, this.zzdix, this.zzefl, z, v, this.zzefl.zzyw()));
    }

    @Override  // com.google.android.gms.internal.ads.zzbfx
    public final boolean zzc(zzbga zzbga0) {
        String s = String.valueOf(zzbga0.url);
        zzawf.zzee((s.length() == 0 ? new String("AdWebView shouldOverrideUrlLoading: ") : "AdWebView shouldOverrideUrlLoading: " + s));
        Uri uri0 = zzbga0.uri;
        if(this.zzejb.zzg(uri0)) {
            return true;
        }
        if(this.zzefr) {
            String s1 = uri0.getScheme();
            if("http".equalsIgnoreCase(s1) || "https".equalsIgnoreCase(s1)) {
                zztz zztz0 = this.zzcch;
                if(zztz0 != null) {
                    zztz0.onAdClicked();
                    zzaub zzaub0 = this.zzefw;
                    if(zzaub0 != null) {
                        zzaub0.zzdw(zzbga0.url);
                    }
                    this.zzcch = null;
                }
                return false;
            }
        }
        if(!this.zzefl.getWebView().willNotDraw()) {
            try {
                zzdq zzdq0 = this.zzefl.zzaai();
                if(zzdq0 != null && zzdq0.zzb(uri0)) {
                    uri0 = zzdq0.zza(uri0, this.zzefl.getContext(), this.zzefl.getView(), this.zzefl.zzys());
                }
            }
            catch(zzdt unused_ex) {
                String s2 = String.valueOf(zzbga0.url);
                zzawf.zzfa((s2.length() == 0 ? new String("Unable to append parameter to URL: ") : "Unable to append parameter to URL: " + s2));
            }
            if(this.zzcyp != null && !this.zzcyp.zzjv()) {
                this.zzcyp.zzbr(zzbga0.url);
                return true;
            }
            this.zza(new zzb("android.intent.action.VIEW", uri0.toString(), null, null, null, null, null));
            return true;
        }
        String s3 = String.valueOf(zzbga0.url);
        zzawf.zzfa((s3.length() == 0 ? new String("AdWebView unable to handle URL: ") : "AdWebView unable to handle URL: " + s3));
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfx
    @Nullable
    public final WebResourceResponse zzd(zzbga zzbga0) {
        WebResourceResponse webResourceResponse0;
        String s;
        zzaub zzaub0 = this.zzefw;
        if(zzaub0 != null) {
            zzaub0.zza(zzbga0.url, zzbga0.zzab, 1);
        }
        if("mraid.js".equalsIgnoreCase(new File(zzbga0.url).getName())) {
            this.zzts();
            if(this.zzefl.zzaad().zzaby()) {
                s = (String)zzvh.zzpd().zzd(zzzx.zzcig);
            }
            else {
                s = this.zzefl.zzaak() ? ((String)zzvh.zzpd().zzd(zzzx.zzcif)) : ((String)zzvh.zzpd().zzd(zzzx.zzcie));
            }
            webResourceResponse0 = zzawo.zzd(this.zzefl.getContext(), this.zzefl.zzyw().zzbmj, s);
        }
        else {
            webResourceResponse0 = null;
        }
        if(webResourceResponse0 != null) {
            return webResourceResponse0;
        }
        try {
            Context context0 = this.zzefl.getContext();
            if(!zzaux.zzb(zzbga0.url, context0, this.zzdmq).equals(zzbga0.url)) {
                return this.zze(zzbga0);
            }
            zzrz zzrz0 = zzrz.zzbz(zzbga0.url);
            if(zzrz0 != null) {
                zzry zzry0 = zzq.zzlb().zza(zzrz0);
                if(zzry0 != null && zzry0.zzmu()) {
                    return new WebResourceResponse("", "", zzry0.zzmv());
                }
            }
            return !zzazb.isEnabled() || !((Boolean)zzabh.zzcuv.get()).booleanValue() ? null : this.zze(zzbga0);
        }
        catch(Exception | NoClassDefFoundError exception0) {
            zzq.zzkz().zza(exception0, "AdWebViewClient.interceptRequest");
            return zzbfo.zzabj();
        }
    }

    private final WebResourceResponse zze(zzbga zzbga0) throws IOException {
        URLConnection uRLConnection0;
        URL uRL0 = new URL(zzbga0.url);
        int v = 0;
        while(true) {
            ++v;
            if(v > 20) {
                throw new IOException("Too many redirects (20)");
            }
            uRLConnection0 = uRL0.openConnection();
            uRLConnection0.setConnectTimeout(10000);
            uRLConnection0.setReadTimeout(10000);
            for(Object object0: zzbga0.zzab.entrySet()) {
                uRLConnection0.addRequestProperty(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
            }
            if(!(uRLConnection0 instanceof HttpURLConnection)) {
                throw new IOException("Invalid protocol.");
            }
            zzq.zzkv().zza(this.zzefl.getContext(), this.zzefl.zzyw().zzbmj, false, ((HttpURLConnection)uRLConnection0));
            zzazb zzazb0 = new zzazb();
            zzazb0.zza(((HttpURLConnection)uRLConnection0), null);
            int v1 = ((HttpURLConnection)uRLConnection0).getResponseCode();
            zzazb0.zza(((HttpURLConnection)uRLConnection0), v1);
            if(v1 >= 300 && v1 < 400) {
                String s = ((HttpURLConnection)uRLConnection0).getHeaderField("Location");
                if(s == null) {
                    break;
                }
                if(s.startsWith("tel:")) {
                    return null;
                }
                URL uRL1 = new URL(uRL0, s);
                String s1 = uRL1.getProtocol();
                if(s1 == null) {
                    zzawf.zzfa("Protocol is null");
                    return zzbfo.zzabj();
                }
                if(!s1.equals("http") && !s1.equals("https")) {
                    String s2 = String.valueOf(s1);
                    zzawf.zzfa((s2.length() == 0 ? new String("Unsupported scheme: ") : "Unsupported scheme: " + s2));
                    return zzbfo.zzabj();
                }
                String s3 = String.valueOf(s);
                zzawf.zzeb((s3.length() == 0 ? new String("Redirecting to ") : "Redirecting to " + s3));
                ((HttpURLConnection)uRLConnection0).disconnect();
                uRL0 = uRL1;
                continue;
            }
            return zzawo.zzd(((HttpURLConnection)uRLConnection0));
        }
        throw new IOException("Missing Location header in redirect");
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzh(Uri uri0) {
        this.zzejb.zzh(uri0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzi(int v, int v1) {
        zzaom zzaom0 = this.zzcyq;
        if(zzaom0 != null) {
            zzaom0.zzi(v, v1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zzts() {
        synchronized(this.lock) {
            this.zzefr = false;
            this.zzbmk = true;
            zzbfn zzbfn0 = new zzbfn(this);
            zzazq.zzdxo.execute(zzbfn0);
        }
    }
}

