package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public final class zzcsd extends zzvw implements zzbsj {
    private final ViewGroup zzfgb;
    private final zzbgk zzgcx;
    @Nullable
    @GuardedBy("this")
    private zzblg zzgfn;
    private final Context zzgfp;
    @GuardedBy("this")
    private final zzdew zzgfq;
    private final zzcsg zzgfu;
    private final zzcsh zzgfv;
    private final zzcsj zzgfw;
    private final zzcsf zzgfx;
    private final zzbsf zzgfy;
    @Nullable
    @GuardedBy("this")
    private zzaaq zzgfz;
    @Nullable
    @GuardedBy("this")
    private zzdof zzgga;

    public zzcsd(zzbgk zzbgk0, Context context0, zzuk zzuk0, String s) {
        this.zzgfu = new zzcsg();
        this.zzgfv = new zzcsh();
        this.zzgfw = new zzcsj();
        this.zzgfx = new zzcsf();
        this.zzgfq = new zzdew();
        this.zzfgb = new FrameLayout(context0);
        this.zzgcx = zzbgk0;
        this.zzgfp = context0;
        this.zzgfq.zzd(zzuk0).zzgn(s);
        this.zzgfy = zzbgk0.zzacj();
        Executor executor0 = this.zzgcx.zzacf();
        this.zzgfy.zza(this, executor0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void destroy() {
        synchronized(this) {
            Preconditions.checkMainThread("destroy must be called on the main UI thread.");
            if(this.zzgfn != null) {
                this.zzgfn.destroy();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("getAdMetadata must be called on the main UI thread.");
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getAdUnitId() {
        synchronized(this) {
        }
        return this.zzgfq.zzara();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getMediationAdapterClassName() {
        synchronized(this) {
            return this.zzgfn != null && this.zzgfn.zzahi() != null ? this.zzgfn.zzahi().getMediationAdapterClassName() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxj getVideoController() {
        synchronized(this) {
            Preconditions.checkMainThread("getVideoController must be called from the main thread.");
            return this.zzgfn != null ? this.zzgfn.getVideoController() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isLoading() {
        synchronized(this) {
            return this.zzgga != null && !this.zzgga.isDone();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isReady() {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void pause() {
        synchronized(this) {
            Preconditions.checkMainThread("pause must be called on the main UI thread.");
            if(this.zzgfn != null) {
                this.zzgfn.zzahh().zzby(null);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void resume() {
        synchronized(this) {
            Preconditions.checkMainThread("resume must be called on the main UI thread.");
            if(this.zzgfn != null) {
                this.zzgfn.zzahh().zzbz(null);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setImmersiveMode(boolean z) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setManualImpressionsEnabled(boolean z) {
        synchronized(this) {
            Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
            this.zzgfq.zzbo(z);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setUserId(String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void showInterstitial() {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void stopLoading() {
    }

    static zzblg zza(zzcsd zzcsd0) {
        return zzcsd0.zzgfn;
    }

    static zzblg zza(zzcsd zzcsd0, zzblg zzblg0) {
        zzcsd0.zzgfn = zzblg0;
        return zzblg0;
    }

    static zzdof zza(zzcsd zzcsd0, zzdof zzdof0) {
        zzcsd0.zzgga = null;
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzaaq zzaaq0) {
        synchronized(this) {
            Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
            this.zzgfz = zzaaq0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapl zzapl0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapr zzapr0, String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzasb zzasb0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzrh zzrh0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzuk zzuk0) {
        synchronized(this) {
            Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
            this.zzgfq.zzd(zzuk0);
            if(this.zzgfn != null) {
                this.zzgfn.zza(this.zzfgb, zzuk0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzur zzur0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvj zzvj0) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgfv.zzb(zzvj0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvk zzvk0) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgfu.zzc(zzvk0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwa zzwa0) {
        Preconditions.checkMainThread("setAdMetadataListener must be called on the main UI thread.");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwf zzwf0) {
        Preconditions.checkMainThread("setAppEventListener must be called on the main UI thread.");
        this.zzgfw.zzb(zzwf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwl zzwl0) {
        synchronized(this) {
            Preconditions.checkMainThread("setCorrelationIdProvider must be called on the main UI thread");
            this.zzgfq.zzc(zzwl0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxd zzxd0) {
        Preconditions.checkMainThread("setPaidEventListener must be called on the main UI thread.");
        this.zzgfx.zzb(zzxd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxp zzxp0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzzc zzzc0) {
        synchronized(this) {
            Preconditions.checkMainThread("setVideoOptions must be called on the main UI thread.");
            this.zzgfq.zzc(zzzc0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean zza(zzuh zzuh0) {
        synchronized(this) {
            Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
            if(this.zzgga != null) {
                return false;
            }
            zzdfc.zze(this.zzgfp, zzuh0.zzccp);
            zzdeu zzdeu0 = this.zzgfq.zzg(zzuh0).zzarb();
            if(((Boolean)zzabk.zzcvb.get()).booleanValue() && this.zzgfq.zzke().zzcdg && this.zzgfu != null) {
                this.zzgfu.onAdFailedToLoad(1);
                return false;
            }
            zzbmc zzbmc0 = this.zzb(zzdeu0);
            this.zzgga = zzbmc0.zzadx().zzahq();
            zzdnt.zza(this.zzgga, new zzcsc(this, zzbmc0), this.zzgcx.zzacf());
            return true;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbsj
    public final void zzaie() {
        synchronized(this) {
            ViewParent viewParent0 = this.zzfgb.getParent();
            if((viewParent0 instanceof View ? zzq.zzkv().zza(((View)viewParent0), ((View)viewParent0).getContext()) : false)) {
                this.zza(this.zzgfq.zzaqz());
                return;
            }
            this.zzgfy.zzdg(60);
        }
    }

    static ViewGroup zzb(zzcsd zzcsd0) {
        return zzcsd0.zzfgb;
    }

    private final zzbmc zzb(zzdeu zzdeu0) {
        synchronized(this) {
            zzbmf zzbmf0 = this.zzgcx.zzacm().zzc(new zza().zzcc(this.zzgfp).zza(zzdeu0).zzahz());
            com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza0 = new com.google.android.gms.internal.ads.zzbtl.zza();
            Executor executor0 = this.zzgcx.zzacf();
            com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza1 = zzbtl$zza0.zza(this.zzgfu, executor0);
            Executor executor1 = this.zzgcx.zzacf();
            com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza2 = zzbtl$zza1.zza(this.zzgfv, executor1);
            Executor executor2 = this.zzgcx.zzacf();
            com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza3 = zzbtl$zza2.zza(this.zzgfu, executor2);
            Executor executor3 = this.zzgcx.zzacf();
            com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza4 = zzbtl$zza3.zza(this.zzgfu, executor3);
            Executor executor4 = this.zzgcx.zzacf();
            com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza5 = zzbtl$zza4.zza(this.zzgfu, executor4);
            Executor executor5 = this.zzgcx.zzacf();
            com.google.android.gms.internal.ads.zzbtl.zza zzbtl$zza6 = zzbtl$zza5.zza(this.zzgfw, executor5);
            Executor executor6 = this.zzgcx.zzacf();
            return zzbmf0.zzc(zzbtl$zza6.zza(this.zzgfx, executor6).zzais()).zza(new zzcrh(this.zzgfz)).zzb(new zzbxk(zzbzg.zzfpz, null)).zza(new zzbmy(this.zzgfy)).zzb(new zzblf(this.zzfgb)).zzael();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzbs(String s) {
    }

    static zzbsf zzc(zzcsd zzcsd0) {
        return zzcsd0.zzgfy;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final IObjectWrapper zzkc() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzfgb);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzkd() {
        synchronized(this) {
            Preconditions.checkMainThread("recordManualImpression must be called on the main UI thread.");
            if(this.zzgfn != null) {
                this.zzgfn.zzkd();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzuk zzke() {
        synchronized(this) {
            Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
            if(this.zzgfn != null) {
                List list0 = Collections.singletonList(this.zzgfn.zzagl());
                return zzdex.zzb(this.zzgfp, list0);
            }
        }
        return this.zzgfq.zzke();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String zzkf() {
        synchronized(this) {
            return this.zzgfn != null && this.zzgfn.zzahi() != null ? this.zzgfn.zzahi().getMediationAdapterClassName() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxe zzkg() {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcsf)).booleanValue()) {
                return null;
            }
            return this.zzgfn == null ? null : this.zzgfn.zzahi();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzwf zzkh() {
        return this.zzgfw.zzaop();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzvk zzki() {
        return this.zzgfu.zzaon();
    }
}

