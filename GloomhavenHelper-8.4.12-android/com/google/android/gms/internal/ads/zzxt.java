package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzxt {
    private final zzui zzabf;
    private VideoOptions zzbki;
    private boolean zzbkp;
    private AppEventListener zzbkr;
    private zzvx zzbrh;
    private String zzbri;
    private final zzall zzbrk;
    private zztz zzcch;
    private AdListener zzcck;
    private AdSize[] zzcdt;
    private final AtomicBoolean zzcfd;
    private final VideoController zzcfe;
    @VisibleForTesting
    private final zzvg zzcff;
    private OnCustomRenderedAdLoadedListener zzcfg;
    private ViewGroup zzcfh;
    private int zzcfi;
    @Nullable
    private OnPaidEventListener zzcfj;

    public zzxt(ViewGroup viewGroup0) {
        this(viewGroup0, null, false, zzui.zzcdb, 0);
    }

    public zzxt(ViewGroup viewGroup0, int v) {
        this(viewGroup0, null, false, zzui.zzcdb, v);
    }

    public zzxt(ViewGroup viewGroup0, AttributeSet attributeSet0, boolean z) {
        this(viewGroup0, attributeSet0, z, zzui.zzcdb, 0);
    }

    public zzxt(ViewGroup viewGroup0, AttributeSet attributeSet0, boolean z, int v) {
        this(viewGroup0, attributeSet0, false, zzui.zzcdb, v);
    }

    @VisibleForTesting
    private zzxt(ViewGroup viewGroup0, AttributeSet attributeSet0, boolean z, zzui zzui0, int v) {
        this(viewGroup0, attributeSet0, z, zzui0, null, v);
    }

    @VisibleForTesting
    private zzxt(ViewGroup viewGroup0, AttributeSet attributeSet0, boolean z, zzui zzui0, zzvx zzvx0, int v) {
        zzuk zzuk0;
        this.zzbrk = new zzall();
        this.zzcfe = new VideoController();
        this.zzcff = new zzxs(this);
        this.zzcfh = viewGroup0;
        this.zzabf = zzui0;
        this.zzbrh = null;
        this.zzcfd = new AtomicBoolean(false);
        this.zzcfi = v;
        if(attributeSet0 != null) {
            Context context0 = viewGroup0.getContext();
            try {
                zzut zzut0 = new zzut(context0, attributeSet0);
                this.zzcdt = zzut0.zzy(z);
                this.zzbri = zzut0.getAdUnitId();
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                zzvh.zzoz().zza(viewGroup0, new zzuk(context0, AdSize.BANNER), illegalArgumentException0.getMessage(), illegalArgumentException0.getMessage());
                return;
            }
            if(viewGroup0.isInEditMode()) {
                zzayx zzayx0 = zzvh.zzoz();
                AdSize adSize0 = this.zzcdt[0];
                int v1 = this.zzcfi;
                if(adSize0.equals(AdSize.INVALID)) {
                    zzuk0 = zzuk.zzos();
                }
                else {
                    zzuk zzuk1 = new zzuk(context0, adSize0);
                    zzuk1.zzcdf = zzxt.zzcm(v1);
                    zzuk0 = zzuk1;
                }
                zzayx0.zza(viewGroup0, zzuk0, "Ads by Google");
            }
        }
    }

    public final void destroy() {
        try {
            if(this.zzbrh != null) {
                this.zzbrh.destroy();
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final AdListener getAdListener() {
        return this.zzcck;
    }

    public final AdSize getAdSize() {
        if(this.zzbrh != null) {
            try {
                zzuk zzuk0 = this.zzbrh.zzke();
                if(zzuk0 != null) {
                    return zzuk0.zzot();
                }
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        return this.zzcdt == null ? null : this.zzcdt[0];
    }

    public final AdSize[] getAdSizes() {
        return this.zzcdt;
    }

    public final String getAdUnitId() {
        if(this.zzbri == null) {
            zzvx zzvx0 = this.zzbrh;
            if(zzvx0 != null) {
                try {
                    this.zzbri = zzvx0.getAdUnitId();
                    return this.zzbri;
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
        return this.zzbri;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzbkr;
    }

    public final String getMediationAdapterClassName() {
        if(this.zzbrh != null) {
            try {
                return this.zzbrh.zzkf();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        return null;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcfg;
    }

    @Nullable
    public final ResponseInfo getResponseInfo() {
        if(this.zzbrh != null) {
            try {
                return ResponseInfo.zza(this.zzbrh.zzkg());
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
                return ResponseInfo.zza(null);
            }
        }
        return ResponseInfo.zza(null);
    }

    public final VideoController getVideoController() {
        return this.zzcfe;
    }

    public final VideoOptions getVideoOptions() {
        return this.zzbki;
    }

    public final boolean isLoading() {
        if(this.zzbrh != null) {
            try {
                return this.zzbrh.isLoading();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        return false;
    }

    public final void pause() {
        try {
            if(this.zzbrh != null) {
                this.zzbrh.pause();
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void recordManualImpression() {
        if(this.zzcfd.getAndSet(true)) {
            return;
        }
        try {
            if(this.zzbrh != null) {
                this.zzbrh.zzkd();
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void resume() {
        try {
            if(this.zzbrh != null) {
                this.zzbrh.resume();
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void setAdListener(AdListener adListener0) {
        this.zzcck = adListener0;
        this.zzcff.zza(adListener0);
    }

    public final void setAdSizes(AdSize[] arr_adSize) {
        if(this.zzcdt != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.zza(arr_adSize);
    }

    public final void setAdUnitId(String s) {
        if(this.zzbri != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzbri = s;
    }

    public final void setAppEventListener(AppEventListener appEventListener0) {
        try {
            this.zzbkr = appEventListener0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((appEventListener0 == null ? null : new zzuo(appEventListener0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void setManualImpressionsEnabled(boolean z) {
        try {
            this.zzbkp = z;
            if(this.zzbrh != null) {
                this.zzbrh.setManualImpressionsEnabled(this.zzbkp);
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener0) {
        try {
            this.zzcfg = onCustomRenderedAdLoadedListener0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((onCustomRenderedAdLoadedListener0 == null ? null : new zzaav(onCustomRenderedAdLoadedListener0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener0) {
        try {
            this.zzcfj = onPaidEventListener0;
            if(this.zzbrh != null) {
                this.zzbrh.zza(new zzyx(onPaidEventListener0));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    public final void setVideoOptions(VideoOptions videoOptions0) {
        try {
            this.zzbki = videoOptions0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((videoOptions0 == null ? null : new zzzc(videoOptions0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    private static zzuk zza(Context context0, AdSize[] arr_adSize, int v) {
        for(int v1 = 0; v1 < arr_adSize.length; ++v1) {
            if(arr_adSize[v1].equals(AdSize.INVALID)) {
                return zzuk.zzos();
            }
        }
        zzuk zzuk0 = new zzuk(context0, arr_adSize);
        zzuk0.zzcdf = zzxt.zzcm(v);
        return zzuk0;
    }

    public final void zza(zztz zztz0) {
        try {
            this.zzcch = zztz0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((zztz0 == null ? null : new zzty(zztz0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void zza(zzxr zzxr0) {
        try {
            if(this.zzbrh == null) {
                if((this.zzcdt == null || this.zzbri == null) && this.zzbrh == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context0 = this.zzcfh.getContext();
                zzuk zzuk0 = zzxt.zza(context0, this.zzcdt, this.zzcfi);
                this.zzbrh = "search_v2".equals(zzuk0.zzabk) ? ((zzvx)new zzuz(zzvh.zzpa(), context0, zzuk0, this.zzbri).zzd(context0, false)) : ((zzvx)new zzuv(zzvh.zzpa(), context0, zzuk0, this.zzbri, this.zzbrk).zzd(context0, false));
                this.zzbrh.zza(new zzud(this.zzcff));
                if(this.zzcch != null) {
                    this.zzbrh.zza(new zzty(this.zzcch));
                }
                if(this.zzbkr != null) {
                    this.zzbrh.zza(new zzuo(this.zzbkr));
                }
                if(this.zzcfg != null) {
                    this.zzbrh.zza(new zzaav(this.zzcfg));
                }
                if(this.zzbki != null) {
                    this.zzbrh.zza(new zzzc(this.zzbki));
                }
                this.zzbrh.zza(new zzyx(this.zzcfj));
                this.zzbrh.setManualImpressionsEnabled(this.zzbkp);
                try {
                    IObjectWrapper iObjectWrapper0 = this.zzbrh.zzkc();
                    if(iObjectWrapper0 != null) {
                        this.zzcfh.addView(((View)ObjectWrapper.unwrap(iObjectWrapper0)));
                    }
                }
                catch(RemoteException remoteException1) {
                    zzazh.zze("#007 Could not call remote method.", remoteException1);
                }
            }
            if(this.zzbrh.zza(zzui.zza(this.zzcfh.getContext(), zzxr0))) {
                this.zzbrk.zzf(zzxr0.zzps());
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void zza(AdSize[] arr_adSize) {
        this.zzcdt = arr_adSize;
        if(this.zzbrh != null) {
            try {
                this.zzbrh.zza(zzxt.zza(this.zzcfh.getContext(), this.zzcdt, this.zzcfi));
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        this.zzcfh.requestLayout();
    }

    public final boolean zza(zzvx zzvx0) {
        IObjectWrapper iObjectWrapper0;
        if(zzvx0 == null) {
            return false;
        }
        try {
            iObjectWrapper0 = zzvx0.zzkc();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return false;
        }
        if(iObjectWrapper0 == null) {
            return false;
        }
        if(((View)ObjectWrapper.unwrap(iObjectWrapper0)).getParent() != null) {
            return false;
        }
        this.zzcfh.addView(((View)ObjectWrapper.unwrap(iObjectWrapper0)));
        this.zzbrh = zzvx0;
        return true;
    }

    private static boolean zzcm(int v) {
        return v == 1;
    }

    public final zzxj zzdq() {
        zzvx zzvx0 = this.zzbrh;
        if(zzvx0 == null) {
            return null;
        }
        try {
            return zzvx0.getVideoController();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return null;
        }
    }
}

