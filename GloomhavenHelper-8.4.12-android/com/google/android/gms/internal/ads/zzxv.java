package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzxv {
    private final zzui zzabf;
    private AppEventListener zzbkr;
    private boolean zzblm;
    private zzvx zzbrh;
    private String zzbri;
    private final zzall zzbrk;
    private zztz zzcch;
    private AdListener zzcck;
    private AdMetadataListener zzccl;
    private OnCustomRenderedAdLoadedListener zzcfg;
    @Nullable
    private OnPaidEventListener zzcfj;
    private RewardedVideoAdListener zzcfp;
    private boolean zzcfq;
    private final Context zzur;

    public zzxv(Context context0) {
        this(context0, zzui.zzcdb, null);
    }

    public zzxv(Context context0, PublisherInterstitialAd publisherInterstitialAd0) {
        this(context0, zzui.zzcdb, publisherInterstitialAd0);
    }

    @VisibleForTesting
    private zzxv(Context context0, zzui zzui0, PublisherInterstitialAd publisherInterstitialAd0) {
        this.zzbrk = new zzall();
        this.zzur = context0;
        this.zzabf = zzui0;
    }

    public final AdListener getAdListener() {
        return this.zzcck;
    }

    public final Bundle getAdMetadata() {
        if(this.zzbrh != null) {
            try {
                return this.zzbrh.getAdMetadata();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
            }
        }
        return new Bundle();
    }

    public final String getAdUnitId() {
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
                zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
            }
        }
        return null;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcfg;
    }

    public final ResponseInfo getResponseInfo() {
        if(this.zzbrh != null) {
            try {
                return ResponseInfo.zza(this.zzbrh.zzkg());
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
                return ResponseInfo.zza(null);
            }
        }
        return ResponseInfo.zza(null);
    }

    public final boolean isLoaded() {
        try {
            return this.zzbrh == null ? false : this.zzbrh.isReady();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
            return false;
        }
    }

    public final boolean isLoading() {
        try {
            return this.zzbrh == null ? false : this.zzbrh.isLoading();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
            return false;
        }
    }

    public final void setAdListener(AdListener adListener0) {
        try {
            this.zzcck = adListener0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((adListener0 == null ? null : new zzud(adListener0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener0) {
        try {
            this.zzccl = adMetadataListener0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((adMetadataListener0 == null ? null : new zzue(adMetadataListener0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    public final void setAdUnitId(String s) {
        if(this.zzbri != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
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
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    public final void setImmersiveMode(boolean z) {
        try {
            this.zzblm = z;
            if(this.zzbrh != null) {
                this.zzbrh.setImmersiveMode(z);
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
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
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
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

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener0) {
        try {
            this.zzcfp = rewardedVideoAdListener0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((rewardedVideoAdListener0 == null ? null : new zzasi(rewardedVideoAdListener0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    public final void show() {
        try {
            this.zzco("show");
            this.zzbrh.showInterstitial();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    public final void zza(zztz zztz0) {
        try {
            this.zzcch = zztz0;
            if(this.zzbrh != null) {
                this.zzbrh.zza((zztz0 == null ? null : new zzty(zztz0)));
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    public final void zza(zzxr zzxr0) {
        try {
            if(this.zzbrh == null) {
                if(this.zzbri == null) {
                    this.zzco("loadAd");
                }
                zzuk zzuk0 = this.zzcfq ? zzuk.zzoq() : new zzuk();
                this.zzbrh = (zzvx)new zzuy(zzvh.zzpa(), this.zzur, zzuk0, this.zzbri, this.zzbrk).zzd(this.zzur, false);
                if(this.zzcck != null) {
                    this.zzbrh.zza(new zzud(this.zzcck));
                }
                if(this.zzcch != null) {
                    this.zzbrh.zza(new zzty(this.zzcch));
                }
                if(this.zzccl != null) {
                    this.zzbrh.zza(new zzue(this.zzccl));
                }
                if(this.zzbkr != null) {
                    this.zzbrh.zza(new zzuo(this.zzbkr));
                }
                if(this.zzcfg != null) {
                    this.zzbrh.zza(new zzaav(this.zzcfg));
                }
                if(this.zzcfp != null) {
                    this.zzbrh.zza(new zzasi(this.zzcfp));
                }
                this.zzbrh.zza(new zzyx(this.zzcfj));
                this.zzbrh.setImmersiveMode(this.zzblm);
            }
            if(this.zzbrh.zza(zzui.zza(this.zzur, zzxr0))) {
                this.zzbrk.zzf(zzxr0.zzps());
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#008 Must be called on the main UI thread.", remoteException0);
        }
    }

    private final void zzco(String s) {
        if(this.zzbrh == null) {
            throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + s + " is called.");
        }
    }

    public final void zzd(boolean z) {
        this.zzcfq = true;
    }
}

