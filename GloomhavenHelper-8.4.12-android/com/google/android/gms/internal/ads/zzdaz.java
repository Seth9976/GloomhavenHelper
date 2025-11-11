package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzo;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.zzw;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

public final class zzdaz extends zzvw implements zzw, zzbrw, zzrb {
    private final zzazo zzblr;
    private final String zzbri;
    private final ViewGroup zzfgb;
    private final zzbgk zzgcx;
    private final Context zzgfp;
    private AtomicBoolean zzgmk;
    private final zzdat zzgml;
    private final zzdbh zzgmm;
    @Nullable
    private zzbko zzgmn;
    @Nullable
    @GuardedBy("this")
    protected zzbla zzgmo;

    public zzdaz(zzbgk zzbgk0, Context context0, String s, zzdat zzdat0, zzdbh zzdbh0, zzazo zzazo0) {
        this.zzgmk = new AtomicBoolean();
        this.zzfgb = new FrameLayout(context0);
        this.zzgcx = zzbgk0;
        this.zzgfp = context0;
        this.zzbri = s;
        this.zzgml = zzdat0;
        this.zzgmm = zzdbh0;
        zzdbh0.zza(this);
        this.zzblr = zzazo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void destroy() {
        synchronized(this) {
            Preconditions.checkMainThread("destroy must be called on the main UI thread.");
            if(this.zzgmo != null) {
                this.zzgmo.destroy();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final Bundle getAdMetadata() {
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getAdUnitId() {
        synchronized(this) {
        }
        return this.zzbri;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getMediationAdapterClassName() {
        synchronized(this) {
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxj getVideoController() {
        synchronized(this) {
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isLoading() {
        synchronized(this) {
            return this.zzgml.isLoading();
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
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void resume() {
        synchronized(this) {
            Preconditions.checkMainThread("resume must be called on the main UI thread.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setImmersiveMode(boolean z) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setManualImpressionsEnabled(boolean z) {
        synchronized(this) {
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

    static ViewGroup zza(zzdaz zzdaz0) {
        return zzdaz0.zzfgb;
    }

    private final zzo zza(zzbla zzbla0) {
        boolean z = zzbla0.zzaay();
        int v = (int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcos)));
        zzr zzr0 = new zzr();
        zzr0.size = 50;
        zzr0.paddingLeft = z ? v : 0;
        zzr0.paddingRight = z ? 0 : v;
        zzr0.paddingTop = 0;
        zzr0.paddingBottom = v;
        return new zzo(this.zzgfp, zzr0, this);
    }

    static zzo zza(zzdaz zzdaz0, zzbla zzbla0) {
        return zzdaz0.zza(zzbla0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzaaq zzaaq0) {
        synchronized(this) {
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
        this.zzgmm.zzb(zzrh0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzuk zzuk0) {
        synchronized(this) {
            Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzur zzur0) {
        this.zzgml.zza(zzur0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvj zzvj0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvk zzvk0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwa zzwa0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwf zzwf0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwl zzwl0) {
        synchronized(this) {
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxd zzxd0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxp zzxp0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzzc zzzc0) {
        synchronized(this) {
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean zza(zzuh zzuh0) throws RemoteException {
        synchronized(this) {
            Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
            if(this.isLoading()) {
                return false;
            }
            this.zzgmk = new AtomicBoolean();
            zzdbe zzdbe0 = new zzdbe(this);
            zzdbd zzdbd0 = new zzdbd(this);
            return this.zzgml.zza(zzuh0, this.zzbri, zzdbe0, zzdbd0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrw
    public final void zzaia() {
        zzbla zzbla0 = this.zzgmo;
        if(zzbla0 == null) {
            return;
        }
        int v = zzbla0.zzagf();
        if(v <= 0) {
            return;
        }
        this.zzgmn = new zzbko(this.zzgcx.zzacg(), zzq.zzlc());
        this.zzgmn.zza(v, () -> this.zzgcx.zzacf().execute(() -> this.zzaqf()));
    }

    private final void zzaqf() {
        if(this.zzgmk.compareAndSet(false, true)) {
            if(this.zzgmo != null && this.zzgmo.zzago() != null) {
                this.zzgmm.zzb(this.zzgmo.zzago());
            }
            this.zzgmm.onAdClosed();
            this.zzfgb.removeAllViews();
            zzbko zzbko0 = this.zzgmn;
            if(zzbko0 != null) {
                zzq.zzky().zzb(zzbko0);
            }
            this.destroy();
        }
    }

    private final zzuk zzaqg() {
        List list0 = Collections.singletonList(this.zzgmo.zzagl());
        return zzdex.zzb(this.zzgfp, list0);
    }

    // 检测为 Lambda 实现
    final void zzaqh() [...]

    // 检测为 Lambda 实现
    final void zzaqi() [...]

    private static RelativeLayout.LayoutParams zzb(zzbla zzbla0) {
        RelativeLayout.LayoutParams relativeLayout$LayoutParams0 = new RelativeLayout.LayoutParams(-2, -2);
        relativeLayout$LayoutParams0.addRule(10);
        relativeLayout$LayoutParams0.addRule((zzbla0.zzaay() ? 11 : 9));
        return relativeLayout$LayoutParams0;
    }

    static zzazo zzb(zzdaz zzdaz0) {
        return zzdaz0.zzblr;
    }

    static void zzb(zzdaz zzdaz0, zzbla zzbla0) {
        zzdaz0.zzc(zzbla0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzbs(String s) {
    }

    static zzuk zzc(zzdaz zzdaz0) {
        return zzdaz0.zzaqg();
    }

    private final void zzc(zzbla zzbla0) {
        zzbla0.zza(this);
    }

    static RelativeLayout.LayoutParams zzd(zzbla zzbla0) {
        return zzdaz.zzb(zzbla0);
    }

    static zzdbh zzd(zzdaz zzdaz0) {
        return zzdaz0.zzgmm;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final IObjectWrapper zzkc() {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzfgb);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzkd() {
        synchronized(this) {
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzuk zzke() {
        synchronized(this) {
            Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
            if(this.zzgmo != null) {
                List list0 = Collections.singletonList(this.zzgmo.zzagl());
                return zzdex.zzb(this.zzgfp, list0);
            }
            return null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String zzkf() {
        synchronized(this) {
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxe zzkg() {
        synchronized(this) {
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzwf zzkh() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzvk zzki() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzrb
    public final void zzmr() {
        this.zzaqf();
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzw
    public final void zztq() {
        this.zzaqf();
    }
}

