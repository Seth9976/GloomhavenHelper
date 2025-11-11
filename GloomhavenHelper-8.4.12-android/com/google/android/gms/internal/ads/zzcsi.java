package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public final class zzcsi extends zzvw {
    private final Executor zzfeo;
    private final zzbgk zzgcx;
    private final Context zzgfp;
    @GuardedBy("this")
    private final zzdew zzgfq;
    private final zzcsg zzgfu;
    private final zzcsj zzgfw;
    private final zzcsf zzgfx;
    @Nullable
    @GuardedBy("this")
    private zzaaq zzgfz;
    @Nullable
    @GuardedBy("this")
    private zzdof zzgga;
    private final zzdct zzggf;
    @Nullable
    @GuardedBy("this")
    private zzbvu zzggg;
    @GuardedBy("this")
    private boolean zzggh;

    public zzcsi(zzbgk zzbgk0, Context context0, zzuk zzuk0, String s) {
        this.zzgfu = new zzcsg();
        this.zzgfw = new zzcsj();
        this.zzggf = new zzdct(new zzdfw());
        this.zzgfx = new zzcsf();
        this.zzgfq = new zzdew();
        this.zzggh = false;
        this.zzgcx = zzbgk0;
        this.zzgfq.zzd(zzuk0).zzgn(s);
        this.zzfeo = zzbgk0.zzacf();
        this.zzgfp = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void destroy() {
        synchronized(this) {
            Preconditions.checkMainThread("destroy must be called on the main UI thread.");
            if(this.zzggg != null) {
                this.zzggg.zzahh().zzca(null);
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
            return this.zzggg != null && this.zzggg.zzahi() != null ? this.zzggg.zzahi().getMediationAdapterClassName() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxj getVideoController() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isLoading() {
        synchronized(this) {
            return this.zzgga != null && !this.zzgga.isDone();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isReady() {
        synchronized(this) {
            Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
            return this.zzaoo();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void pause() {
        synchronized(this) {
            Preconditions.checkMainThread("pause must be called on the main UI thread.");
            if(this.zzggg != null) {
                this.zzggg.zzahh().zzby(null);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void resume() {
        synchronized(this) {
            Preconditions.checkMainThread("resume must be called on the main UI thread.");
            if(this.zzggg != null) {
                this.zzggg.zzahh().zzbz(null);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setImmersiveMode(boolean z) {
        synchronized(this) {
            Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
            this.zzggh = z;
        }
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
        synchronized(this) {
            Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
            if(this.zzggg == null) {
                return;
            }
            if(this.zzggg.zzaja()) {
                this.zzggg.zzbi(this.zzggh);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void stopLoading() {
    }

    static zzbvu zza(zzcsi zzcsi0, zzbvu zzbvu0) {
        zzcsi0.zzggg = zzbvu0;
        return zzbvu0;
    }

    static zzdof zza(zzcsi zzcsi0, zzdof zzdof0) {
        zzcsi0.zzgga = null;
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
        this.zzggf.zzb(zzasb0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzrh zzrh0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzuk zzuk0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzur zzur0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvj zzvj0) {
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
            this.zzgfq.zzc(zzzc0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean zza(zzuh zzuh0) {
        synchronized(this) {
            Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
            if(this.zzgga == null && !this.zzaoo()) {
                zzdfc.zze(this.zzgfp, zzuh0.zzccp);
                this.zzggg = null;
                zzdeu zzdeu0 = this.zzgfq.zzg(zzuh0).zzarb();
                zza zzbtl$zza0 = new zza();
                if(this.zzggf != null) {
                    Executor executor0 = this.zzgcx.zzacf();
                    zza zzbtl$zza1 = zzbtl$zza0.zza(this.zzggf, executor0);
                    Executor executor1 = this.zzgcx.zzacf();
                    zza zzbtl$zza2 = zzbtl$zza1.zza(this.zzggf, executor1);
                    Executor executor2 = this.zzgcx.zzacf();
                    zzbtl$zza2.zza(this.zzggf, executor2);
                }
                zzbws zzbws0 = this.zzgcx.zzacp().zzd(new com.google.android.gms.internal.ads.zzbpt.zza().zzcc(this.zzgfp).zza(zzdeu0).zzahz());
                Executor executor3 = this.zzgcx.zzacf();
                zza zzbtl$zza3 = zzbtl$zza0.zza(this.zzgfu, executor3);
                Executor executor4 = this.zzgcx.zzacf();
                zza zzbtl$zza4 = zzbtl$zza3.zza(this.zzgfu, executor4);
                Executor executor5 = this.zzgcx.zzacf();
                zza zzbtl$zza5 = zzbtl$zza4.zza(this.zzgfu, executor5);
                Executor executor6 = this.zzgcx.zzacf();
                zza zzbtl$zza6 = zzbtl$zza5.zza(this.zzgfu, executor6);
                Executor executor7 = this.zzgcx.zzacf();
                zza zzbtl$zza7 = zzbtl$zza6.zza(this.zzgfw, executor7);
                Executor executor8 = this.zzgcx.zzacf();
                zzbwt zzbwt0 = zzbws0.zzd(zzbtl$zza7.zza(this.zzgfx, executor8).zzais()).zzb(new zzcrh(this.zzgfz)).zzaev();
                this.zzgga = zzbwt0.zzadx().zzahq();
                zzdnt.zza(this.zzgga, new zzcsl(this, zzbwt0), this.zzfeo);
                return true;
            }
            return false;
        }
    }

    private final boolean zzaoo() {
        synchronized(this) {
            return this.zzggg != null && !this.zzggg.isClosed();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzbs(String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final IObjectWrapper zzkc() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzkd() {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzuk zzke() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String zzkf() {
        synchronized(this) {
            return this.zzggg != null && this.zzggg.zzahi() != null ? this.zzggg.zzahi().getMediationAdapterClassName() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxe zzkg() {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcsf)).booleanValue()) {
                return null;
            }
            return this.zzggg == null ? null : this.zzggg.zzahi();
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

