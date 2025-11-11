package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaq;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzapr;
import com.google.android.gms.internal.ads.zzasb;
import com.google.android.gms.internal.ads.zzawf;
import com.google.android.gms.internal.ads.zzayx;
import com.google.android.gms.internal.ads.zzazo;
import com.google.android.gms.internal.ads.zzazq;
import com.google.android.gms.internal.ads.zzdq;
import com.google.android.gms.internal.ads.zzdt;
import com.google.android.gms.internal.ads.zzrh;
import com.google.android.gms.internal.ads.zzuh;
import com.google.android.gms.internal.ads.zzuk;
import com.google.android.gms.internal.ads.zzur;
import com.google.android.gms.internal.ads.zzvj;
import com.google.android.gms.internal.ads.zzvk;
import com.google.android.gms.internal.ads.zzvw;
import com.google.android.gms.internal.ads.zzwa;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwl;
import com.google.android.gms.internal.ads.zzxd;
import com.google.android.gms.internal.ads.zzxe;
import com.google.android.gms.internal.ads.zzxj;
import com.google.android.gms.internal.ads.zzxp;
import com.google.android.gms.internal.ads.zzzc;
import java.util.Map;
import java.util.concurrent.Future;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzl extends zzvw {
    private final zzazo zzblu;
    private final zzuk zzblv;
    private final Future zzblw;
    private final zzo zzblx;
    @Nullable
    private WebView zzbly;
    @Nullable
    private zzvk zzblz;
    @Nullable
    private zzdq zzbma;
    private AsyncTask zzbmb;
    private final Context zzur;

    public zzl(Context context0, zzuk zzuk0, String s, zzazo zzazo0) {
        this.zzur = context0;
        this.zzblu = zzazo0;
        this.zzblv = zzuk0;
        this.zzbly = new WebView(this.zzur);
        zzm zzm0 = new zzm(this);
        this.zzblw = zzazq.zzdxk.zzd(zzm0);
        this.zzblx = new zzo(context0, s);
        this.zzbm(0);
        this.zzbly.setVerticalScrollBarEnabled(false);
        this.zzbly.getSettings().setJavaScriptEnabled(true);
        this.zzbly.setWebViewClient(new zzk(this));
        this.zzbly.setOnTouchListener(new zzn(this));
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzbmb.cancel(true);
        this.zzblw.cancel(true);
        this.zzbly.destroy();
        this.zzbly = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final Bundle getAdMetadata() {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    @Nullable
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    @Nullable
    public final zzxj getVideoController() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isLoading() throws RemoteException {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isReady() throws RemoteException {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setImmersiveMode(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setUserId(String s) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void stopLoading() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzaaq zzaaq0) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapl zzapl0) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapr zzapr0, String s) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzasb zzasb0) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzrh zzrh0) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzuk zzuk0) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzur zzur0) {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvj zzvj0) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvk zzvk0) throws RemoteException {
        this.zzblz = zzvk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwa zzwa0) {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwf zzwf0) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwl zzwl0) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxd zzxd0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxp zzxp0) {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzzc zzzc0) {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean zza(zzuh zzuh0) throws RemoteException {
        Preconditions.checkNotNull(this.zzbly, "This Search Ad has already been torn down");
        this.zzblx.zza(zzuh0, this.zzblu);
        this.zzbmb = new zzp(this, null).execute(new Void[0]);
        return true;
    }

    @VisibleForTesting
    final void zzbm(int v) {
        if(this.zzbly == null) {
            return;
        }
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = new ViewGroup.LayoutParams(-1, v);
        this.zzbly.setLayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzbs(String s) {
        throw new IllegalStateException("Unused method");
    }

    @VisibleForTesting
    final int zzbt(String s) {
        String s1 = Uri.parse(s).getQueryParameter("height");
        if(TextUtils.isEmpty(s1)) {
            return 0;
        }
        try {
            int v = Integer.parseInt(s1);
            return zzayx.zzb(this.zzur, v);
        }
        catch(NumberFormatException unused_ex) {
            return 0;
        }
    }

    private final String zzbu(String s) {
        if(this.zzbma == null) {
            return s;
        }
        Uri uri0 = Uri.parse(s);
        try {
            uri0 = this.zzbma.zza(uri0, this.zzur, null, null);
            return uri0.toString();
        }
        catch(zzdt zzdt0) {
            zzawf.zzd("Unable to process ad data", zzdt0);
            return uri0.toString();
        }
    }

    private final void zzbv(String s) {
        Intent intent0 = new Intent("android.intent.action.VIEW");
        intent0.setData(Uri.parse(s));
        this.zzur.startActivity(intent0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final IObjectWrapper zzkc() throws RemoteException {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzbly);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzkd() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzuk zzke() throws RemoteException {
        return this.zzblv;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    @Nullable
    public final String zzkf() throws RemoteException {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    @Nullable
    public final zzxe zzkg() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzwf zzkh() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzvk zzki() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    @VisibleForTesting
    final String zzkj() {
        Uri.Builder uri$Builder0 = new Uri.Builder();
        uri$Builder0.scheme("https://").appendEncodedPath(((String)zzabb.zzcts.get()));
        uri$Builder0.appendQueryParameter("query", this.zzblx.getQuery());
        uri$Builder0.appendQueryParameter("pubId", this.zzblx.zzkm());
        Map map0 = this.zzblx.zzkn();
        for(Object object0: map0.keySet()) {
            uri$Builder0.appendQueryParameter(((String)object0), ((String)map0.get(((String)object0))));
        }
        Uri uri0 = uri$Builder0.build();
        zzdq zzdq0 = this.zzbma;
        if(zzdq0 != null) {
            try {
                uri0 = zzdq0.zza(uri0, this.zzur);
                return this.zzkk() + "#" + uri0.getEncodedQuery();
            }
            catch(zzdt zzdt0) {
                zzawf.zzd("Unable to process ad data", zzdt0);
                return this.zzkk() + "#" + uri0.getEncodedQuery();
            }
        }
        return this.zzkk() + "#" + uri0.getEncodedQuery();
    }

    @VisibleForTesting
    final String zzkk() {
        String s = this.zzblx.zzkl();
        if(TextUtils.isEmpty(s)) {
            s = "www.google.com";
        }
        return "https://" + s + ((String)zzabb.zzcts.get());
    }
}

