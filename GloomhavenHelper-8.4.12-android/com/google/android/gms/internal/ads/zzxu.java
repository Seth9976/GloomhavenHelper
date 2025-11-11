package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.RequestConfiguration.Builder;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus.State;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public final class zzxu {
    private static final Object lock;
    @GuardedBy("lock")
    private static zzxu zzcfk;
    private zzwn zzcfl;
    private RewardedVideoAd zzcfm;
    @NonNull
    private RequestConfiguration zzcfn;
    private InitializationStatus zzcfo;

    static {
        zzxu.lock = new Object();
    }

    private zzxu() {
        this.zzcfn = new Builder().build();
    }

    public final InitializationStatus getInitializationStatus() {
        Preconditions.checkState(this.zzcfl != null, "MobileAds.initialize() must be called prior to getting initialization status.");
        try {
            return this.zzcfo == null ? zzxu.zzc(this.zzcfl.zzpl()) : this.zzcfo;
        }
        catch(RemoteException unused_ex) {
            zzazh.zzey("Unable to get Initialization status.");
            return null;
        }
    }

    @NonNull
    public final RequestConfiguration getRequestConfiguration() {
        return this.zzcfn;
    }

    // 检测为 Lambda 实现
    public final RewardedVideoAd getRewardedVideoAdInstance(Context context0) [...]

    public final String getVersionString() {
        Preconditions.checkState(this.zzcfl != null, "MobileAds.initialize() must be called prior to getting version string.");
        try {
            return this.zzcfl.getVersionString();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to get version string.", remoteException0);
            return "";
        }
    }

    public final void openDebugMenu(Context context0, String s) {
        Preconditions.checkState(this.zzcfl != null, "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzcfl.zzb(ObjectWrapper.wrap(context0), s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to open debug menu.", remoteException0);
        }
    }

    public final void registerRtbAdapter(Class class0) {
        try {
            this.zzcfl.zzcf(class0.getCanonicalName());
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to register RtbAdapter", remoteException0);
        }
    }

    public final void setAppMuted(boolean z) {
        Preconditions.checkState(this.zzcfl != null, "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzcfl.setAppMuted(z);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to set app mute state.", remoteException0);
        }
    }

    public final void setAppVolume(float f) {
        boolean z = true;
        Preconditions.checkArgument(0.0f <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        if(this.zzcfl == null) {
            z = false;
        }
        Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzcfl.setAppVolume(f);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to set app volume.", remoteException0);
        }
    }

    public final void setRequestConfiguration(@NonNull RequestConfiguration requestConfiguration0) {
        Preconditions.checkArgument(requestConfiguration0 != null, "Null passed to setRequestConfiguration.");
        RequestConfiguration requestConfiguration1 = this.zzcfn;
        this.zzcfn = requestConfiguration0;
        if(this.zzcfl == null) {
            return;
        }
        if(requestConfiguration1.getTagForChildDirectedTreatment() != requestConfiguration0.getTagForChildDirectedTreatment() || requestConfiguration1.getTagForUnderAgeOfConsent() != requestConfiguration0.getTagForUnderAgeOfConsent()) {
            this.zza(requestConfiguration0);
        }
    }

    static InitializationStatus zza(zzxu zzxu0, List list0) {
        return zzxu.zzc(list0);
    }

    private final void zza(@NonNull RequestConfiguration requestConfiguration0) {
        try {
            this.zzcfl.zza(new zzyw(requestConfiguration0));
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to set request configuration parcel.", remoteException0);
        }
    }

    public final void zza(Context context0, String s, zzyd zzyd0, OnInitializationCompleteListener onInitializationCompleteListener0) {
        synchronized(zzxu.lock) {
            if(this.zzcfl != null) {
                return;
            }
            if(context0 != null) {
                try {
                    zzalc.zzso().zzc(context0, s);
                    this.zzcfl = (zzwn)new zzva(zzvh.zzpa(), context0).zzd(context0, false);
                    if(onInitializationCompleteListener0 != null) {
                        this.zzcfl.zza(new zzyb(this, onInitializationCompleteListener0, null));
                    }
                    this.zzcfl.zza(new zzall());
                    this.zzcfl.initialize();
                    this.zzcfl.zza(s, ObjectWrapper.wrap(() -> synchronized(zzxu.lock) {
                        if(this.zzcfm != null) {
                            return this.zzcfm;
                        }
                        this.zzcfm = new zzasj(context0, ((zzary)new zzvf(zzvh.zzpa(), context0, new zzall()).zzd(context0, false)));
                        return this.zzcfm;
                    }));
                    if(this.zzcfn.getTagForChildDirectedTreatment() != -1 || this.zzcfn.getTagForUnderAgeOfConsent() != -1) {
                        this.zza(this.zzcfn);
                    }
                    zzzx.initialize(context0);
                    if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcpj)).booleanValue() && !this.zzpz()) {
                        zzazh.zzey("Google Mobile Ads SDK initialization functionality unavailable for this session. Ad requests can be made at any time.");
                        this.zzcfo = new zzxz(this);
                        if(onInitializationCompleteListener0 != null) {
                            zzxw zzxw0 = () -> onInitializationCompleteListener0.onInitializationComplete(this.zzcfo);
                            zzayx.zzyy.post(zzxw0);
                        }
                    }
                }
                catch(RemoteException remoteException0) {
                    zzazh.zzd("MobileAdsSettingManager initialization failed", remoteException0);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Context cannot be null.");
    }

    // 检测为 Lambda 实现
    final void zza(OnInitializationCompleteListener onInitializationCompleteListener0) [...]

    private static InitializationStatus zzc(List list0) {
        HashMap hashMap0 = new HashMap();
        for(Object object0: list0) {
            hashMap0.put(((zzagz)object0).zzczf, new zzahh((((zzagz)object0).zzczg ? State.READY : State.NOT_READY), ((zzagz)object0).description, ((zzagz)object0).zzczh));
        }
        return new zzahg(hashMap0);
    }

    public final float zzpj() {
        zzwn zzwn0 = this.zzcfl;
        if(zzwn0 == null) {
            return 1.0f;
        }
        try {
            return zzwn0.zzpj();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to get app volume.", remoteException0);
            return 1.0f;
        }
    }

    public final boolean zzpk() {
        zzwn zzwn0 = this.zzcfl;
        if(zzwn0 == null) {
            return false;
        }
        try {
            return zzwn0.zzpk();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to get app mute state.", remoteException0);
            return false;
        }
    }

    public static zzxu zzpy() {
        synchronized(zzxu.lock) {
            if(zzxu.zzcfk == null) {
                zzxu.zzcfk = new zzxu();
            }
            return zzxu.zzcfk;
        }
    }

    private final boolean zzpz() throws RemoteException {
        try {
            return this.zzcfl.getVersionString().endsWith("0");
        }
        catch(RemoteException unused_ex) {
            zzazh.zzey("Unable to get version string.");
            return true;
        }
    }
}

