package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.impl.R.string;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public final class zzbeh extends FrameLayout implements zzbdv {
    private final zzbdv zzegu;
    private final zzbbb zzegv;
    private final AtomicBoolean zzegw;

    public zzbeh(zzbdv zzbdv0) {
        super(zzbdv0.getContext());
        this.zzegw = new AtomicBoolean();
        this.zzegu = zzbdv0;
        this.zzegv = new zzbbb(zzbdv0.zzaaa(), this, this);
        if(!this.zzaaw()) {
            this.addView(this.zzegu.getView());
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void destroy() {
        IObjectWrapper iObjectWrapper0 = this.zzaaj();
        if(iObjectWrapper0 != null) {
            zzbek zzbek0 = () -> zzq.zzlk().zzac(iObjectWrapper0);
            zzawo.zzdtx.post(zzbek0);
            zzbej zzbej0 = new zzbej(this);
            long v = (long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcpa)))));
            zzawo.zzdtx.postDelayed(zzbej0, v);
            return;
        }
        this.zzegu.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final View getView() {
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final WebView getWebView() {
        return this.zzegu.getWebView();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean isDestroyed() {
        return this.zzegu.isDestroyed();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void loadData(String s, String s1, String s2) {
        this.zzegu.loadData(s, s1, s2);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void loadDataWithBaseURL(String s, String s1, String s2, String s3, String s4) {
        this.zzegu.loadDataWithBaseURL(s, s1, s2, s3, s4);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void loadUrl(String s) {
        this.zzegu.loadUrl(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void onPause() {
        this.zzegv.onPause();
        this.zzegu.onPause();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void onResume() {
        this.zzegu.onResume();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv, android.view.View
    public final void setOnClickListener(View.OnClickListener view$OnClickListener0) {
        this.zzegu.setOnClickListener(view$OnClickListener0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv, android.view.View
    public final void setOnTouchListener(View.OnTouchListener view$OnTouchListener0) {
        this.zzegu.setOnTouchListener(view$OnTouchListener0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void setRequestedOrientation(int v) {
        this.zzegu.setRequestedOrientation(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void setWebChromeClient(WebChromeClient webChromeClient0) {
        this.zzegu.setWebChromeClient(webChromeClient0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void setWebViewClient(WebViewClient webViewClient0) {
        this.zzegu.setWebViewClient(webViewClient0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(ViewGroup viewGroup0, Activity activity0, String s, String s1) {
        this.zzegu.zza(this, activity0, s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zza(zzb zzb0) {
        this.zzegu.zza(zzb0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zze zze0) {
        this.zzegu.zza(zze0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzaca zzaca0) {
        this.zzegu.zza(zzaca0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(@Nullable zzacf zzacf0) {
        this.zzegu.zza(zzacf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzbeq zzbeq0) {
        this.zzegu.zza(zzbeq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzbfl zzbfl0) {
        this.zzegu.zza(zzbfl0);
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        this.zzegu.zza(zzpu0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(zzrb zzrb0) {
        this.zzegu.zza(zzrb0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(String s, Predicate predicate0) {
        this.zzegu.zza(s, predicate0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(String s, zzafz zzafz0) {
        this.zzegu.zza(s, zzafz0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zza(String s, zzbda zzbda0) {
        this.zzegu.zza(s, zzbda0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, Map map0) {
        this.zzegu.zza(s, map0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, JSONObject jSONObject0) {
        this.zzegu.zza(s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zza(boolean z, int v, String s) {
        this.zzegu.zza(z, v, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zza(boolean z, int v, String s, String s1) {
        this.zzegu.zza(z, v, s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final void zza(boolean z, long v) {
        this.zzegu.zza(z, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final Context zzaaa() {
        return this.zzegu.zzaaa();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zze zzaab() {
        return this.zzegu.zzaab();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zze zzaac() {
        return this.zzegu.zzaac();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzbfl zzaad() {
        return this.zzegu.zzaad();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final String zzaae() {
        return this.zzegu.zzaae();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzbfi zzaaf() {
        return this.zzegu.zzaaf();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final WebViewClient zzaag() {
        return this.zzegu.zzaag();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaah() {
        return this.zzegu.zzaah();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzdq zzaai() {
        return this.zzegu.zzaai();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final IObjectWrapper zzaaj() {
        return this.zzegu.zzaaj();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaak() {
        return this.zzegu.zzaak();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaal() {
        this.zzegv.onDestroy();
        this.zzegu.zzaal();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaam() {
        return this.zzegu.zzaam();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaan() {
        return this.zzegu.zzaan();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaao() {
        this.zzegu.zzaao();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaap() {
        this.zzegu.zzaap();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    @Nullable
    public final zzacf zzaaq() {
        return this.zzegu.zzaaq();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaar() {
        this.setBackgroundColor(0);
        this.zzegu.setBackgroundColor(0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaas() {
        TextView textView0 = new TextView(this.getContext());
        Resources resources0 = zzq.zzkz().getResources();
        textView0.setText((resources0 == null ? "Test Ad" : resources0.getString(string.s7)));
        textView0.setTextSize(15.0f);
        textView0.setTextColor(-1);
        textView0.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable0 = new GradientDrawable();
        gradientDrawable0.setShape(0);
        gradientDrawable0.setColor(-12303292);
        gradientDrawable0.setCornerRadius(8.0f);
        textView0.setBackground(gradientDrawable0);
        this.addView(textView0, new FrameLayout.LayoutParams(-2, -2, 49));
        this.bringChildToFront(textView0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzrb zzaat() {
        return this.zzegu.zzaat();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaau() {
        return this.zzegw.get();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzrp zzaav() {
        return this.zzegu.zzaav();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzaaw() {
        return this.zzegu.zzaaw();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzal(boolean z) {
        this.zzegu.zzal(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzap(IObjectWrapper iObjectWrapper0) {
        this.zzegu.zzap(iObjectWrapper0);
    }

    // 检测为 Lambda 实现
    static final void zzaq(IObjectWrapper iObjectWrapper0) [...]

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final void zzav(boolean z) {
        this.zzegu.zzav(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzax(boolean z) {
        this.zzegu.zzax(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzay(boolean z) {
        this.zzegu.zzay(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzaz(boolean z) {
        this.zzegu.zzaz(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzb(zze zze0) {
        this.zzegu.zzb(zze0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzb(String s, zzafz zzafz0) {
        this.zzegu.zzb(s, zzafz0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzb(String s, String s1, @Nullable String s2) {
        this.zzegu.zzb(s, s1, s2);
    }

    @Override  // com.google.android.gms.internal.ads.zzajb
    public final void zzb(String s, JSONObject jSONObject0) {
        this.zzegu.zzb(s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final boolean zzb(boolean z, int v) {
        if(!this.zzegw.compareAndSet(false, true)) {
            return true;
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjl)).booleanValue()) {
            return false;
        }
        if(this.zzegu.getParent() instanceof ViewGroup) {
            ((ViewGroup)this.zzegu.getParent()).removeView(this.zzegu.getView());
        }
        return this.zzegu.zzb(z, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzba(boolean z) {
        this.zzegu.zzba(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzbu(Context context0) {
        this.zzegu.zzbu(context0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfc
    public final void zzc(boolean z, int v) {
        this.zzegu.zzc(z, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzajb
    public final void zzcz(String s) {
        this.zzegu.zzcz(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzde(int v) {
        this.zzegu.zzde(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final zzbda zzff(String s) {
        return this.zzegu.zzff(s);
    }

    @Override  // com.google.android.gms.ads.internal.zzi
    public final void zzka() {
        this.zzegu.zzka();
    }

    @Override  // com.google.android.gms.ads.internal.zzi
    public final void zzkb() {
        this.zzegu.zzkb();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zztw() {
        this.zzegu.zztw();
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final void zztx() {
        this.zzegu.zztx();
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final zzbbb zzyp() {
        return this.zzegv;
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzbeq zzyq() {
        return this.zzegu.zzyq();
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final zzaai zzyr() {
        return this.zzegu.zzyr();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final Activity zzys() {
        return this.zzegu.zzys();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zza zzyt() {
        return this.zzegu.zzyt();
    }

    @Override  // com.google.android.gms.internal.ads.zzbbm
    public final String zzyu() {
        return this.zzegu.zzyu();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzaal zzyv() {
        return this.zzegu.zzyv();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final zzazo zzyw() {
        return this.zzegu.zzyw();
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
        this.zzegu.zzyz();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzzy() {
        this.zzegu.zzzy();
    }

    @Override  // com.google.android.gms.internal.ads.zzbdv
    public final void zzzz() {
        this.zzegu.zzzz();
    }
}

