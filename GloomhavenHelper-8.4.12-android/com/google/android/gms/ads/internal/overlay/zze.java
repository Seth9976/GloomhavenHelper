package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaow;
import com.google.android.gms.internal.ads.zzape;
import com.google.android.gms.internal.ads.zzawf;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzawu;
import com.google.android.gms.internal.ads.zzbdv;
import com.google.android.gms.internal.ads.zzbee;
import com.google.android.gms.internal.ads.zzbfi;
import com.google.android.gms.internal.ads.zzbfl;
import com.google.android.gms.internal.ads.zzsn;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzzx;
import java.util.Collections;

public class zze extends zzape implements zzw {
    @VisibleForTesting
    private boolean zzblg;
    @VisibleForTesting
    zzbdv zzdae;
    @VisibleForTesting
    private static final int zzdhu;
    @VisibleForTesting
    AdOverlayInfoParcel zzdhv;
    @VisibleForTesting
    private zzk zzdhw;
    @VisibleForTesting
    private zzo zzdhx;
    @VisibleForTesting
    private boolean zzdhy;
    @VisibleForTesting
    private FrameLayout zzdhz;
    @VisibleForTesting
    private WebChromeClient.CustomViewCallback zzdia;
    @VisibleForTesting
    private boolean zzdib;
    @VisibleForTesting
    private zzh zzdic;
    @VisibleForTesting
    private boolean zzdid;
    @VisibleForTesting
    int zzdie;
    private final Object zzdif;
    private Runnable zzdig;
    private boolean zzdih;
    private boolean zzdii;
    private boolean zzdij;
    private boolean zzdik;
    private boolean zzdil;
    protected final Activity zzzo;

    static {
        zze.zzdhu = 0;
    }

    public zze(Activity activity0) {
        this.zzdhy = false;
        this.zzdib = false;
        this.zzblg = false;
        this.zzdid = false;
        this.zzdie = 0;
        this.zzdif = new Object();
        this.zzdij = false;
        this.zzdik = false;
        this.zzdil = true;
        this.zzzo = activity0;
    }

    public final void close() {
        this.zzdie = 2;
        this.zzzo.finish();
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onActivityResult(int v, int v1, Intent intent0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onBackPressed() {
        this.zzdie = 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public void onCreate(Bundle bundle0) {
        this.zzzo.requestWindowFeature(1);
        boolean z = bundle0 != null && bundle0.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        try {
            this.zzdib = z;
            this.zzdhv = AdOverlayInfoParcel.zzc(this.zzzo.getIntent());
            if(this.zzdhv != null) {
                if(this.zzdhv.zzblu.zzdxg > 7500000) {
                    this.zzdie = 3;
                }
                if(this.zzzo.getIntent() != null) {
                    this.zzdil = this.zzzo.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
                }
                this.zzblg = this.zzdhv.zzdja == null ? false : this.zzdhv.zzdja.zzblg;
                if(this.zzblg && this.zzdhv.zzdja.zzbll != -1) {
                    new zzj(this, null).zzvw();
                }
                if(bundle0 == null) {
                    if(this.zzdhv.zzdit != null && this.zzdil) {
                        this.zzdhv.zzdit.zztk();
                    }
                    if(this.zzdhv.zzdiy != 1 && this.zzdhv.zzcch != null) {
                        this.zzdhv.zzcch.onAdClicked();
                    }
                }
                this.zzdic = new zzh(this.zzzo, this.zzdhv.zzdiz, this.zzdhv.zzblu.zzbmj);
                this.zzdic.setId(1000);
                zzq.zzkx().zzg(this.zzzo);
                switch(this.zzdhv.zzdiy) {
                    case 1: {
                        this.zzak(false);
                        return;
                    }
                    case 2: {
                        this.zzdhw = new zzk(this.zzdhv.zzdae);
                        this.zzak(false);
                        return;
                    }
                    case 3: {
                        this.zzak(true);
                        return;
                    }
                    default: {
                        throw new zzi("Could not determine ad overlay type.");
                    }
                }
            }
            throw new zzi("Could not get info for ad overlay.");
        }
        catch(zzi zzi0) {
            zzawf.zzfa(zzi0.getMessage());
            this.zzdie = 3;
            this.zzzo.finish();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onDestroy() {
        zzbdv zzbdv0 = this.zzdae;
        if(zzbdv0 != null) {
            try {
                this.zzdic.removeView(zzbdv0.getView());
            }
            catch(NullPointerException unused_ex) {
            }
        }
        this.zztt();
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onPause() {
        this.zztp();
        if(this.zzdhv.zzdit != null) {
            this.zzdhv.zzdit.onPause();
        }
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcoq)).booleanValue() && this.zzdae != null && (!this.zzzo.isFinishing() || this.zzdhw == null)) {
            zzawu.zza(this.zzdae);
        }
        this.zztt();
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onRestart() {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onResume() {
        if(this.zzdhv.zzdit != null) {
            this.zzdhv.zzdit.onResume();
        }
        this.zza(this.zzzo.getResources().getConfiguration());
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcoq)).booleanValue()) {
            if(this.zzdae != null && !this.zzdae.isDestroyed()) {
                zzawu.zzb(this.zzdae);
                return;
            }
            zzawf.zzfa("The webview does not exist. Ignoring action.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onSaveInstanceState(Bundle bundle0) {
        bundle0.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdib);
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onStart() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoq)).booleanValue()) {
            if(this.zzdae != null && !this.zzdae.isDestroyed()) {
                zzawu.zzb(this.zzdae);
                return;
            }
            zzawf.zzfa("The webview does not exist. Ignoring action.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onStop() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoq)).booleanValue() && this.zzdae != null && (!this.zzzo.isFinishing() || this.zzdhw == null)) {
            zzawu.zza(this.zzdae);
        }
        this.zztt();
    }

    public final void setRequestedOrientation(int v) {
        if(30 >= ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcqq)))) && 30 <= ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcqr)))) && Build.VERSION.SDK_INT >= ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcqs)))) && Build.VERSION.SDK_INT <= ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcqt))))) {
            return;
        }
        try {
            this.zzzo.setRequestedOrientation(v);
        }
        catch(Throwable throwable0) {
            zzq.zzkz().zzb(throwable0, "AdOverlay.setRequestedOrientation");
        }
    }

    private final void zza(Configuration configuration0) {
        boolean z = true;
        boolean z1 = false;
        if(this.zzblg && (this.zzdhv == null || this.zzdhv.zzdja == null || !this.zzdhv.zzdja.zzblh) || zzq.zzkx().zza(this.zzzo, configuration0)) {
            z = false;
        }
        else if(Build.VERSION.SDK_INT >= 19 && (this.zzdhv != null && this.zzdhv.zzdja != null && this.zzdhv.zzdja.zzblm)) {
            z1 = true;
        }
        Window window0 = this.zzzo.getWindow();
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzckc)).booleanValue() && Build.VERSION.SDK_INT >= 19) {
            window0.getDecorView().setSystemUiVisibility((!z || !z1 ? 0x100 : 0x1706));
            return;
        }
        if(z) {
            window0.addFlags(0x400);
            window0.clearFlags(0x800);
            if(Build.VERSION.SDK_INT >= 19 && z1) {
                window0.getDecorView().setSystemUiVisibility(0x1002);
            }
        }
        else {
            window0.addFlags(0x800);
            window0.clearFlags(0x400);
        }
    }

    public final void zza(View view0, WebChromeClient.CustomViewCallback webChromeClient$CustomViewCallback0) {
        this.zzdhz = new FrameLayout(this.zzzo);
        this.zzdhz.setBackgroundColor(0xFF000000);
        this.zzdhz.addView(view0, -1, -1);
        this.zzzo.setContentView(this.zzdhz);
        this.zzdii = true;
        this.zzdia = webChromeClient$CustomViewCallback0;
        this.zzdhy = true;
    }

    public final void zza(boolean z, boolean z1) {
        boolean z2 = true;
        boolean z3 = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcka)).booleanValue() && (this.zzdhv != null && this.zzdhv.zzdja != null && this.zzdhv.zzdja.zzbln);
        boolean z4 = ((Boolean)zzvh.zzpd().zzd(zzzx.zzckb)).booleanValue() && (this.zzdhv != null && this.zzdhv.zzdja != null && this.zzdhv.zzdja.zzblo);
        if(z && z1 && z3 && !z4) {
            new zzaow(this.zzdae, "useCustomClose").zzdt("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        zzo zzo0 = this.zzdhx;
        if(zzo0 != null) {
            if(!z4 && (!z1 || z3)) {
                z2 = false;
            }
            zzo0.zzal(z2);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void zzad(IObjectWrapper iObjectWrapper0) {
        this.zza(((Configuration)ObjectWrapper.unwrap(iObjectWrapper0)));
    }

    private final void zzaj(boolean z) {
        int v = (int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcos)));
        zzr zzr0 = new zzr();
        zzr0.size = 50;
        zzr0.paddingLeft = z ? v : 0;
        zzr0.paddingRight = z ? 0 : v;
        zzr0.paddingTop = 0;
        zzr0.paddingBottom = v;
        this.zzdhx = new zzo(this.zzzo, zzr0, this);
        RelativeLayout.LayoutParams relativeLayout$LayoutParams0 = new RelativeLayout.LayoutParams(-2, -2);
        relativeLayout$LayoutParams0.addRule(10);
        relativeLayout$LayoutParams0.addRule((z ? 11 : 9));
        this.zza(z, this.zzdhv.zzdiv);
        this.zzdic.addView(this.zzdhx, relativeLayout$LayoutParams0);
    }

    private final void zzak(boolean z) throws zzi {
        if(!this.zzdii) {
            this.zzzo.requestWindowFeature(1);
        }
        Window window0 = this.zzzo.getWindow();
        if(window0 == null) {
            throw new zzi("Invalid activity, no window available.");
        }
        zzbfi zzbfi0 = this.zzdhv.zzdae == null ? null : this.zzdhv.zzdae.zzaaf();
        boolean z1 = false;
        boolean z2 = zzbfi0 != null && zzbfi0.zzaay();
        this.zzdid = false;
        if(z2) {
            switch(this.zzdhv.orientation) {
                case 6: {
                    if(this.zzzo.getResources().getConfiguration().orientation == 1) {
                        z1 = true;
                    }
                    this.zzdid = z1;
                    break;
                }
                case 7: {
                    if(this.zzzo.getResources().getConfiguration().orientation == 2) {
                        z1 = true;
                    }
                    this.zzdid = z1;
                }
            }
        }
        zzawf.zzeb(("Delay onShow to next orientation change: " + this.zzdid));
        this.setRequestedOrientation(this.zzdhv.orientation);
        window0.setFlags(0x1000000, 0x1000000);
        zzawf.zzeb("Hardware acceleration on the AdActivity window enabled.");
        if(this.zzblg) {
            this.zzdic.setBackgroundColor(zze.zzdhu);
        }
        else {
            this.zzdic.setBackgroundColor(0xFF000000);
        }
        this.zzzo.setContentView(this.zzdic);
        this.zzdii = true;
        if(z) {
            try {
                zzbfl zzbfl0 = this.zzdhv.zzdae == null ? null : this.zzdhv.zzdae.zzaad();
                this.zzdae = zzbee.zza(this.zzzo, zzbfl0, (this.zzdhv.zzdae == null ? null : this.zzdhv.zzdae.zzaae()), true, z2, null, this.zzdhv.zzblu, null, null, (this.zzdhv.zzdae == null ? null : this.zzdhv.zzdae.zzyt()), zzsn.zzmy(), null, false);
            }
            catch(Exception exception0) {
                zzawf.zzc("Error obtaining webview.", exception0);
                throw new zzi("Could not obtain webview for the overlay.");
            }
            this.zzdae.zzaaf().zza(null, this.zzdhv.zzcxu, null, this.zzdhv.zzcxv, this.zzdhv.zzdix, true, null, (this.zzdhv.zzdae == null ? null : this.zzdhv.zzdae.zzaaf().zzaax()), null, null);
            this.zzdae.zzaaf().zza(new zzd(this));
            if(this.zzdhv.url == null) {
                if(this.zzdhv.zzdiw == null) {
                    throw new zzi("No URL or HTML to display in ad overlay.");
                }
                this.zzdae.loadDataWithBaseURL(this.zzdhv.zzdiu, this.zzdhv.zzdiw, "text/html", "UTF-8", null);
            }
            else {
                this.zzdae.loadUrl(this.zzdhv.url);
            }
            if(this.zzdhv.zzdae != null) {
                this.zzdhv.zzdae.zzb(this);
            }
        }
        else {
            this.zzdae = this.zzdhv.zzdae;
            this.zzdae.zzbu(this.zzzo);
        }
        this.zzdae.zza(this);
        if(this.zzdhv.zzdae != null) {
            zze.zzc(this.zzdhv.zzdae.zzaaj(), this.zzdic);
        }
        ViewParent viewParent0 = this.zzdae.getParent();
        if(viewParent0 != null && viewParent0 instanceof ViewGroup) {
            ((ViewGroup)viewParent0).removeView(this.zzdae.getView());
        }
        if(this.zzblg) {
            this.zzdae.zzaar();
        }
        this.zzdae.zza(null, this.zzzo, this.zzdhv.zzdiu, this.zzdhv.zzdiw);
        this.zzdic.addView(this.zzdae.getView(), -1, -1);
        if(!z && !this.zzdid) {
            this.zztw();
        }
        this.zzaj(z2);
        if(this.zzdae.zzaah()) {
            this.zza(z2, true);
        }
    }

    private static void zzc(@Nullable IObjectWrapper iObjectWrapper0, @Nullable View view0) {
        if(iObjectWrapper0 != null && view0 != null) {
            zzq.zzlk().zza(iObjectWrapper0, view0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void zzdk() {
        this.zzdii = true;
    }

    public final void zztp() {
        AdOverlayInfoParcel adOverlayInfoParcel0 = this.zzdhv;
        if(adOverlayInfoParcel0 != null && this.zzdhy) {
            this.setRequestedOrientation(adOverlayInfoParcel0.orientation);
        }
        if(this.zzdhz != null) {
            this.zzzo.setContentView(this.zzdic);
            this.zzdii = true;
            this.zzdhz.removeAllViews();
            this.zzdhz = null;
        }
        WebChromeClient.CustomViewCallback webChromeClient$CustomViewCallback0 = this.zzdia;
        if(webChromeClient$CustomViewCallback0 != null) {
            webChromeClient$CustomViewCallback0.onCustomViewHidden();
            this.zzdia = null;
        }
        this.zzdhy = false;
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzw
    public final void zztq() {
        this.zzdie = 1;
        this.zzzo.finish();
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final boolean zztr() {
        this.zzdie = 0;
        zzbdv zzbdv0 = this.zzdae;
        if(zzbdv0 == null) {
            return true;
        }
        boolean z = zzbdv0.zzaam();
        if(!z) {
            this.zzdae.zza("onbackblocked", Collections.emptyMap());
        }
        return z;
    }

    public final void zzts() {
        this.zzdic.removeView(this.zzdhx);
        this.zzaj(true);
    }

    private final void zztt() {
        if(this.zzzo.isFinishing() && !this.zzdij) {
            this.zzdij = true;
            zzbdv zzbdv0 = this.zzdae;
            if(zzbdv0 != null) {
                zzbdv0.zzde(this.zzdie);
                Object object0 = this.zzdif;
                synchronized(object0) {
                    if(!this.zzdih && this.zzdae.zzaan()) {
                        this.zzdig = () -> {
                            if(this.zzdik) {
                                return;
                            }
                            this.zzdik = true;
                            zzbdv zzbdv0 = this.zzdae;
                            if(zzbdv0 != null) {
                                this.zzdic.removeView(zzbdv0.getView());
                                zzk zzk0 = this.zzdhw;
                                if(zzk0 != null) {
                                    this.zzdae.zzbu(zzk0.zzur);
                                    this.zzdae.zzax(false);
                                    this.zzdhw.parent.addView(this.zzdae.getView(), this.zzdhw.index, this.zzdhw.zzdip);
                                    this.zzdhw = null;
                                }
                                else if(this.zzzo.getApplicationContext() != null) {
                                    this.zzdae.zzbu(this.zzzo.getApplicationContext());
                                }
                                this.zzdae = null;
                            }
                            if(this.zzdhv != null && this.zzdhv.zzdit != null) {
                                this.zzdhv.zzdit.zztj();
                            }
                            if(this.zzdhv != null && this.zzdhv.zzdae != null) {
                                zze.zzc(this.zzdhv.zzdae.zzaaj(), this.zzdhv.zzdae.getView());
                            }
                        };
                        Runnable runnable0 = this.zzdig;
                        long v1 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzcjz)));
                        zzawo.zzdtx.postDelayed(runnable0, v1);
                        return;
                    }
                }
            }
            this.zztu();
        }
    }

    // 检测为 Lambda 实现
    @VisibleForTesting
    final void zztu() [...]

    public final void zztv() {
        if(this.zzdid) {
            this.zzdid = false;
            this.zztw();
        }
    }

    private final void zztw() {
        this.zzdae.zztw();
    }

    public final void zztx() {
        this.zzdic.zzdin = true;
    }

    public final void zzty() {
        synchronized(this.zzdif) {
            this.zzdih = true;
            if(this.zzdig != null) {
                zzawo.zzdtx.removeCallbacks(this.zzdig);
                zzawo.zzdtx.post(this.zzdig);
            }
        }
    }
}

