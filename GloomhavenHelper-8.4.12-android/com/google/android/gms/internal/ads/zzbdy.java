package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
@ParametersAreNonnullByDefault
public class zzbdy extends WebViewClient implements zzbfi {
    private final Object lock;
    @GuardedBy("lock")
    private boolean zzbmk;
    private zztz zzcch;
    private zzafe zzcxu;
    private zzafg zzcxv;
    private zzc zzcyp;
    private zzaom zzcyq;
    private zzp zzdit;
    private zzv zzdix;
    private boolean zzdmq;
    protected zzbdv zzefl;
    @Nullable
    private final zzsn zzefm;
    private final HashMap zzefn;
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
    private final zzaox zzefv;
    @Nullable
    protected zzaub zzefw;
    private boolean zzefx;
    private boolean zzefy;
    private int zzefz;
    private View.OnAttachStateChangeListener zzega;

    public zzbdy(zzbdv zzbdv0, zzsn zzsn0, boolean z) {
        this(zzbdv0, zzsn0, z, new zzaox(zzbdv0, zzbdv0.zzaaa(), new zzze(zzbdv0.getContext())), null);
    }

    @VisibleForTesting
    private zzbdy(zzbdv zzbdv0, zzsn zzsn0, boolean z, zzaox zzaox0, zzaom zzaom0) {
        this.zzefn = new HashMap();
        this.lock = new Object();
        this.zzefr = false;
        this.zzefm = zzsn0;
        this.zzefl = zzbdv0;
        this.zzbmk = z;
        this.zzefv = zzaox0;
        this.zzcyq = null;
    }

    @Override  // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView0, String s) {
        String s1 = String.valueOf(s);
        zzawf.zzee((s1.length() == 0 ? new String("Loading resource: ") : "Loading resource: " + s1));
        Uri uri0 = Uri.parse(s);
        if("gmsg".equalsIgnoreCase(uri0.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri0.getHost())) {
            this.zzh(uri0);
        }
    }

    @Override  // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView0, String s) {
        synchronized(this.lock) {
            if(this.zzefl.isDestroyed()) {
                zzawf.zzee("Blank page loaded, 1...");
                this.zzefl.zzaal();
                return;
            }
        }
        this.zzefx = true;
        zzbfk zzbfk0 = this.zzefp;
        if(zzbfk0 != null) {
            zzbfk0.zzsg();
            this.zzefp = null;
        }
        this.zzabi();
    }

    @Override  // android.webkit.WebViewClient
    public void onPageStarted(WebView webView0, String s, Bitmap bitmap0) {
        zzrp zzrp0 = this.zzefl.zzaav();
        if(zzrp0 != null && webView0 == zzrp0.getWebView()) {
            zzrp0.onPageStarted(webView0, s, bitmap0);
        }
        super.onPageStarted(webView0, s, bitmap0);
    }

    @Override  // android.webkit.WebViewClient
    @TargetApi(26)
    public boolean onRenderProcessGone(WebView webView0, RenderProcessGoneDetail renderProcessGoneDetail0) {
        return this.zzefl.zzb(renderProcessGoneDetail0.didCrash(), renderProcessGoneDetail0.rendererPriorityAtExit());
    }

    public final void reset() {
        zzaub zzaub0 = this.zzefw;
        if(zzaub0 != null) {
            zzaub0.zzus();
            this.zzefw = null;
        }
        this.zzabd();
        synchronized(this.lock) {
            this.zzefn.clear();
            this.zzcch = null;
            this.zzdit = null;
            this.zzefo = null;
            this.zzefp = null;
            this.zzcxu = null;
            this.zzcxv = null;
            this.zzefr = false;
            this.zzbmk = false;
            this.zzefs = false;
            this.zzefu = false;
            this.zzdix = null;
            this.zzefq = null;
            if(this.zzcyq != null) {
                this.zzcyq.zzac(true);
                this.zzcyq = null;
            }
        }
    }

    @Override  // android.webkit.WebViewClient
    @TargetApi(11)
    @Nullable
    public WebResourceResponse shouldInterceptRequest(WebView webView0, String s) {
        return this.zzd(s, Collections.emptyMap());
    }

    @Override  // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView0, KeyEvent keyEvent0) {
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
    public final boolean shouldOverrideUrlLoading(WebView webView0, String s) {
        String s1 = String.valueOf(s);
        zzawf.zzee((s1.length() == 0 ? new String("AdWebView shouldOverrideUrlLoading: ") : "AdWebView shouldOverrideUrlLoading: " + s1));
        Uri uri0 = Uri.parse(s);
        if("gmsg".equalsIgnoreCase(uri0.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri0.getHost())) {
            this.zzh(uri0);
            return true;
        }
        if(this.zzefr && webView0 == this.zzefl.getWebView()) {
            String s2 = uri0.getScheme();
            if("http".equalsIgnoreCase(s2) || "https".equalsIgnoreCase(s2)) {
                zztz zztz0 = this.zzcch;
                if(zztz0 != null) {
                    zztz0.onAdClicked();
                    zzaub zzaub0 = this.zzefw;
                    if(zzaub0 != null) {
                        zzaub0.zzdw(s);
                    }
                    this.zzcch = null;
                }
                return super.shouldOverrideUrlLoading(webView0, s);
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
                String s3 = String.valueOf(s);
                zzawf.zzfa((s3.length() == 0 ? new String("Unable to append parameter to URL: ") : "Unable to append parameter to URL: " + s3));
            }
            if(this.zzcyp != null && !this.zzcyp.zzjv()) {
                this.zzcyp.zzbr(s);
                return true;
            }
            this.zza(new zzb("android.intent.action.VIEW", uri0.toString(), null, null, null, null, null));
            return true;
        }
        String s4 = String.valueOf(s);
        zzawf.zzfa((s4.length() == 0 ? new String("AdWebView unable to handle URL: ") : "AdWebView unable to handle URL: " + s4));
        return true;
    }

    private final void zza(View view0, zzaub zzaub0, int v) {
        if(zzaub0.zzuq() && v > 0) {
            zzaub0.zzj(view0);
            if(zzaub0.zzuq()) {
                zzbdz zzbdz0 = new zzbdz(this, view0, zzaub0, v);
                zzawo.zzdtx.postDelayed(zzbdz0, 100L);
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

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfh zzbfh0) {
        this.zzefo = zzbfh0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfk zzbfk0) {
        this.zzefp = zzbfk0;
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
        synchronized(this.lock) {
            List list0 = (List)this.zzefn.get(s);
            if(list0 == null) {
                return;
            }
            ArrayList arrayList0 = new ArrayList();
            for(Object object1: list0) {
                zzafz zzafz0 = (zzafz)object1;
                if(predicate0.apply(zzafz0)) {
                    arrayList0.add(zzafz0);
                }
            }
            list0.removeAll(arrayList0);
        }
    }

    public final void zza(String s, zzafz zzafz0) {
        synchronized(this.lock) {
            List list0 = (List)this.zzefn.get(s);
            if(list0 == null) {
                list0 = new CopyOnWriteArrayList();
                this.zzefn.put(s, list0);
            }
            list0.add(zzafz0);
        }
    }

    public final void zza(boolean z, int v, String s) {
        boolean z1 = this.zzefl.zzaak();
        this.zza(new AdOverlayInfoParcel((!z1 || this.zzefl.zzaad().zzaby() ? this.zzcch : null), (z1 ? null : new zzbeb(this.zzefl, this.zzdit)), this.zzcxu, this.zzcxv, this.zzdix, this.zzefl, z, v, s, this.zzefl.zzyw()));
    }

    public final void zza(boolean z, int v, String s, String s1) {
        boolean z1 = this.zzefl.zzaak();
        this.zza(new AdOverlayInfoParcel((!z1 || this.zzefl.zzaad().zzaby() ? this.zzcch : null), (z1 ? null : new zzbeb(this.zzefl, this.zzdit)), this.zzcxu, this.zzcxv, this.zzdix, this.zzefl, z, v, s, s1, this.zzefl.zzyw()));
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final zzc zzaax() {
        return this.zzcyp;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfi
    public final boolean zzaay() {
        synchronized(this.lock) {
        }
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
            this.zzega = new zzbec(this, zzaub0);
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
        zzsn zzsn0 = this.zzefm;
        if(zzsn0 != null) {
            zzsn0.zza(zza.zzbuj);
        }
        this.zzefy = true;
        this.zzabi();
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcqb)).booleanValue()) {
            this.zzefl.destroy();
        }
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

    public final void zzb(String s, zzafz zzafz0) {
        synchronized(this.lock) {
            List list0 = (List)this.zzefn.get(s);
            if(list0 == null) {
                return;
            }
            list0.remove(zzafz0);
        }
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

    @Nullable
    protected final WebResourceResponse zzd(String s, Map map0) {
        try {
            String s1 = zzaux.zzb(s, this.zzefl.getContext(), this.zzdmq);
            if(!s1.equals(s)) {
                return this.zze(s1, map0);
            }
            zzrz zzrz0 = zzrz.zzbz(s);
            if(zzrz0 != null) {
                zzry zzry0 = zzq.zzlb().zza(zzrz0);
                if(zzry0 != null && zzry0.zzmu()) {
                    return new WebResourceResponse("", "", zzry0.zzmv());
                }
            }
            return !zzazb.isEnabled() || !((Boolean)zzabh.zzcuv.get()).booleanValue() ? null : this.zze(s, map0);
        }
        catch(Exception | NoClassDefFoundError exception0) {
            zzq.zzkz().zza(exception0, "AdWebViewClient.interceptRequest");
            return zzbdy.zzabj();
        }
    }

    private final WebResourceResponse zze(String s, Map map0) throws IOException {
        URLConnection uRLConnection0;
        URL uRL0 = new URL(s);
        int v = 0;
        while(true) {
            ++v;
            if(v > 20) {
                throw new IOException("Too many redirects (20)");
            }
            uRLConnection0 = uRL0.openConnection();
            uRLConnection0.setConnectTimeout(10000);
            uRLConnection0.setReadTimeout(10000);
            for(Object object0: map0.entrySet()) {
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
                String s1 = ((HttpURLConnection)uRLConnection0).getHeaderField("Location");
                if(s1 == null) {
                    break;
                }
                if(s1.startsWith("tel:")) {
                    return null;
                }
                URL uRL1 = new URL(uRL0, s1);
                String s2 = uRL1.getProtocol();
                if(s2 == null) {
                    zzawf.zzfa("Protocol is null");
                    return zzbdy.zzabj();
                }
                if(!s2.equals("http") && !s2.equals("https")) {
                    String s3 = String.valueOf(s2);
                    zzawf.zzfa((s3.length() == 0 ? new String("Unsupported scheme: ") : "Unsupported scheme: " + s3));
                    return zzbdy.zzabj();
                }
                String s4 = String.valueOf(s1);
                zzawf.zzeb((s4.length() == 0 ? new String("Redirecting to ") : "Redirecting to " + s4));
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
        String s = uri0.getPath();
        List list0 = (List)this.zzefn.get(s);
        if(list0 != null) {
            Map map0 = zzawo.zzi(uri0);
            if(zzawf.isLoggable(2)) {
                String s1 = String.valueOf(s);
                zzawf.zzee((s1.length() == 0 ? new String("Received GMSG: ") : "Received GMSG: " + s1));
                for(Object object0: map0.keySet()) {
                    zzawf.zzee(("  " + ((String)object0) + ": " + ((String)map0.get(((String)object0)))));
                }
            }
            for(Object object1: list0) {
                ((zzafz)object1).zza(this.zzefl, map0);
            }
            return;
        }
        zzawf.zzee(("No GMSG handler found for GMSG: " + uri0));
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcse)).booleanValue() && zzq.zzkz().zzve() != null) {
            zzbea zzbea0 = new zzbea(s);
            zzazq.zzdxk.execute(zzbea0);
        }
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
            zzbdx zzbdx0 = new zzbdx(this);
            zzazq.zzdxo.execute(zzbdx0);
        }
    }
}

