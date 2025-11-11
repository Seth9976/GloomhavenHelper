package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzamg extends zzalo {
    private final Object zzdel;
    private zzaml zzdem;
    private zzasm zzden;
    private IObjectWrapper zzdeo;
    private MediationRewardedAd zzdep;

    public zzamg(@NonNull Adapter adapter0) {
        this.zzdel = adapter0;
    }

    public zzamg(@NonNull MediationAdapter mediationAdapter0) {
        this.zzdel = mediationAdapter0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void destroy() throws RemoteException {
        Object object0 = this.zzdel;
        if(!(object0 instanceof MediationAdapter)) {
            return;
        }
        try {
            ((MediationAdapter)object0).onDestroy();
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle getInterstitialAdapterInfo() {
        Object object0 = this.zzdel;
        if(!(object0 instanceof zzbgl)) {
            String s = this.zzdel.getClass().getCanonicalName();
            zzazh.zzfa((zzbgl.class.getCanonicalName() + " #009 Class mismatch: " + s));
            return new Bundle();
        }
        return ((zzbgl)object0).getInterstitialAdapterInfo();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzxj getVideoController() {
        Object object0 = this.zzdel;
        if(!(object0 instanceof zza)) {
            return null;
        }
        try {
            return ((zza)object0).getVideoController();
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            return null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final boolean isInitialized() throws RemoteException {
        Object object0 = this.zzdel;
        if(object0 instanceof MediationRewardedVideoAdAdapter) {
            zzazh.zzeb("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter)this.zzdel).isInitialized();
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        if(object0 instanceof Adapter) {
            return this.zzden != null;
        }
        String s = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationRewardedVideoAdAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + s));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void pause() throws RemoteException {
        Object object0 = this.zzdel;
        if(!(object0 instanceof MediationAdapter)) {
            return;
        }
        try {
            ((MediationAdapter)object0).onPause();
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void resume() throws RemoteException {
        Object object0 = this.zzdel;
        if(!(object0 instanceof MediationAdapter)) {
            return;
        }
        try {
            ((MediationAdapter)object0).onResume();
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void setImmersiveMode(boolean z) throws RemoteException {
        Object object0 = this.zzdel;
        if(!(object0 instanceof OnImmersiveModeUpdatedListener)) {
            String s = this.zzdel.getClass().getCanonicalName();
            zzazh.zzeb((OnImmersiveModeUpdatedListener.class.getCanonicalName() + " #009 Class mismatch: " + s));
            return;
        }
        try {
            ((OnImmersiveModeUpdatedListener)object0).onImmersiveModeUpdated(z);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void showInterstitial() throws RemoteException {
        if(this.zzdel instanceof MediationInterstitialAdapter) {
            zzazh.zzeb("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter)this.zzdel).showInterstitial();
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        String s = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationInterstitialAdapter.class.getCanonicalName() + " #009 Class mismatch: " + s));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void showVideo() throws RemoteException {
        Object object0 = this.zzdel;
        if(object0 instanceof MediationRewardedVideoAdAdapter) {
            zzazh.zzeb("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter)this.zzdel).showVideo();
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        if(object0 instanceof Adapter) {
            MediationRewardedAd mediationRewardedAd0 = this.zzdep;
            if(mediationRewardedAd0 != null) {
                mediationRewardedAd0.showAd(((Context)ObjectWrapper.unwrap(this.zzdeo)));
                return;
            }
            zzazh.zzey("Can not show null mediated rewarded ad.");
            throw new RemoteException();
        }
        String s = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationRewardedVideoAdAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + s));
        throw new RemoteException();
    }

    private final Bundle zza(String s, zzuh zzuh0, String s1) throws RemoteException {
        Bundle bundle1;
        String s2 = String.valueOf(s);
        zzazh.zzeb((s2.length() == 0 ? new String("Server parameters: ") : "Server parameters: " + s2));
        try {
            Bundle bundle0 = new Bundle();
            if(s == null) {
                bundle1 = bundle0;
            }
            else {
                JSONObject jSONObject0 = new JSONObject(s);
                bundle1 = new Bundle();
                Iterator iterator0 = jSONObject0.keys();
                while(iterator0.hasNext()) {
                    Object object0 = iterator0.next();
                    bundle1.putString(((String)object0), jSONObject0.getString(((String)object0)));
                }
            }
            if(this.zzdel instanceof AdMobAdapter) {
                bundle1.putString("adJson", s1);
                if(zzuh0 != null) {
                    bundle1.putInt("tagForChildDirectedTreatment", zzuh0.zzabv);
                }
            }
            bundle1.remove("max_ad_content_rating");
            return bundle1;
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    static MediationRewardedAd zza(zzamg zzamg0, MediationRewardedAd mediationRewardedAd0) {
        zzamg0.zzdep = mediationRewardedAd0;
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

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzahb zzahb0, List list0) throws RemoteException {
        AdFormat adFormat0;
        if(!(this.zzdel instanceof Adapter)) {
            throw new RemoteException();
        }
        zzami zzami0 = new zzami(this, zzahb0);
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: list0) {
            switch(((zzahj)object0).zzczk) {
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
                    throw new RemoteException();
                }
            }
            arrayList0.add(new MediationConfiguration(adFormat0, ((zzahj)object0).extras));
        }
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        ((Adapter)this.zzdel).initialize(context0, zzami0, arrayList0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzasm zzasm0, List list0) throws RemoteException {
        if(this.zzdel instanceof InitializableMediationRewardedVideoAdAdapter) {
            zzazh.zzeb("Initialize rewarded video adapter.");
            try {
                InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter0 = (InitializableMediationRewardedVideoAdAdapter)this.zzdel;
                ArrayList arrayList0 = new ArrayList();
                for(Object object0: list0) {
                    arrayList0.add(this.zza(((String)object0), null, null));
                }
                initializableMediationRewardedVideoAdAdapter0.initialize(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), new zzasn(zzasm0), arrayList0);
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzd("Could not initialize rewarded video adapter.", throwable0);
                throw new RemoteException();
            }
        }
        String s = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((InitializableMediationRewardedVideoAdAdapter.class.getCanonicalName() + " #009 Class mismatch: " + s));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        this.zza(iObjectWrapper0, zzuh0, s, null, zzalq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzasm zzasm0, String s1) throws RemoteException {
        MediationAdRequest mediationAdRequest0;
        Bundle bundle1;
        Object object0 = this.zzdel;
        if(object0 instanceof MediationRewardedVideoAdAdapter) {
            zzazh.zzeb("Initialize rewarded video adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0 = (MediationRewardedVideoAdAdapter)this.zzdel;
                Bundle bundle0 = this.zza(s1, zzuh0, null);
                if(zzuh0 == null) {
                    mediationAdRequest0 = null;
                    bundle1 = null;
                }
                else {
                    HashSet hashSet0 = zzuh0.zzcco == null ? null : new HashSet(zzuh0.zzcco);
                    Date date0 = zzuh0.zzccm == -1L ? null : new Date(zzuh0.zzccm);
                    boolean z = zzamg.zzc(zzuh0);
                    String s2 = zzamg.zza(s1, zzuh0);
                    zzamh zzamh0 = new zzamh(date0, zzuh0.zzccn, hashSet0, zzuh0.zzmk, z, zzuh0.zzabv, zzuh0.zzccy, zzuh0.zzabw, s2);
                    if(zzuh0.zzcct == null) {
                        bundle1 = null;
                    }
                    else {
                        String s3 = mediationRewardedVideoAdAdapter0.getClass().getName();
                        bundle1 = zzuh0.zzcct.getBundle(s3);
                    }
                    mediationAdRequest0 = zzamh0;
                }
                mediationRewardedVideoAdAdapter0.initialize(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), mediationAdRequest0, s, new zzasn(zzasm0), bundle0, bundle1);
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        if(object0 instanceof Adapter) {
            this.zzdeo = iObjectWrapper0;
            this.zzden = zzasm0;
            zzasm0.zzaf(ObjectWrapper.wrap(object0));
            return;
        }
        String s4 = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationRewardedVideoAdAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + s4));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws RemoteException {
        Bundle bundle0;
        if(this.zzdel instanceof MediationInterstitialAdapter) {
            zzazh.zzeb("Requesting interstitial ad from adapter.");
            try {
                MediationInterstitialAdapter mediationInterstitialAdapter0 = (MediationInterstitialAdapter)this.zzdel;
                HashSet hashSet0 = zzuh0.zzcco == null ? null : new HashSet(zzuh0.zzcco);
                Date date0 = zzuh0.zzccm == -1L ? null : new Date(zzuh0.zzccm);
                boolean z = zzamg.zzc(zzuh0);
                String s2 = zzamg.zza(s, zzuh0);
                zzamh zzamh0 = new zzamh(date0, zzuh0.zzccn, hashSet0, zzuh0.zzmk, z, zzuh0.zzabv, zzuh0.zzccy, zzuh0.zzabw, s2);
                if(zzuh0.zzcct == null) {
                    bundle0 = null;
                }
                else {
                    String s3 = mediationInterstitialAdapter0.getClass().getName();
                    bundle0 = zzuh0.zzcct.getBundle(s3);
                }
                mediationInterstitialAdapter0.requestInterstitialAd(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), new zzaml(zzalq0), this.zza(s, zzuh0, s1), zzamh0, bundle0);
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        String s4 = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationInterstitialAdapter.class.getCanonicalName() + " #009 Class mismatch: " + s4));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, String s1, zzalq zzalq0, zzach zzach0, List list0) throws RemoteException {
        Bundle bundle0 = null;
        Object object0 = this.zzdel;
        if(object0 instanceof MediationNativeAdapter) {
            try {
                HashSet hashSet0 = zzuh0.zzcco == null ? null : new HashSet(zzuh0.zzcco);
                Date date0 = zzuh0.zzccm == -1L ? null : new Date(zzuh0.zzccm);
                boolean z = zzamg.zzc(zzuh0);
                String s2 = zzamg.zza(s, zzuh0);
                zzamp zzamp0 = new zzamp(date0, zzuh0.zzccn, hashSet0, zzuh0.zzmk, z, zzuh0.zzabv, zzach0, list0, zzuh0.zzccy, zzuh0.zzabw, s2);
                if(zzuh0.zzcct != null) {
                    String s3 = ((MediationNativeAdapter)object0).getClass().getName();
                    bundle0 = zzuh0.zzcct.getBundle(s3);
                }
                this.zzdem = new zzaml(zzalq0);
                ((MediationNativeAdapter)object0).requestNativeAd(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), this.zzdem, this.zza(s, zzuh0, s1), zzamp0, bundle0);
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        String s4 = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationNativeAdapter.class.getCanonicalName() + " #009 Class mismatch: " + s4));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        this.zza(iObjectWrapper0, zzuk0, zzuh0, s, null, zzalq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws RemoteException {
        Bundle bundle0;
        if(this.zzdel instanceof MediationBannerAdapter) {
            zzazh.zzeb("Requesting banner ad from adapter.");
            try {
                MediationBannerAdapter mediationBannerAdapter0 = (MediationBannerAdapter)this.zzdel;
                HashSet hashSet0 = zzuh0.zzcco == null ? null : new HashSet(zzuh0.zzcco);
                Date date0 = zzuh0.zzccm == -1L ? null : new Date(zzuh0.zzccm);
                boolean z = zzamg.zzc(zzuh0);
                String s2 = zzamg.zza(s, zzuh0);
                zzamh zzamh0 = new zzamh(date0, zzuh0.zzccn, hashSet0, zzuh0.zzmk, z, zzuh0.zzabv, zzuh0.zzccy, zzuh0.zzabw, s2);
                if(zzuh0.zzcct == null) {
                    bundle0 = null;
                }
                else {
                    String s3 = mediationBannerAdapter0.getClass().getName();
                    bundle0 = zzuh0.zzcct.getBundle(s3);
                }
                AdSize adSize0 = zzuk0.zzcdj ? zzb.zza(zzuk0.width, zzuk0.height) : zzb.zza(zzuk0.width, zzuk0.height, zzuk0.zzabk);
                mediationBannerAdapter0.requestBannerAd(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), new zzaml(zzalq0), this.zza(s, zzuh0, s1), adSize0, zzamh0, bundle0);
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        String s4 = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationBannerAdapter.class.getCanonicalName() + " #009 Class mismatch: " + s4));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(zzuh zzuh0, String s) throws RemoteException {
        this.zza(zzuh0, s, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(zzuh zzuh0, String s, String s1) throws RemoteException {
        Bundle bundle0;
        Object object0 = this.zzdel;
        if(object0 instanceof MediationRewardedVideoAdAdapter) {
            zzazh.zzeb("Requesting rewarded video ad from adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0 = (MediationRewardedVideoAdAdapter)this.zzdel;
                HashSet hashSet0 = zzuh0.zzcco == null ? null : new HashSet(zzuh0.zzcco);
                Date date0 = zzuh0.zzccm == -1L ? null : new Date(zzuh0.zzccm);
                boolean z = zzamg.zzc(zzuh0);
                String s2 = zzamg.zza(s, zzuh0);
                zzamh zzamh0 = new zzamh(date0, zzuh0.zzccn, hashSet0, zzuh0.zzmk, z, zzuh0.zzabv, zzuh0.zzccy, zzuh0.zzabw, s2);
                if(zzuh0.zzcct == null) {
                    bundle0 = null;
                }
                else {
                    String s3 = mediationRewardedVideoAdAdapter0.getClass().getName();
                    bundle0 = zzuh0.zzcct.getBundle(s3);
                }
                mediationRewardedVideoAdAdapter0.loadAd(zzamh0, this.zza(s, zzuh0, s1), bundle0);
                return;
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        if(object0 instanceof Adapter) {
            this.zzb(this.zzdeo, zzuh0, s, new zzamk(((Adapter)object0), this.zzden));
            return;
        }
        String s4 = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationRewardedVideoAdAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + s4));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzb(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        Bundle bundle2;
        if(this.zzdel instanceof Adapter) {
            zzazh.zzeb("Requesting rewarded ad from adapter.");
            try {
                Adapter adapter0 = (Adapter)this.zzdel;
                zzamj zzamj0 = new zzamj(this, zzalq0, adapter0);
                Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
                Bundle bundle0 = this.zza(s, zzuh0, null);
                if(zzuh0.zzcct == null) {
                    bundle2 = new Bundle();
                }
                else {
                    String s1 = this.zzdel.getClass().getName();
                    Bundle bundle1 = zzuh0.zzcct.getBundle(s1);
                    bundle2 = bundle1 == null ? new Bundle() : bundle1;
                }
                boolean z = zzamg.zzc(zzuh0);
                String s2 = zzamg.zza(s, zzuh0);
                adapter0.loadRewardedAd(new MediationRewardedAdConfiguration(((Context)object0), "", bundle0, bundle2, z, zzuh0.zzmk, zzuh0.zzabv, zzuh0.zzabw, s2, ""), zzamj0);
                return;
            }
            catch(Exception exception0) {
                zzazh.zzc("", exception0);
                throw new RemoteException();
            }
        }
        String s3 = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((Adapter.class.getCanonicalName() + " #009 Class mismatch: " + s3));
        throw new RemoteException();
    }

    // 去混淆评级： 低(20)
    private static boolean zzc(zzuh zzuh0) {
        return zzuh0.zzccp || zzayx.zzxi();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzs(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        Object object0 = this.zzdel;
        if(object0 instanceof OnContextChangedListener) {
            ((OnContextChangedListener)object0).onContextChanged(context0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final IObjectWrapper zzsp() throws RemoteException {
        Object object0 = this.zzdel;
        if(object0 instanceof MediationBannerAdapter) {
            try {
                return ObjectWrapper.wrap(((MediationBannerAdapter)object0).getBannerView());
            }
            catch(Throwable throwable0) {
                zzazh.zzc("", throwable0);
                throw new RemoteException();
            }
        }
        String s = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((MediationBannerAdapter.class.getCanonicalName() + " #009 Class mismatch: " + s));
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzalx zzsq() {
        NativeAdMapper nativeAdMapper0 = this.zzdem.zztb();
        return nativeAdMapper0 instanceof NativeAppInstallAdMapper ? new zzamn(((NativeAppInstallAdMapper)nativeAdMapper0)) : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzaly zzsr() {
        NativeAdMapper nativeAdMapper0 = this.zzdem.zztb();
        return nativeAdMapper0 instanceof NativeContentAdMapper ? new zzamm(((NativeContentAdMapper)nativeAdMapper0)) : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle zzss() {
        Object object0 = this.zzdel;
        if(!(object0 instanceof zzbgj)) {
            String s = this.zzdel.getClass().getCanonicalName();
            zzazh.zzfa((zzbgj.class.getCanonicalName() + " #009 Class mismatch: " + s));
            return new Bundle();
        }
        return ((zzbgj)object0).zzss();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle zzst() {
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final boolean zzsu() {
        return this.zzdel instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzadn zzsv() {
        NativeCustomTemplateAd nativeCustomTemplateAd0 = this.zzdem.zztd();
        return nativeCustomTemplateAd0 instanceof zzado ? ((zzado)nativeCustomTemplateAd0).zzrs() : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzamd zzsw() {
        UnifiedNativeAdMapper unifiedNativeAdMapper0 = this.zzdem.zztc();
        return unifiedNativeAdMapper0 != null ? new zzanf(unifiedNativeAdMapper0) : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzt(IObjectWrapper iObjectWrapper0) throws RemoteException {
        if(this.zzdel instanceof Adapter) {
            zzazh.zzeb("Show rewarded ad from adapter.");
            MediationRewardedAd mediationRewardedAd0 = this.zzdep;
            if(mediationRewardedAd0 != null) {
                mediationRewardedAd0.showAd(((Context)ObjectWrapper.unwrap(iObjectWrapper0)));
                return;
            }
            zzazh.zzey("Can not show null mediation rewarded ad.");
            throw new RemoteException();
        }
        String s = this.zzdel.getClass().getCanonicalName();
        zzazh.zzfa((Adapter.class.getCanonicalName() + " #009 Class mismatch: " + s));
        throw new RemoteException();
    }
}

