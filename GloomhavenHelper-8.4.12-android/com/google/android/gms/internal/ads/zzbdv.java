package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@VisibleForTesting
@ParametersAreNonnullByDefault
public interface zzbdv extends zzi, zzaia, zzajb, zzbbm, zzbev, zzbey, zzbfc, zzbfd, zzbff, zzbfg, zzpt {
    void destroy();

    @Override  // com.google.android.gms.internal.ads.zzbbm, com.google.android.gms.internal.ads.zzbev
    Context getContext();

    int getHeight();

    ViewGroup.LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] arg1);

    ViewParent getParent();

    @Override  // com.google.android.gms.internal.ads.zzbff
    View getView();

    WebView getWebView();

    int getWidth();

    boolean isDestroyed();

    void loadData(String arg1, String arg2, String arg3);

    void loadDataWithBaseURL(String arg1, String arg2, String arg3, String arg4, @Nullable String arg5);

    void loadUrl(String arg1);

    void measure(int arg1, int arg2);

    void onPause();

    void onResume();

    @Override  // com.google.android.gms.internal.ads.zzbbm
    void setBackgroundColor(int arg1);

    void setOnClickListener(View.OnClickListener arg1);

    void setOnTouchListener(View.OnTouchListener arg1);

    void setRequestedOrientation(int arg1);

    void setWebChromeClient(WebChromeClient arg1);

    void setWebViewClient(WebViewClient arg1);

    void zza(ViewGroup arg1, Activity arg2, String arg3, String arg4);

    void zza(zze arg1);

    void zza(zzaca arg1);

    void zza(zzacf arg1);

    @Override  // com.google.android.gms.internal.ads.zzbbm
    void zza(zzbeq arg1);

    void zza(zzbfl arg1);

    void zza(zzrb arg1);

    void zza(String arg1, Predicate arg2);

    void zza(String arg1, zzafz arg2);

    @Override  // com.google.android.gms.internal.ads.zzbbm
    void zza(String arg1, zzbda arg2);

    Context zzaaa();

    zze zzaab();

    zze zzaac();

    @Override  // com.google.android.gms.internal.ads.zzbfe
    zzbfl zzaad();

    String zzaae();

    @Nullable
    zzbfi zzaaf();

    WebViewClient zzaag();

    boolean zzaah();

    @Override  // com.google.android.gms.internal.ads.zzbfd
    zzdq zzaai();

    @Nullable
    IObjectWrapper zzaaj();

    @Override  // com.google.android.gms.internal.ads.zzbey
    boolean zzaak();

    void zzaal();

    boolean zzaam();

    boolean zzaan();

    void zzaao();

    void zzaap();

    zzacf zzaaq();

    void zzaar();

    void zzaas();

    zzrb zzaat();

    boolean zzaau();

    zzrp zzaav();

    boolean zzaaw();

    void zzal(boolean arg1);

    void zzap(IObjectWrapper arg1);

    void zzax(boolean arg1);

    void zzay(boolean arg1);

    void zzaz(boolean arg1);

    void zzb(zze arg1);

    void zzb(String arg1, zzafz arg2);

    void zzb(String arg1, String arg2, @Nullable String arg3);

    boolean zzb(boolean arg1, int arg2);

    void zzba(boolean arg1);

    void zzbu(Context arg1);

    void zzde(int arg1);

    void zztw();

    @Override  // com.google.android.gms.internal.ads.zzbbm
    @Nullable
    zzbeq zzyq();

    @Override  // com.google.android.gms.internal.ads.zzbbm, com.google.android.gms.internal.ads.zzbev
    Activity zzys();

    @Override  // com.google.android.gms.internal.ads.zzbbm
    zza zzyt();

    @Override  // com.google.android.gms.internal.ads.zzbbm
    zzaal zzyv();

    @Override  // com.google.android.gms.internal.ads.zzbbm, com.google.android.gms.internal.ads.zzbfg
    zzazo zzyw();

    void zzzy();

    void zzzz();
}

