package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.mediation.rtb.RtbSignalData;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzanz extends zzant {
    private MediationRewardedAd zzdep;
    private final RtbAdapter zzdfp;
    private MediationInterstitialAd zzdfq;
    private String zzdfr;

    public zzanz(RtbAdapter rtbAdapter0) {
        this.zzdfr = "";
        this.zzdfp = rtbAdapter0;
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final zzxj getVideoController() {
        RtbAdapter rtbAdapter0 = this.zzdfp;
        if(!(rtbAdapter0 instanceof zza)) {
            return null;
        }
        try {
            return ((zza)rtbAdapter0).getVideoController();
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            return null;
        }
    }

    static MediationInterstitialAd zza(zzanz zzanz0, MediationInterstitialAd mediationInterstitialAd0) {
        zzanz0.zzdfq = mediationInterstitialAd0;
        return mediationInterstitialAd0;
    }

    static MediationRewardedAd zza(zzanz zzanz0, MediationRewardedAd mediationRewardedAd0) {
        zzanz0.zzdep = mediationRewardedAd0;
        return mediationRewardedAd0;
    }

    @Nullable
    private static String zza(String s, zzuh zzuh0) {
        String s1;
        try {
            s1 = zzuh0.zzabx;
            return new JSONObject(s).getString("max_ad_content_rating");
        }
        catch(JSONException unused_ex) {
            return s1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(IObjectWrapper iObjectWrapper0, String s, Bundle bundle0, Bundle bundle1, zzuk zzuk0, zzanv zzanv0) throws RemoteException {
        AdFormat adFormat0;
        try {
            zzaoc zzaoc0 = new zzaoc(this, zzanv0);
            RtbAdapter rtbAdapter0 = this.zzdfp;
            switch(s) {
                case "banner": {
                    adFormat0 = AdFormat.BANNER;
                    break;
                }
                case "interstitial": {
                    adFormat0 = AdFormat.INTERSTITIAL;
                    break;
                }
                case "native": {
                    adFormat0 = AdFormat.NATIVE;
                    break;
                }
                case "rewarded": {
                    adFormat0 = AdFormat.REWARDED;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Internal Error");
                }
            }
            MediationConfiguration mediationConfiguration0 = new MediationConfiguration(adFormat0, bundle1);
            ArrayList arrayList0 = new ArrayList();
            arrayList0.add(mediationConfiguration0);
            rtbAdapter0.collectSignals(new RtbSignalData(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), arrayList0, bundle0, zzb.zza(zzuk0.width, zzuk0.height, zzuk0.zzabk)), zzaoc0);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("Error generating signals for RTB", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzane zzane0, zzalq zzalq0, zzuk zzuk0) throws RemoteException {
        try {
            zzany zzany0 = new zzany(this, zzane0, zzalq0);
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            Bundle bundle0 = zzanz.zzdq(s1);
            Bundle bundle1 = this.zzd(zzuh0);
            boolean z = zzanz.zzc(zzuh0);
            String s2 = zzanz.zza(s1, zzuh0);
            AdSize adSize0 = zzb.zza(zzuk0.width, zzuk0.height, zzuk0.zzabk);
            MediationBannerAdConfiguration mediationBannerAdConfiguration0 = new MediationBannerAdConfiguration(((Context)object0), s, bundle0, bundle1, z, zzuh0.zzmk, zzuh0.zzabv, zzuh0.zzabw, s2, adSize0, this.zzdfr);
            this.zzdfp.loadBannerAd(mediationBannerAdConfiguration0, zzany0);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("Adapter failed to render banner ad.", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzanj zzanj0, zzalq zzalq0) throws RemoteException {
        try {
            zzaob zzaob0 = new zzaob(this, zzanj0, zzalq0);
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            Bundle bundle0 = zzanz.zzdq(s1);
            Bundle bundle1 = this.zzd(zzuh0);
            boolean z = zzanz.zzc(zzuh0);
            String s2 = zzanz.zza(s1, zzuh0);
            MediationInterstitialAdConfiguration mediationInterstitialAdConfiguration0 = new MediationInterstitialAdConfiguration(((Context)object0), s, bundle0, bundle1, z, zzuh0.zzmk, zzuh0.zzabv, zzuh0.zzabw, s2, this.zzdfr);
            this.zzdfp.loadInterstitialAd(mediationInterstitialAdConfiguration0, zzaob0);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("Adapter failed to render interstitial ad.", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzank zzank0, zzalq zzalq0) throws RemoteException {
        try {
            zzaod zzaod0 = new zzaod(this, zzank0, zzalq0);
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            Bundle bundle0 = zzanz.zzdq(s1);
            Bundle bundle1 = this.zzd(zzuh0);
            boolean z = zzanz.zzc(zzuh0);
            String s2 = zzanz.zza(s1, zzuh0);
            MediationNativeAdConfiguration mediationNativeAdConfiguration0 = new MediationNativeAdConfiguration(((Context)object0), s, bundle0, bundle1, z, zzuh0.zzmk, zzuh0.zzabv, zzuh0.zzabw, s2, this.zzdfr);
            this.zzdfp.loadNativeAd(mediationNativeAdConfiguration0, zzaod0);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("Adapter failed to render rewarded ad.", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzanp zzanp0, zzalq zzalq0) throws RemoteException {
        try {
            zzaoa zzaoa0 = new zzaoa(this, zzanp0, zzalq0);
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            Bundle bundle0 = zzanz.zzdq(s1);
            Bundle bundle1 = this.zzd(zzuh0);
            boolean z = zzanz.zzc(zzuh0);
            String s2 = zzanz.zza(s1, zzuh0);
            MediationRewardedAdConfiguration mediationRewardedAdConfiguration0 = new MediationRewardedAdConfiguration(((Context)object0), s, bundle0, bundle1, z, zzuh0.zzmk, zzuh0.zzabv, zzuh0.zzabw, s2, this.zzdfr);
            this.zzdfp.loadRewardedAd(mediationRewardedAdConfiguration0, zzaoa0);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("Adapter failed to render rewarded ad.", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String[] arr_s, Bundle[] arr_bundle) {
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final boolean zzaa(IObjectWrapper iObjectWrapper0) throws RemoteException {
        MediationRewardedAd mediationRewardedAd0 = this.zzdep;
        if(mediationRewardedAd0 == null) {
            return false;
        }
        try {
            mediationRewardedAd0.showAd(((Context)ObjectWrapper.unwrap(iObjectWrapper0)));
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
        }
        return true;
    }

    // 去混淆评级： 低(20)
    private static boolean zzc(zzuh zzuh0) {
        return zzuh0.zzccp || zzayx.zzxi();
    }

    private final Bundle zzd(zzuh zzuh0) {
        if(zzuh0.zzcct != null) {
            String s = this.zzdfp.getClass().getName();
            Bundle bundle0 = zzuh0.zzcct.getBundle(s);
            return bundle0 == null ? new Bundle() : bundle0;
        }
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zzdn(String s) {
        this.zzdfr = s;
    }

    private static Bundle zzdq(String s) throws RemoteException {
        String s1 = String.valueOf(s);
        zzazh.zzfa((s1.length() == 0 ? new String("Server parameters: ") : "Server parameters: " + s1));
        try {
            Bundle bundle0 = new Bundle();
            if(s != null) {
                JSONObject jSONObject0 = new JSONObject(s);
                Bundle bundle1 = new Bundle();
                Iterator iterator0 = jSONObject0.keys();
                while(iterator0.hasNext()) {
                    Object object0 = iterator0.next();
                    bundle1.putString(((String)object0), jSONObject0.getString(((String)object0)));
                }
                return bundle1;
            }
            return bundle0;
        }
        catch(JSONException jSONException0) {
            zzazh.zzc("", jSONException0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final zzaoe zzth() throws RemoteException {
        return zzaoe.zza(this.zzdfp.getVersionInfo());
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final zzaoe zzti() throws RemoteException {
        return zzaoe.zza(this.zzdfp.getSDKVersionInfo());
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zzy(IObjectWrapper iObjectWrapper0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final boolean zzz(IObjectWrapper iObjectWrapper0) throws RemoteException {
        MediationInterstitialAd mediationInterstitialAd0 = this.zzdfq;
        if(mediationInterstitialAd0 == null) {
            return false;
        }
        try {
            mediationInterstitialAd0.showAd(((Context)ObjectWrapper.unwrap(iObjectWrapper0)));
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
        }
        return true;
    }
}

