package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;

public final class zzcrz extends zzvw {
    private final ViewGroup zzfgb;
    private final zzdeu zzfir;
    private final zzvk zzfmr;
    private final zzblg zzgfn;
    private final Context zzur;

    public zzcrz(Context context0, @Nullable zzvk zzvk0, zzdeu zzdeu0, zzblg zzblg0) {
        this.zzur = context0;
        this.zzfmr = zzvk0;
        this.zzfir = zzdeu0;
        this.zzgfn = zzblg0;
        FrameLayout frameLayout0 = new FrameLayout(this.zzur);
        frameLayout0.removeAllViews();
        frameLayout0.addView(this.zzgfn.zzagm(), zzq.zzkx().zzwu());
        frameLayout0.setMinimumHeight(this.zzke().heightPixels);
        frameLayout0.setMinimumWidth(this.zzke().widthPixels);
        this.zzfgb = frameLayout0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgfn.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final Bundle getAdMetadata() throws RemoteException {
        zzawf.zzez("getAdMetadata is not supported in Publisher AdView returned by AdLoader.");
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getAdUnitId() throws RemoteException {
        return this.zzfir.zzgqr;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getMediationAdapterClassName() throws RemoteException {
        return this.zzgfn.zzahi() == null ? null : this.zzgfn.zzahi().getMediationAdapterClassName();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxj getVideoController() throws RemoteException {
        return this.zzgfn.getVideoController();
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
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgfn.zzahh().zzby(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgfn.zzahh().zzbz(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        zzawf.zzez("setManualImpressionsEnabled is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setUserId(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void showInterstitial() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void stopLoading() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzaaq zzaaq0) throws RemoteException {
        zzawf.zzez("setOnCustomRenderedAdLoadedListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapl zzapl0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapr zzapr0, String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzasb zzasb0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzrh zzrh0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzuk zzuk0) throws RemoteException {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
        zzblg zzblg0 = this.zzgfn;
        if(zzblg0 != null) {
            zzblg0.zza(this.zzfgb, zzuk0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzur zzur0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvj zzvj0) throws RemoteException {
        zzawf.zzez("setAdClickListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvk zzvk0) throws RemoteException {
        zzawf.zzez("setAdListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwa zzwa0) throws RemoteException {
        zzawf.zzez("setAdMetadataListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwf zzwf0) throws RemoteException {
        zzawf.zzez("setAppEventListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwl zzwl0) throws RemoteException {
        zzawf.zzez("setCorrelationIdProvider is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxd zzxd0) {
        zzawf.zzez("setOnPaidEventListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxp zzxp0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzzc zzzc0) throws RemoteException {
        zzawf.zzez("setVideoOptions is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean zza(zzuh zzuh0) throws RemoteException {
        zzawf.zzez("loadAd is not supported for a Publisher AdView returned from AdLoader.");
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzbs(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final IObjectWrapper zzkc() throws RemoteException {
        return ObjectWrapper.wrap(this.zzfgb);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzkd() throws RemoteException {
        this.zzgfn.zzkd();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzuk zzke() {
        Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
        List list0 = Collections.singletonList(this.zzgfn.zzagl());
        return zzdex.zzb(this.zzur, list0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String zzkf() throws RemoteException {
        return this.zzgfn.zzahi() == null ? null : this.zzgfn.zzahi().getMediationAdapterClassName();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxe zzkg() {
        return this.zzgfn.zzahi();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzwf zzkh() throws RemoteException {
        return this.zzfir.zzgqw;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzvk zzki() throws RemoteException {
        return this.zzfmr;
    }
}

